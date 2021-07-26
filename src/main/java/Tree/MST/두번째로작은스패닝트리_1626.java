package Tree.MST;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class 두번째로작은스패닝트리_1626 {
    int V;
    ArrayList<KruskalEdgeIW> edges;
    ArrayList<KruskalEdgeIW>[] graph;
    int[] parent;

    public 두번째로작은스패닝트리_1626(int v, ArrayList<KruskalEdgeIW> edges) {
        V = v;
        this.edges = edges;
        parent = IntStream.rangeClosed(0, V).toArray();
    }

    int solve() {
        PriorityQueue<KruskalEdgeIW> pq = new PriorityQueue<>(edges);

        int cnt = 0, ans = 0;
        KruskalEdgeIW e;

        graph = new ArrayList[V + 1];
        for(int v = 1; v <= V; v++) graph[v] = new ArrayList<>();

        KruskalEdgeIW cycleEdge = new KruskalEdgeIW(0, 0, Integer.MAX_VALUE);
        int val;

        while (cnt < V - 1) {
            if(pq.isEmpty()) return -1;

            e = pq.poll();

            if(!union(e.v1, e.v2)){
                val = e.w - dfs(e.v1, e.v2, -1, new boolean[V + 1]);
                if(val < cycleEdge.w) cycleEdge = new KruskalEdgeIW(e.v1, e.v2, val);
                continue;
            }

            ans += e.w;
            cnt++;

            graph[e.v1].add(new KruskalEdgeIW(e.v1, e.v2, e.w));
            graph[e.v2].add(new KruskalEdgeIW(e.v2, e.v1, e.w));
        }

        if (!(cycleEdge.v1 == 0 && cycleEdge.v2 == 0 && cycleEdge.w == Integer.MAX_VALUE)) {
            ans += cycleEdge.w;
        }else{
            if(pq.isEmpty()) return -1; // 두번째 MST는 없는 경우
            else{
                e = pq.poll();
                ans += (e.w - dfs(e.v1, e.v2, -1, new boolean[V + 1]));
            }
        }

        return ans;
    }

    int dfs(int now, int dst, int max, boolean[] check) {
        if(check[now] || now == dst) return max;

        check[now] = true;

        int ret = max;
        for (KruskalEdgeIW e : graph[now]) {
            ret = Math.max(max, dfs(e.v2, dst, Math.max(max, e.w), check));
        }

        return ret;
    }

    boolean union(int a, int b){
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return false;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int a) {
        return parent[a] == a ? a : (parent[a] = find(parent[a]));
    }
}

class MainA1626{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];

        ArrayList<KruskalEdgeIW> edges = new ArrayList<>();
        for (int e = 0; e < E; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            edges.add(new KruskalEdgeIW(tmp[0], tmp[1], tmp[2]));
        }

        System.out.println(new 두번째로작은스패닝트리_1626(V, edges).solve());
    }
}