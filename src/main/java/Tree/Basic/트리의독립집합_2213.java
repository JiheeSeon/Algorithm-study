package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 트리의독립집합_2213{
    int V;
    int[] weights;
    ArrayList<Integer>[] tree;

    int[][] valDp;
    Set<Integer>[][] setDp;

    final int IN_SET = 1;
    final int NOT_IN_SET = 0;
    final int DEFAULT = -1;

    public 트리의독립집합_2213(int V, int[] weights, ArrayList<Integer>[] graph) {
        this.V = V;
        this.weights = weights;

        tree = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) tree[v] = new ArrayList<>();
        setTree(1, new boolean[V + 1], graph);

        valDp = new int[V + 1][2];
        for (int v = 0; v <= V; v++) {
            Arrays.fill(valDp[v], DEFAULT);
        }
        setDp = new HashSet[V + 1][2];
        for (int v = 0; v <= V; v++) {
            Arrays.fill(setDp[v], null);
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

    String solve() {
        Set<Integer> setIfContainChild = solve(1, IN_SET);
        Set<Integer> setIfNotContainChild = solve(1, NOT_IN_SET);
        StringBuilder stb = new StringBuilder();

        if(valDp[1][IN_SET] > valDp[1][NOT_IN_SET]){
            stb.append(valDp[1][IN_SET]).append("\n");
            setIfContainChild.stream().sorted().forEach(s -> stb.append(s).append(" "));
        }
        else{
            stb.append(valDp[1][NOT_IN_SET]).append("\n");
            setIfNotContainChild.stream().sorted().forEach(s -> stb.append(s).append(" "));
        }
        return stb.toString();
    }

    private Set<Integer> solve(int node, int setFeature) {
        if(valDp[node][setFeature] != -1) return setDp[node][setFeature];

        setDp[node][setFeature] = new HashSet<>();

        Set<Integer> setIfNotContainChild;
        if(setFeature == IN_SET){
            valDp[node][setFeature] = weights[node];
            setDp[node][setFeature].add(node);

            for (int child : tree[node]) {
                setIfNotContainChild = solve(child, NOT_IN_SET);
                valDp[node][setFeature] += valDp[child][setFeature];
                setDp[node][setFeature].addAll(setIfNotContainChild);
            }
        } else{
            valDp[node][setFeature] = 0;

            for (int child : tree[node]) {
                Set<Integer> setIfContainsChild = solve(child, IN_SET);
                setIfNotContainChild = solve(child, NOT_IN_SET);

                if (valDp[child][IN_SET] > valDp[child][NOT_IN_SET]) {
                    valDp[node][setFeature] += valDp[child][IN_SET];
                    setDp[node][setFeature].addAll(setIfContainsChild);
                } else{
                    valDp[node][setFeature] += valDp[child][NOT_IN_SET];
                    setDp[node][setFeature].addAll(setIfNotContainChild);
                }
            }
        }
        return setDp[node][setFeature];
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