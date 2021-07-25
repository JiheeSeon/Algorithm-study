package Tree.MST;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/*
TODO
KruskalEdgeDW 추가
Util.InputProcessor 추가
Point 추가
*/

class 행성연결_16398 {
    int V; int[] parent;
    ArrayList<KruskalEdgeIW> edges;
    long ans;

    public 행성연결_16398(int v, ArrayList<KruskalEdgeIW> edges) {
        V = v;
        this.edges = edges;
        parent = IntStream.rangeClosed(0, V).toArray();
        ans = 0;
    }

    long solve() {
        PriorityQueue<KruskalEdgeIW> pq = new PriorityQueue<>(edges);

        KruskalEdgeIW e;
        int cnt = 0;
        while (!pq.isEmpty() && cnt < V - 1) {
            e = pq.poll();
            if (!union(e.v1, e.v2))  continue;

            ans += e.w;
            cnt++;
        }
        return ans;
    }

    boolean union(int a, int b){
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return false;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int a){
        return a == parent[a] ? a : (parent[a] = find(parent[a]));
    }
}

class MainA16398{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        ArrayList<KruskalEdgeIW> edges = new ArrayList<>();
        int[] tmp;
        for(int i = 0; i < V; i++){
            tmp = InputProcessor.strToIntArr(br.readLine());
            for(int j = i + 1; j <= V; j++){
                edges.add(new KruskalEdgeIW(i + 1, j + 1, tmp[j]));
            }
        }

        System.out.println(new 행성연결_16398(V, edges).solve());
    }
}