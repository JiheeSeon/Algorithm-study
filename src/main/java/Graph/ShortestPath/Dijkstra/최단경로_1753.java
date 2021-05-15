package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

// Dijkstra Basic Problem

class 최단경로_1753 {
    int vertexN;
    int startV;
    ArrayList<MainA1753.Edge>[] graph;

    int[] distance;

    public 최단경로_1753(int vertexN, int startV, ArrayList<MainA1753.Edge>[] graph) {
        this.vertexN = vertexN;
        this.startV = startV;
        this.graph = graph;

        distance = new int[vertexN + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startV] = 0;
    }

    int[] solution(){
        PriorityQueue<MainA1753.Edge> pq = new PriorityQueue<>();
        pq.offer(new MainA1753.Edge(startV, 0));

        MainA1753.Edge now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for(MainA1753.Edge next: graph[now.vertex]){
                if(distance[next.vertex] > distance[now.vertex] + next.weight){
                    distance[next.vertex] = distance[now.vertex] + next.weight;
                    pq.add(new MainA1753.Edge(next.vertex, distance[next.vertex]));
                }
            }
        }
        return distance;
    }
}


class MainA1753{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmp = processIntoIntArray(br.readLine());
        int vertexN = tmp[0]; int edgeN = tmp[1];
        int startV = Integer.parseInt(br.readLine());

        ArrayList<Edge>[] graph = new ArrayList[vertexN + 1];
        for(int i = 0; i < vertexN + 1; i++)
            graph[i] = new ArrayList<>();

        for (int e = 0; e < edgeN; e++) {
            tmp = processIntoIntArray(br.readLine());
            graph[tmp[0]].add(new Edge(tmp[1], tmp[2]));
        }

        int[] result = new 최단경로_1753(vertexN, startV, graph).solution();
        StringBuilder stb = new StringBuilder();

        for (int i = 1; i < result.length; i++) {
            stb.append(result[i] == Integer.MAX_VALUE ? "INF" : result[i]).append("\n");
        }
        System.out.print(stb);
    }

    static int[] processIntoIntArray(String s) {
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
