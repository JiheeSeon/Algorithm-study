package Tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 행성터널_2887_Kruskal {
    int V;
    KruskalEdge[] edges;

    public 행성터널_2887_Kruskal(int v, KruskalEdge[] edges) {
        V = v;
        this.edges = edges;
    }

    int solve() {
        int ans = 0;
        int[] parent = IntStream.rangeClosed(0, V).toArray();
        PriorityQueue<KruskalEdge> pq = Arrays.stream(edges).collect(Collectors.toCollection(PriorityQueue::new));

        KruskalEdge e; int cnt = 0; // local variable
        while (!pq.isEmpty() && cnt < V - 1) {
            e = pq.poll();
            if(!union(e.v1, e.v2, parent)) continue;

            ans += e.w;
            cnt++;
        }

        return ans;
    }

    boolean union(int a, int b, int[] parent) {
        int pA = find(a, parent);
        int pB = find(b, parent);

        if(pA == pB) return false;

        if (pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int a, int[] parent) {
        return a == parent[a] ? a : (parent[a] = find(parent[a], parent));
    }
}

class PointA2887{
    int x, y, z;

    public PointA2887(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class MainA2887{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = V * (V - 1) / 2;
        KruskalEdge[] edges = new KruskalEdge[E];
        PointA2887[] points = new PointA2887[V];

        int[] tmp;
        for(int v = 0; v < V; v++){
            tmp = strToIntArr(br.readLine());
            points[v] = new PointA2887(tmp[0], tmp[1], tmp[2]);
        }

        int eIdx = 0, cost;
        for(int i = 0; i < V - 1; i++){
            for(int j = i + 1; j < V; j++){
                cost = Math.min(
                        Math.abs(points[i].z - points[j].z),
                        Math.min(
                                Math.abs(points[i].x - points[j].x), Math.abs(points[i].y - points[j].y)));
                edges[eIdx++] = new KruskalEdge(i + 1, j + 1, cost);
            }
        }

        System.out.println(new 행성터널_2887_Kruskal(V, edges).solve());
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}