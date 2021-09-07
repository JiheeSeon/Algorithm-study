package CramForCodingTest.SampleCode;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Dijkstra {
    static final int INF = Integer.MAX_VALUE;
    int V, E, startV;
    ArrayList<DijkstraEdge>[] graph;
    int[] dist;

    public Dijkstra(int v, int e, int startV, ArrayList<DijkstraEdge>[] graph) {
        V = v;
        E = e;
        this.startV = startV;
        this.graph = graph;
        dist = new int[V + 1];
        Arrays.fill(dist, INF);
    }

    String solve() {
        Set<Integer> visited = new HashSet<>();

        PriorityQueue<DijkstraEdge> pq = new PriorityQueue<>();
        pq.add(new DijkstraEdge(startV, 0));
        dist[startV] = 0;

//        int cnt = 0; // 1644 ms -> 1364 ms
        DijkstraEdge now;

        // 사실상 정점의 개수만큼만 반복하면 됨.
        while (visited.size() < V && !pq.isEmpty()) {
            now = pq.poll();
            // nowEdge.vertex : 거리 갱신의 주체 (이미 걔까지의 최단거리는 정해짐)
            // nowEdge.weight는 사실상 사용되지 않음. (어차피 앞서 반영)

            if(visited.contains(now.vertex)) continue;
            visited.add(now.vertex);

            for (DijkstraEdge connected : graph[now.vertex]) {
                if(!visited.contains(connected.vertex) && dist[connected.vertex] > dist[now.vertex] + connected.weight){
                    dist[connected.vertex] = dist[now.vertex] + connected.weight;
                    pq.add(new DijkstraEdge(connected.vertex, dist[connected.vertex]));
                }
            }
        }

        StringBuilder stb = new StringBuilder();
        for (int v = 1; v <= V; v++) stb.append(dist[v] == INF ? "INF" : dist[v]).append("\n");

        return stb.toString();
    }
}

class DijkstraEdge implements Comparable<DijkstraEdge>{
    int vertex;
    int weight;

    public DijkstraEdge(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(DijkstraEdge o) {
        return Integer.compare(weight, o.weight);
    }

    @Override
    public String toString() {
        return vertex + " [" + weight + ']';
    }
}

class DijkstraApp{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];
        int startV = Integer.parseInt(br.readLine());

        ArrayList<DijkstraEdge>[] graph = new ArrayList[V + 1];
        for(int v = 1; v <= V; v++) graph[v] = new ArrayList<>();

        for (int e = 0; e < E; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(new DijkstraEdge(tmp[1], tmp[2]));
        }

        System.out.print(new Dijkstra(V, E, startV, graph).solve());
    }
}