package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class 트리의독립집합_2213{
    int V;
    int[] weights;
    ArrayList<Integer>[] tree;

    int[][] dp;
    final int IN_SET = 1;
    final int NOT_IN_SET = 0;
    final int DEFAULT = -1;

    public 트리의독립집합_2213(int V, int[] weights, ArrayList<Integer>[] graph) {
        this.V = V;
        this.weights = weights;

        tree = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) tree[v] = new ArrayList<>();
        setTree(1, new boolean[V + 1], graph);

        dp = new int[V + 1][2];
        for (int v = 0; v <= V; v++) {
            Arrays.fill(dp[v], DEFAULT);
        }
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
        return Math.max(solve(1, IN_SET), solve(1, NOT_IN_SET));
    }

    private int solve(int node, int setFeature) {
        if(dp[node][setFeature] != -1) return dp[node][setFeature];

        if(setFeature == IN_SET){
            dp[node][setFeature] = weights[node];

            for (int child : tree[node])
                dp[node][setFeature] += solve(child, NOT_IN_SET);
        } else{
            dp[node][setFeature] = 0;

            for (int child : tree[node])
                dp[node][setFeature] += Math.max(solve(child, NOT_IN_SET), solve(child, IN_SET));
        }
        return dp[node][setFeature];
    }
}

class MainA2213{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());
        int[] weights = InputProcessor.strToIntArr("0 " + br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++)
            graph[v] = new ArrayList<>();

        int[] tmp;
        for (int e = 0; e < V - 1; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        System.out.println(new 트리의독립집합_2213(V, weights, graph).solve());
    }
}