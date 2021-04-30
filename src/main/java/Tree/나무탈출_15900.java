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

class 나무탈출_15900 {
    static int[] parent;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        graph = new ArrayList[N + 1];
        for(int i = 1; i < graph.length; i++)
            graph[i] = new ArrayList<>();

        int[] tmp;
        for(int i = 0; i < N - 1 ; i++){
            tmp = strToIntArray(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        setParent(1, 1);

        Set<Integer> leaf = IntStream.rangeClosed(1, N).boxed().collect(Collectors.toCollection(HashSet::new));
        for(int i = 1; i <= N; i++){
            leaf.remove(parent[i]);
        }

        int totalLength = 0;
        for(int i : leaf)
            totalLength += getPathLength(i, 0);

        System.out.println(totalLength % 2 == 0 ? "No" : "Yes");
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static void setParent(int now, int prev){
        if(parent[now] != 0) return;

        parent[now] = prev;

        for(int i : graph[now]){
            setParent(i, now);
        }
    }

    static int getPathLength(int now, int len){
        if(now == 1) return len;
        return getPathLength(parent[now], len + 1);
    }
}
