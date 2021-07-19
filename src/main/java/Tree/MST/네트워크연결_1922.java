package Tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 네트워크연결_1922 {
    int V;

    public 네트워크연결_1922(int v) {
        V = v;
    }

    int solveInKrukal(EdgeA1922[] krukalGraph) {
        PriorityQueue<EdgeA1922> pq = Arrays.stream(krukalGraph).collect(Collectors.toCollection(PriorityQueue::new));

        int ans = 0;
        int selectedEdgeCnt = 0;
        int[] parent = IntStream.rangeClosed(0, V).toArray();
        EdgeA1922 e;

        while (!pq.isEmpty() && selectedEdgeCnt < V - 1){
            e = pq.poll();
            if (e.v1 == e.v2 || !union(e.v1, e.v2, parent)) continue;

            selectedEdgeCnt++;
            ans += e.w;
        }

        return ans;
    }

    boolean union(int a, int b, int[] parent){
        int pA = find(a, parent);
        int pB = find(b, parent);

        if(pA == pB) return false;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int a, int[] parent){
        return a == parent[a] ? a : (parent[a] = find(parent[a], parent));
    }
}

class EdgeA1922 implements Comparable<EdgeA1922>{
    int v1, v2, w;

    public EdgeA1922(int[] info) {
        this.v1 = info[0];
        this.v2 = info[1];
        this.w = info[2];
    }

    @Override
    public int compareTo(EdgeA1922 o) {
        return Integer.compare(w, o.w);
    }
}

class MainA1922{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        EdgeA1922[] edges = new EdgeA1922[E];

        for (int e = 0; e < E; e++) {
            edges[e] = new EdgeA1922(strToIntArr(br.readLine()));
        }
        System.out.println(new 네트워크연결_1922(V).solveInKrukal(edges));
    }

    static int[] strToIntArr(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}