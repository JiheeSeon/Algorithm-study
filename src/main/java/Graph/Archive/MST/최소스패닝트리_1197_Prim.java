package Graph.Archive.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

class 최소스패닝트리_1197_Prim {
    int V, E;
    Edge_1197_Prim startEdge;
    ArrayList<Edge_1197_Prim>[] graph;

    boolean[] check;
    int totalCost = 0;

    public 최소스패닝트리_1197_Prim(int v, int e, ArrayList<Edge_1197_Prim>[] graph) {
        V = v;
        E = e;
        this.graph = graph;
        check = new boolean[V + 1];
    }

    int getAns() {
//        Set<Integer> availableVertex = IntStream.rangeClosed(1, V).boxed().collect(Collectors.toCollection(HashSet::new));

        PriorityQueue<Edge_1197_Prim> pq = new PriorityQueue<>();
        pq.add(new Edge_1197_Prim(1, 0));

        Edge_1197_Prim now;
        int cnt = 0;

        while (!pq.isEmpty() && cnt < V) {
            now = pq.poll();

            if (check[now.dst]) continue;

            System.out.println("now.dst = " + now.dst + " cnt = " + cnt);
            check[now.dst] = true;
            cnt++;
            totalCost += now.cost;
            pq.addAll(graph[now.dst]);
        }
        return totalCost;
    }
}

class Edge_1197_Prim implements Comparable<Edge_1197_Prim>{
    int dst, cost;

    public Edge_1197_Prim(int dst, int cost) {
        this.dst = dst;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge_1197_Prim o) {
        return Integer.compare(cost, o.cost);
    }
}

class MainA1197_Prim{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];

        ArrayList<Edge_1197_Prim>[] graph = new ArrayList[V + 1];
        for(int v = 1; v <= V; v++)
            graph[v] = new ArrayList<>();
        Edge_1197_Krukal[] edges = new Edge_1197_Krukal[E];

        for(int e = 0; e < E; e++) {
            tmp = strToIntArr(br.readLine());
            graph[tmp[0]].add(new Edge_1197_Prim(tmp[1], tmp[2]));
        }
        System.out.println(new 최소스패닝트리_1197_Prim(V, E, graph).getAns());
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}