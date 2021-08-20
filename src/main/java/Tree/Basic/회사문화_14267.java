package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class 회사문화_14267 {
    int V;
    ArrayList<Integer>[] graph;
    int[][] praises;
    int[] result;
    Map<Integer, Integer> praiseMap;

    public 회사문화_14267(int v, ArrayList<Integer>[] graph, int[][] praises) {
        V = v;
        this.graph = graph;
        this.praises = praises;
        result = new int[V + 1];
        praiseMap = new HashMap<>();
    }

    String solve() {
        for (int[] praise : praises)
            praiseMap.put(praise[0], praiseMap.getOrDefault(praise[1], 0));

        StringBuilder stb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            stb.append(result[i]).append(" ");
        }
        return stb.toString();
    }

    void dfs(int node, int praisePoint) {
        result[node] += praisePoint;

        for (int child : graph[node]) {
            dfs(child, praisePoint);
        }
    }
}

class MainA14267{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int praiseN = tmp[1];

        tmp = InputProcessor.strToIntArr(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) graph[v] = new ArrayList<>();
        for (int v = 1; v <= V; v++) {
            if(tmp[v - 1] == -1) continue;
            graph[tmp[v - 1]].add(v);
        }

        int[][] praises = new int[praiseN][2];
        for (int p = 0; p < praiseN; p++)
            praises[p] = InputProcessor.strToIntArr(br.readLine());

        System.out.println(new 회사문화_14267(V, graph, praises).solve());
    }
}