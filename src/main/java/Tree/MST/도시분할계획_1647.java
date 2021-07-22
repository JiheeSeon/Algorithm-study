package Tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
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

        while(ptr < V - 1 && !pq.isEmpty()){
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

class 도시분할계획_1647_Prim {
    int V;
    ArrayList<EdgeA1647_Prim> [] graph;

    public 도시분할계획_1647_Prim(int v, ArrayList<EdgeA1647_Prim>[] graph) {
        V = v;
        this.graph = graph;
    }

    int solve() {
        int max = -1;
        int ans = 0;

        Set<Integer> visitedVSet = new HashSet<>();
        visitedVSet.add(1);

        PriorityQueue<EdgeA1647_Prim> pq = new PriorityQueue<>();
        pq.addAll(graph[1]);

        EdgeA1647_Prim now;
        while (!pq.isEmpty() && visitedVSet.size() < V){
            now = pq.poll();
            if(visitedVSet.contains(now.v)) continue;

            visitedVSet.add(now.v);
            pq.addAll(graph[now.v]);
            if(now.w > max) max = now.w;
            ans += now.w;
        }

        return ans - max;
    }
}

class EdgeA1647_Prim implements Comparable<EdgeA1647_Prim> {
    int v, w;

    public EdgeA1647_Prim(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(EdgeA1647_Prim o) {
        return Integer.compare(w, o.w);
    }
}

class MainA1647{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] tmp;
    static int V, E;

    public static void main(String[] args) throws IOException {
        tmp = strToIntArr(br.readLine());
        V = tmp[0]; E = tmp[1];
        runWithKrukal();

        runWithPrim();
    }

    static void runWithPrim() throws IOException{
        ArrayList<EdgeA1647_Prim>[] graph = new ArrayList[V + 1];
        for(int v = 1; v <= V; v++)
            graph[v] = new ArrayList<>();

        for(int e = 0; e < E; e++){
            tmp = strToIntArr(br.readLine());
            graph[tmp[0]].add(new EdgeA1647_Prim(tmp[1], tmp[2]));
            graph[tmp[1]].add(new EdgeA1647_Prim(tmp[0], tmp[2]));
        }
        System.out.println(new 도시분할계획_1647_Prim(V, graph).solve());
    }

    static void runWithKrukal() throws IOException{
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