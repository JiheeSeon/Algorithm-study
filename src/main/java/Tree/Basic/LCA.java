package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class LCA {
    int root;
    int V;
    ArrayList<Integer>[] tree;
    int[][] parent;
    int[] levels; // 각 노드의 레벨

    final int minLevel = 1;
    int treeH = -1;
    int lgOfMaxDistance;

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
        lgOfMaxDistance = (int)(Math.log10(treeH - minLevel) / Math.log10(2));
        setSparseTable();
//        displayInfo();
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
        for (int k = 1; k <= lgOfMaxDistance; k++) {
            for (int v = 1; v <= V; v++) {
                parent[k][v] = parent[k - 1][v] == -1 ? -1 : parent[k - 1][parent[k - 1][v]];
            }
        }
    }

    void displayInfo() {
        StringBuilder stb = new StringBuilder("\n");

        for (int k = 0; k <= lgOfMaxDistance; k++) {
            for (int i : parent[k]) {
                stb.append(i).append(" ");
            }
            stb.append("\n");
        }
        stb.append(Arrays.toString(levels));
        System.out.println(stb);

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

//        System.out.println("node1 = " + node1 + " (" + levels[node1] + ")");
//        System.out.println("node2 = " + node2 + " (" + levels[node2] + ")");

        if(node1 == node2) return node1;

        // opt 2> 1312ms -> 896ms
        int i = (int)(Math.log10(levels[node1] - minLevel) / Math.log10(2)) + 1;
        // 이진탐색 원리 차용
        while(--i >= 0){
//            System.out.println(i + " : " + node1 + "(" + parent[i][node1] + "), " + node2 + "(" + parent[i][node2] + ")" + " => "+ (parent[i][node1] == parent[i][node2]));
            if(parent[i][node1] == parent[i][node2]) continue;

            node1 = parent[i][node1];
            node2 = parent[i][node2];
        }
        return parent[0][node1];
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

/*
[Input]
67
64 59
67 63
63 57
62 57
66 61
60 56
56 61
57 53
59 55
59 65
54 49
47 53
53 58
50 46
50 56
55 49
46 52
41 47
49 45
48 41
46 51
33 46
32 45
44 31
43 29
42 28
41 28
39 25
38 24
36 22
35 21
20 34
9 20
21 9
32 19
22 11
12 23
12 24
14 25
16 26
19 8
40 25
37 22
31 19
27 16
28 16
33 20
29 17
30 19
18 8
17 8
16 7
6 15
14 6
13 6
12 5
5 11
4 9
4 10
2 4
2 5
2 6
3 8
7 3
1 2
3 1
6
67 26
66 34
52 40
9 19
48 47
60 1

[Output]
16
20
2
1
41
1
*/