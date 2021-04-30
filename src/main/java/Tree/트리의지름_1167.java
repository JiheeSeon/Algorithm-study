package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

class 트리의지름_1167 {
    static ArrayList<Edge>[] graph;
    static boolean[] check;
    static int last = -1;
    static int maxx = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        check = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] tmp; int startV;
        for (int i = 0; i < N; i++) {
            tmp = strToIntArray(br.readLine());
            startV = tmp[0];
            for (int j = 1; j < tmp.length - 1; j += 2) {
                graph[startV].add(new Edge(tmp[j], tmp[j + 1]));
            }
        }

        int max;

        check = new boolean[N + 1];
        max = dfs(1, 0);
        check = new boolean[N + 1];
        max = Math.max(dfs(last, 0), max);

        System.out.println(max);
    }

    static int dfs(int curr, int weight) {
        if(check[curr]) return weight;

        check[curr] = true;

        int max = -1;
        for(Edge e : graph[curr]){
            if(!check[e.vertex]) {
                max = Math.max(max, dfs(e.vertex, weight + e.weight));
            }
        }
        if(max == -1){
            if(maxx < weight){
                maxx = weight;
                last = curr;
            }
        }
        return max == -1 ? weight : max;
    }

    static int[] strToIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
    static private class Edge{
        int vertex, weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
/*
6
1 2 3 -1
2 1 3 5 3 3 5 -1
3 2 5 4 7 -1
4 3 7 -1
5 2 3 6 5 -1
6 5 5 -1

*/