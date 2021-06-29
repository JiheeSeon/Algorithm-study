package Graph.Archive.ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

class 최단경로_1916_2nd {
    int V, E;
    int src, dst;
    ArrayList<Edge_1916_2nd>[] graph;

    int[] distance;

    public 최단경로_1916_2nd(int v, int e, int src, int dst, ArrayList<Edge_1916_2nd>[] graph) {
        V = v;
        E = e;
        this.src = src;
        this.dst = dst;
        this.graph = graph;

        distance = new int[V + 1];
        Arrays.fill(distance, (int)1e5 + 1);
    }

    void dijkstra(){
        PriorityQueue<Edge_1916_2nd> pq = new PriorityQueue<>();
        pq.add(new Edge_1916_2nd(src, 0));
        distance[src] = 0;

        Edge_1916_2nd now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for(Edge_1916_2nd next: graph[now.vertex]){
                if(distance[next.vertex] > distance[now.vertex] + next.cost){
                    distance[next.vertex] = distance[now.vertex] + next.cost;
                    pq.add(new Edge_1916_2nd(next.vertex, distance[next.vertex]));
                }
            }
        }
    }

    int getAns() {
        dijkstra();
        return distance[dst];
    }
}

class Edge_1916_2nd implements Comparable<Edge_1916_2nd>{
    int vertex;
    int cost;

    public Edge_1916_2nd(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge_1916_2nd o) {
        return cost == o.cost
                ? Integer.compare(vertex, o.vertex)
                : Integer.compare(cost, o.cost);
    }
}
class MainA1916_2nd {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        ArrayList<Edge_1916_2nd>[] graph= new ArrayList[V + 1];
        for(int v = 1; v <= V; v++)
            graph[v] = new ArrayList<>();

        int[] tmp;
        for(int e = 0; e < E; e++){
            tmp = strToIntArr(br.readLine());
            graph[tmp[0]].add(new Edge_1916_2nd(tmp[1], tmp[2]));
        }
        int[] SE = strToIntArr(br.readLine());
        int src = SE[0]; int dst = SE[1];

        System.out.println(new 최단경로_1916_2nd(V, E, src, dst, graph).getAns());
    }

    static int[] strToIntArr(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
