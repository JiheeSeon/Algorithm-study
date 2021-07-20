package Tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 네트워크연결_1922 {
    int V;

    public 네트워크연결_1922(int v) {
        V = v;
    }

    int solveInPrim(ArrayList<EdgeA1922_Prim>[] primGraph) {
        int ans = 0;
        final int startVertex = 1;

        Set<Integer> vertexSet = new HashSet<>();
        vertexSet.add(startVertex);
        PriorityQueue<EdgeA1922_Prim> pq = new PriorityQueue<>();
        pq.addAll(primGraph[startVertex]);

        EdgeA1922_Prim e;
        while (!pq.isEmpty() && vertexSet.size() < V) {
            e = pq.poll();
            if(vertexSet.contains(e.v)) continue;

            vertexSet.add(e.v); // 정점 추가
            ans += e.w; // 이번 간선의 가중치를 추가
            pq.addAll(primGraph[e.v]); // 다음에 갈 수 있는 목적지 추가
        }

        return ans;
    }

    int solveInKrukal(EdgeA1922_Krukal[] krukalGraph) {
        PriorityQueue<EdgeA1922_Krukal> pq = Arrays.stream(krukalGraph).collect(Collectors.toCollection(PriorityQueue::new));

        int ans = 0;
        int selectedEdgeCnt = 0;
        int[] parent = IntStream.rangeClosed(0, V).toArray();
        EdgeA1922_Krukal e;

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

class EdgeA1922_Krukal implements Comparable<EdgeA1922_Krukal>{
    int v1, v2, w;

    public EdgeA1922_Krukal(int[] info) {
        this.v1 = info[0];
        this.v2 = info[1];
        this.w = info[2];
    }

    @Override
    public int compareTo(EdgeA1922_Krukal o) {
        return Integer.compare(w, o.w);
    }
}

class EdgeA1922_Prim implements Comparable<EdgeA1922_Prim>{
    int v, w;

    public EdgeA1922_Prim(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(EdgeA1922_Prim o) {
        return Integer.compare(w, o.w);
    }
}

class MainA1922{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

//        runWithKrukal(V, E, br);
        runWithPrim(V, E, br);
    }

    static void runWithKrukal(int V, int E, BufferedReader br) throws IOException{
        EdgeA1922_Krukal[] edges = new EdgeA1922_Krukal[E];

        for (int e = 0; e < E; e++) {
            edges[e] = new EdgeA1922_Krukal(strToIntArr(br.readLine()));
        }
        System.out.println(new 네트워크연결_1922(V).solveInKrukal(edges));
    }

    static void runWithPrim(int V, int E, BufferedReader br) throws IOException{
        ArrayList<EdgeA1922_Prim>[] primGraph = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) {
            primGraph[v] = new ArrayList<>();
        }
        int[] tmp;
        for (int e = 0; e < E; e++) {
            tmp = strToIntArr(br.readLine());
            primGraph[tmp[0]].add(new EdgeA1922_Prim(tmp[1], tmp[2]));
            primGraph[tmp[1]].add(new EdgeA1922_Prim(tmp[0], tmp[2]));
        }
        System.out.println(new 네트워크연결_1922(V).solveInPrim(primGraph));
    }

    static int[] strToIntArr(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}