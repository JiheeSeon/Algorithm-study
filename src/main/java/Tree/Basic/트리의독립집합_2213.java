package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

class 트리의독립집합_2213 {
    int V;
    int[] weights;
    ArrayList<Integer>[] tree;
    int[][] dp;

    final static int IN_SET = 1;
    final static int NOT_IN_SET = 0;
    final static int DEFAULT = -1;

    public 트리의독립집합_2213(int V, int[] weights, ArrayList<Integer>[] graph) {
        this.V = V;
        this.weights = weights;

        tree = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++)
            tree[v] = new ArrayList<>();

        makeTree(1, new boolean[V + 1], graph);
    }

    void makeTree(int node, boolean[] check, ArrayList<Integer>[] graph){
        check[node] = true;

        for (int child : graph[node]) {
            if(check[child]) continue;

            tree[node].add(child);
            makeTree(child, check, graph);
        }
    }

    String solve() {
        dp = new int[V + 1][2];
        for (int v = 0; v < V + 1; v++) Arrays.fill(dp[v], DEFAULT);

        StringBuilder stb = new StringBuilder();

        Set<Integer> setForElement = new TreeSet<>();
        Set<Integer> setForNonElement = new TreeSet<>();

        int retInSet = solve(1, IN_SET, setForElement);
        int retNotInSet = solve(1, NOT_IN_SET, setForNonElement);

        if(retInSet > retNotInSet){
            setForElement.add(1);
            stb.append(retInSet).append("\n");
            setForElement.forEach(s -> stb.append(s).append(" "));
        }else{
            stb.append(retNotInSet).append("\n");
            setForNonElement.forEach(s -> stb.append(s).append(" "));
        }

        return stb.toString();
    }

    int solve(int root, int elementFlag, Set<Integer> set) {
        if(tree[root].isEmpty()) {
            return elementFlag == IN_SET ? weights[root] : 0;
        }

        if(dp[root][elementFlag] != DEFAULT) return dp[root][elementFlag];

        dp[root][elementFlag] = elementFlag == IN_SET ? weights[root] : 0;

        if (elementFlag == IN_SET) {
            for (int child : tree[root]) {
                dp[root][elementFlag] += solve(child, NOT_IN_SET, set);
            }
            return dp[root][elementFlag];
        }else {
            for (int child : tree[root]) {
                int retInSet = solve(child, IN_SET, set);
                int retNotInSet = solve(child, NOT_IN_SET, set);

                if (retInSet > retNotInSet) {
                    set.add(child);
                    dp[root][elementFlag] += retInSet;
                } else {
                    set.remove(child);
                    dp[root][elementFlag] += retNotInSet;
                }
            }

            return dp[root][elementFlag];
        }
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