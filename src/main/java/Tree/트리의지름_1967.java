package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 트리의지름_1967 {
    static ArrayList<Edge>[] graph;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];

        for(int i = 1; i < N + 1; i++)
            graph[i] = new ArrayList<>();

        int[] tmp;
        Set<Integer> leafs = IntStream.rangeClosed(1, N).boxed().collect(Collectors.toCollection(HashSet::new));
        for (int i = 0; i < N - 1; i++) {
            tmp = strToIntArray(br.readLine());
            leafs.remove(tmp[0]);
            graph[tmp[0]].add(new Edge(tmp[1], tmp[2]));
            graph[tmp[1]].add(new Edge(tmp[0], tmp[2]));
        }

        int max = -1;
        for(int i : leafs){ // leaf마다 dfs 돌기
            check = new boolean[N + 1];
            max = Math.max(max, dfs(i, 0));
        }
        System.out.println(max);
    }

    static int dfs(int now, int weightAcc){
        if(check[now]) return weightAcc;

        check[now] = true;

        int max = -1;
        for(Edge e : graph[now]){
            if(!check[e.end])
                max = Math.max(max, dfs(e.end, e.weight + weightAcc));
        }

        return max == -1 ? weightAcc : max; // max == -1 -> 갈 곳이 없는 leaf node
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    private static class Edge{
        int end, weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}
