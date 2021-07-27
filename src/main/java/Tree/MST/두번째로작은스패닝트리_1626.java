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
        // 1. MST를 구한다.
        PriorityQueue<KruskalEdgeIW> pq = new PriorityQueue<>(edges);

        int cnt = 0, ans = 0;
        KruskalEdgeIW e;

        // MST에서 선택된 그래프 정보를 넣어놓음.
        graph = new ArrayList[V + 1];
        for(int v = 1; v <= V; v++) graph[v] = new ArrayList<>();

        KruskalEdgeIW cycleEdge = new KruskalEdgeIW(0, 0, Integer.MAX_VALUE);
        int val;

        while (cnt < V - 1) {
            if(pq.isEmpty()) return -1; // MST가 존재하지 않는 경우 -1 출력

            e = pq.poll();

            // 엣지를 연결할 경우 사이클이 생성
            if(!union(e.v1, e.v2)){
                // cycle edge를 추가할 때 경로에 있는
                val = e.w - dfs(e.v1, e.v2, e.w,-1, new boolean[V + 1]);
//                System.out.println("(" + e.v1 + ", "+ e.v2 + ")" + " -> e.w = " + e.w + " val = " + val);
                if(val < cycleEdge.w)
                    cycleEdge = new KruskalEdgeIW(e.v1, e.v2, val);
                continue;
            }

            ans += e.w;
            cnt++;

            graph[e.v1].add(new KruskalEdgeIW(e.v1, e.v2, e.w));
            graph[e.v2].add(new KruskalEdgeIW(e.v2, e.v1, e.w));
        }

        // 2. 두번째로 작은 MST를 찾는다.
        // 2.1. 사이클 도는 엣지가 입력된 경우
        if (!cycleEdge.equals(new KruskalEdgeIW(0, 0, Integer.MAX_VALUE))) {
            ans += cycleEdge.w;
        }
        // 2.2 사이클 도는 엣지가 없이 한번에 MST가 된 경우
        else{
            if(pq.isEmpty()) return -1; // 두번째로 작은 MST가 없는 경우
            else{
                e = pq.poll();
                ans += (e.w - dfs(e.v1, e.v2, e.w,-1, new boolean[V + 1]));
            }
        }

        return ans;
    }

    // 기존 MST에서 주어진 dst로 갈 때 나오는 엣지 중 최대 가중치 값
    int dfs(int now, int dst, int upperBound, int max, boolean[] check) {
        if(check[now] || now == dst) return max;

        check[now] = true;

        int ret = max;

        int maxCost; // 하위 dfs를 돌려서 나온 엣지의 최대 가중치 값
        for (KruskalEdgeIW e : graph[now]) {
            maxCost = dfs(e.v2, dst, upperBound, e.w < upperBound ? Math.max(max, e.w) : max, check);
            if(max < maxCost && maxCost < upperBound) ret = maxCost;
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

/*
5 5
1 2 2
2 3 2
2 5 2
3 4 2
4 5 1
8
*/