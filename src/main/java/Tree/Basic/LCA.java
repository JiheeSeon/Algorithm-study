package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class LCA {
    int root;
    int V;
    ArrayList<Integer>[] tree;
    int[][] parent;
    int[] levels; // 각 노드의 레벨

    final int minLevel = 1;
    int treeH = -1;
    int maxLGH;

    public LCA(int V, int root, ArrayList<Integer>[] tree) {
        this.V = V;
        this.root = root;
        this.tree = tree;
        parent = new int[(int)(Math.log10(V) / Math.log10(2)) + 1][V + 1];
        levels = new int[V + 1];
    }

    void init() {
        parent[0][root] = -1;

        topdownDfs(root, 1, new boolean[V + 1]);
        maxLGH = (int)(Math.log10(treeH - minLevel) / Math.log10(2));
        setSparseTable();
    }

    private void topdownDfs(int now, int level, boolean[] check) {
        check[now] = true;
        levels[now] = level;
        treeH = Math.max(treeH, level);

        for (int child : tree[now]) {
            if(check[child]) continue;

            parent[0][child] = now;
            topdownDfs(child, level + 1, check);
        }
    }

    private void setSparseTable() {
        for (int k = 1; k <= maxLGH; k++) {
            for (int v = 1; v <= V; v++) {
                parent[k][v] = parent[k - 1][v] == -1 ? -1 : parent[k - 1][parent[k - 1][v]];
            }
        }
    }

    void displaySparseTable() {
        StringBuilder stb = new StringBuilder("\n");

        for (int k = 0; k <= maxLGH; k++) {
            for (int i : parent[k]) {
                stb.append(i).append(" ");
            }
            stb.append("\n");
        }
        System.out.print(stb);
    }

    int LCA(int node1, int node2) {
        // 더 깊이 위치한 노드를 무조건 node2 로 세팅한다.
        if(levels[node1] > levels[node2]){
            int tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        // opt 1> Sparse table -> 같은 높이의 node2의 조상으로 대치한다.
        // time : 1668ms -> 1312ms
        int adjustK = levels[node2] - levels[node1];
        int powerIdx = (int)(Math.log10(adjustK) / Math.log10(2)) + 1;

        while (--powerIdx >= 0) {
            /*테크닉 익혀놓기 -> 최상의 자리부터 하나씩 내려오면서 체크*/
            if(parent[powerIdx][node2] == 0) return 1;

            if ((adjustK & (1 << powerIdx)) != 0) {
                node2 = parent[powerIdx][node2] == -1 ? -1 : parent[powerIdx][node2];
            }
        }

        if(node1 == node2) return node1;

        while (node1 != node2) {
            node1 = parent[0][node1];
            node2 = parent[0][node2];
        }
        return node1;
    }

    int solve(int a, int b) {
        return LCA(a, b);
    }
}

class LCAApplication{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp;
        ArrayList<Integer>[] graph;
        StringBuilder stb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int e = 0; e < N - 1; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        LCA lca = new LCA(N, 1, graph);
        lca.init();

        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            stb.append(lca.solve(tmp[0], tmp[1])).append("\n");
        }

        System.out.print(stb);
    }
}