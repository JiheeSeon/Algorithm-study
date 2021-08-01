package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class LCA_11437 {
    int V;
    ArrayList<Integer>[] graph;
    int[][] lcaPairs;
    int[] levels;
    int[] parent;

    public LCA_11437(int v, ArrayList<Integer>[] graph, int[][] lcaPairs) {
        V = v;
        this.graph = graph;
        this.lcaPairs = lcaPairs;
        levels = new int[V + 1];
        parent = new int[V + 1];
    }

    void init() {
        topDownDfs(1, 0, 1, new boolean[V + 1]);
    }

    void topDownDfs(int cNode, int pNode, int level, boolean[] check) {
        check[cNode] = true;

        levels[cNode] = level;
        parent[cNode] = pNode;

        for (int next : graph[cNode]) {
            if(check[next]) continue;

            topDownDfs(next, cNode, level + 1, check);
        }
    }

    String solve() {
        init();

        StringBuilder stb = new StringBuilder();
        for (int[] lcaPair : lcaPairs)
            stb.append(getLCA(lcaPair[0], lcaPair[1])).append("\n");

        return stb.toString();
    }

    int getLCA(int a, int b) {
        int levelA = levels[a];
        int levelB = levels[b];

        // adjust level
        while (levelA > levelB) {
            a = parent[a];
            levelA--;
        }

        while (levelA < levelB){
            b = parent[b];
            levelB--;
        }

        int pA = a, pB = b;
        while(pA != pB){
            pA = parent[pA];
            pB = parent[pB];
        }
        return pA;
    }
}

class MainA11437{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) {
            graph[v] = new ArrayList<>();
        }

        int[] tmp;
        for (int e = 0; e < V - 1; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        int N = Integer.parseInt(br.readLine());
        int[][] lcaPairs = new int[N][2];
        for(int i = 0; i < N; i++) {
            lcaPairs[i] = InputProcessor.strToIntArr(br.readLine());
        }

        System.out.print(new LCA_11437(V, graph, lcaPairs).solve());
    }
}