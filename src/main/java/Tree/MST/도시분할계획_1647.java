package Tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 도시분할계획_1647_Kruskal {
    int V;
    EdgeA1647_Krukal[] edges;

    public 도시분할계획_1647_Kruskal(int v, EdgeA1647_Krukal[] edges) {
        V = v;
        this.edges = edges;
    }

    int solve() {
        int max = -1, ptr = 0;
        int ans = 0;
        EdgeA1647_Krukal now;
        int[] parent = IntStream.rangeClosed(0, V).toArray();
        PriorityQueue<EdgeA1647_Krukal> pq = Arrays.stream(edges).collect(Collectors.toCollection(PriorityQueue::new));

        while(ptr < V && !pq.isEmpty()){
            now = pq.poll();
            if(!union(now.v1, now.v2, parent)) continue;

            if(now.w > max) max = now.w;
            ans += now.w;
            ptr++;
        }

        return ans - max;
    }

    boolean union(int a, int b, int[] parent){
        int pA = find(a, parent);
        int pB = find(b, parent);

        if(pA == pB) return false;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int a, int[] parent) {
        return a == parent[a] ? a : (parent[a] = find(parent[a], parent));
    }
}

class EdgeA1647_Krukal implements Comparable<EdgeA1647_Krukal>{
    int v1, v2, w;

    public EdgeA1647_Krukal(int[] info) {
        v1 = info[0];
        v2 = info[1];
        w = info[2];
    }

    @Override
    public int compareTo(EdgeA1647_Krukal o) {
        return Integer.compare(w, o.w);
    }
}

class MainA1647{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];
        EdgeA1647_Krukal[] edges = new EdgeA1647_Krukal[E];
        for(int e = 0; e < E; e++){
            edges[e] = new EdgeA1647_Krukal(strToIntArr(br.readLine()));
        }
        System.out.println(new 도시분할계획_1647_Kruskal(V, edges).solve());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}