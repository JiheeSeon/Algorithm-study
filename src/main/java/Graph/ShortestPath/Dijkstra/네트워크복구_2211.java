package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

class 네트워크복구_2211 {
    int V;
    ArrayList<EdgeA2211>[] graph;
    int[] dist, prev;

    public 네트워크복구_2211(int v, ArrayList<EdgeA2211>[] graph) {
        V = v;
        this.graph = graph;
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        prev = new int[V + 1];
    }

    String solution() {
        PriorityQueue<EdgeA2211> pq = new PriorityQueue<>();
        pq.offer(new EdgeA2211(1, 0));

        EdgeA2211 now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for (EdgeA2211 next : graph[now.vertex]) {
                if(dist[next.vertex] > dist[now.vertex] + next.weight){
                    dist[next.vertex] = dist[now.vertex] + next.weight;
                    prev[next.vertex] = now.vertex;
                    pq.add(new EdgeA2211(next.vertex, dist[next.vertex]));
                }
            }
        }


        StringBuilder stb = new StringBuilder();
        Set<E> set = new HashSet<>();
        int node;
        E e;

        int cnt = 0;
        for(int i = 2; i <= V; i++){
            node = i;

            while(node != 1){
                e = new E(node, prev[node]);
                if(set.contains(e)) break;
                set.add(e);
                stb.append(node).append(" ").append(prev[node]).append("\n");
                cnt++;
                node = prev[node];
            }
        }
        stb.insert(0, "\n").insert(0, cnt);
        return stb.toString();
    }
}
class MainA2211{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = splitIntoIntArray(br.readLine());
        int V = tmp[0]; int E = tmp[1];

        ArrayList<EdgeA2211>[] graph = new ArrayList[V + 1];
        for(int v = 0; v < V + 1; v++)
            graph[v] = new ArrayList<>();

        for(int e = 0; e < E; e++){
            tmp = splitIntoIntArray(br.readLine());
            graph[tmp[0]].add(new EdgeA2211(tmp[1], tmp[2]));
            graph[tmp[1]].add(new EdgeA2211(tmp[0], tmp[2]));
        }

        System.out.print(new 네트워크복구_2211(V, graph).solution());
    }

    static int[] splitIntoIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
class EdgeA2211 implements Comparable<EdgeA2211>{
    int vertex, weight;

    public EdgeA2211(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(EdgeA2211 o) {
        return Integer.compare(weight, o.weight);
    }
}

class E{
    int start;
    int end;

    public E(int start, int end) {
        this.start = Math.min(start, end);
        this.end = Math.max(start, end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        E e = (E) o;
        return start == e.start && end == e.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "E{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}