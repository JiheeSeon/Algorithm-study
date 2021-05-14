package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

class 최소비용구하기_1916 {
    int startV, endV, V;
    ArrayList<MainA1916.Edge>[] graph;
    int[] distance;

    public 최소비용구하기_1916(int startV, int endV, int V, ArrayList<MainA1916.Edge>[] graph) {
        this.startV = startV;
        this.endV = endV;
        this.V = V;
        this.graph = graph;

        distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startV] = 0;
    }

    int solution() {
        PriorityQueue<MainA1916.Edge> pq = new PriorityQueue<>();
        pq.offer(new MainA1916.Edge(startV, 0));

        MainA1916.Edge now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for(MainA1916.Edge next: graph[now.vertex]){
                if(distance[next.vertex] > distance[now.vertex] + next.weight){
                    distance[next.vertex] = distance[now.vertex] + next.weight;
                    pq.add(new MainA1916.Edge(next.vertex, distance[next.vertex]));
                }
            }
        }

        return distance[endV];
    }
}

class MainA1916{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        ArrayList<Edge>[] graph = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] tmp;
        for(int e = 0; e < E; e++){
            tmp = splitIntoIntArray(br.readLine());
            graph[tmp[0]].add(new Edge(tmp[1], tmp[2]));
        }
        tmp = splitIntoIntArray(br.readLine());
        System.out.println(new 최소비용구하기_1916(tmp[0], tmp[1], V, graph).solution());
    }

    static int[] splitIntoIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static class Edge implements Comparable<Edge>{
        int vertex, weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight == o.weight
                    ? Integer.compare(vertex, o.vertex)
                    : Integer.compare(weight, o.weight);
        }
    }
}