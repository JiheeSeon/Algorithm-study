package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class 트리와쿼리_15681 {
    int V, R;
    ArrayList<Integer>[] tree;
    int[] queryDp;

    public 트리와쿼리_15681(int V, int R, ArrayList<Integer>[] graph) {
        this.V = V;
        this.R = R;
        tree = new ArrayList[V + 1];
        for(int v = 1; v <= V; v++) tree[v] = new ArrayList<>();
        setTree(R, new boolean[V + 1], graph);
        queryDp = new int[V + 1];
    }

    void setTree(int now, boolean[] check, ArrayList<Integer>[] graph) {
        check[now] = true;

        for (int child : graph[now]) {
            if(check[child]) continue;

            tree[now].add(child);
            setTree(child, check, graph);
        }
    }

    int getQueryAnswer(int u) {
        return (queryDp[u] != 0) ? queryDp[u] : solve(u);
    }

    int solve(int now) {
        if(queryDp[now] != 0) return queryDp[now];

        for (int child : tree[now]){
            queryDp[now] += solve(child);
        }
        queryDp[now]++;

        return queryDp[now];
    }
}

class MainA15681{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int R = tmp[1]; int Q = tmp[2];

        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) graph[v] = new ArrayList<>();

        for(int v = 0; v < V - 1; v++){
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        트리와쿼리_15681 solution = new 트리와쿼리_15681(V, R, graph);
        StringBuilder stb = new StringBuilder();

        for (int q = 0; q < Q; q++) {
            stb.append(solution.getQueryAnswer(Integer.parseInt(br.readLine()))).append("\n");
        }

        System.out.print(stb);
    }
}