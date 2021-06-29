package Graph.Archive.ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

class 최단경로_1753_2nd {
    final static int INF = Integer.MAX_VALUE;

    int V, E;
    int src;
    int[] distance;
    boolean[] check;
    ArrayList<Edge_1753_2nd>[] graph;

    public 최단경로_1753_2nd(int V, int E, int src, ArrayList<Edge_1753_2nd>[] graph) {
        this.V = V;
        this.E = E;
        this.src = src;
        this.graph = graph;
        check = new boolean[V + 1];
        distance = new int[V + 1];
        Arrays.fill(distance, INF);
    }

    String getAns() {
        dijkstra();
        StringBuilder stb = new StringBuilder();
        for(int i = 1; i <= V; i++)
            stb.append(distance[i] == INF ? "INF" : distance[i]).append("\n");
        return stb.toString();
    }

    void dijkstra() {
        PriorityQueue<Edge_1753_2nd> pq = new PriorityQueue<>();
        distance[src] = 0;
        pq.offer(new Edge_1753_2nd(src, distance[src]));

        Edge_1753_2nd now;
        while (!pq.isEmpty()) {
            now = pq.poll();
            if(check[now.dst]) continue;

            check[now.dst] = true;
            for (Edge_1753_2nd next : graph[now.dst]) {
                if (distance[next.dst] > distance[now.dst] + next.cost) {
                    distance[next.dst] = distance[now.dst] + next.cost;
                    pq.add(new Edge_1753_2nd(next.dst, distance[next.dst]));
                }
            }
        }
    }
}

class Edge_1753_2nd implements Comparable<Edge_1753_2nd>{
    int dst, cost;

    public Edge_1753_2nd(int dst, int cost) {
        this.dst = dst;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge_1753_2nd o) {
        return Integer.compare(cost, o.cost);
    }
}

class MainA1753_2nd{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];
        int src = Integer.parseInt(br.readLine());

        ArrayList<Edge_1753_2nd>[] graph = new ArrayList[V + 1];
        for(int v = 1; v <= V; v++)
            graph[v] = new ArrayList<>();

        for (int e = 0; e < E; e++) {
            tmp = strToIntArr(br.readLine());
            graph[tmp[0]].add(new Edge_1753_2nd(tmp[1], tmp[2]));
        }

        System.out.print(new 최단경로_1753_2nd(V, E, src, graph).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
