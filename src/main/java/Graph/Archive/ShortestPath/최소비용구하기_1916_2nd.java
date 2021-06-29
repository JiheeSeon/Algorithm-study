package Graph.Archive.ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

/*
distance의 초기값, INF

INF는 항상 (간선 가중치의 최댓값) * (정점 개수 - 1) 보다 큰 값을 사용해야 합니다.
거리가 가장 멀어지는 경우를 생각해 보면, 1-2-3-4-5-6-...-V
이렇게 일직선으로 모든 간선이 최대 가중치를 가지고 연결되어 있을 때
총 V-1개의 간선을 전부 차례대로 지나가야 하기 때문입니다.
*/
class 최소비용구하기_1916_2nd {
    int V, E;
    int src, dst;
    ArrayList<Edge_1916_2nd>[] graph;

    int[] distance;
    boolean[] check;

    public 최소비용구하기_1916_2nd(int v, int e, int src, int dst, ArrayList<Edge_1916_2nd>[] graph) {
        V = v;
        E = e;
        this.src = src;
        this.dst = dst;
        this.graph = graph;

        distance = new int[V + 1];
        check = new boolean[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
    }

    void dijkstra(){
        PriorityQueue<Edge_1916_2nd> pq = new PriorityQueue<>();
        pq.add(new Edge_1916_2nd(src, 0)); // start~start : 0
        distance[src] = 0;

        Edge_1916_2nd now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            if(check[now.vertex]) continue;

            check[now.vertex] = true;

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

        System.out.println(new 최소비용구하기_1916_2nd(V, E, src, dst, graph).getAns());
    }

    static int[] strToIntArr(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
