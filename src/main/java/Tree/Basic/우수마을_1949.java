package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class 우수마을_1949 {
    int V;
    int[] populations;
    ArrayList<Integer>[] tree;
    int[][] dp;

    private final static int DEFAULT = -1;
    private final static int GREAT = 1;
    private final static int NOT_GREAT = 0;

    public 우수마을_1949(int V, int[] populations, ArrayList<Integer>[] graph) {
        this.V = V;
        this.populations = populations;

        dp = new int[V + 1][2];

        tree = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) {
            Arrays.fill(dp[v], DEFAULT);
            tree[v] = new ArrayList<>();
        }
        setTree(1, new boolean[V + 1], graph);
    }

    void setTree(int node, boolean[] check, ArrayList<Integer>[] graph) {
        check[node] = true;

        for (int child : graph[node]) {
            if(check[child]) continue;

            tree[node].add(child);
            setTree(child, check, graph);
        }
    }

    int solve() {
        return Math.max(solve(1, GREAT, GREAT)
                , solve(1, NOT_GREAT, GREAT));
    }

    private int solve(int root, int townFeature, int parentTownFeature) {
        if(tree[root].isEmpty()) {
            if(townFeature == GREAT){
                return populations[root];
            } else{
                return parentTownFeature == NOT_GREAT ? -1 : 0;
            }
        }

        if(dp[root][townFeature] != DEFAULT) return dp[root][townFeature];

        if(townFeature == GREAT){
            dp[root][townFeature] = populations[root];
            for (int child : tree[root]) {
                dp[root][townFeature] += solve(child, NOT_GREAT, townFeature);
            }
        } else{
            dp[root][townFeature] = 0;
            for (int child : tree[root]) {
                dp[root][townFeature] += Math.max(solve(child, NOT_GREAT, townFeature), solve(child, GREAT, townFeature));
            }
        }

        return dp[root][townFeature];
    }
}

class MainA1949{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int[] populations = InputProcessor.strToIntArr("0 " + br.readLine());

        int[] tmp;
        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) {
            graph[v] = new ArrayList<>();
        }

        for (int e = 0; e < V - 1; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        System.out.println(new 우수마을_1949(V, populations, graph).solve());
    }
}