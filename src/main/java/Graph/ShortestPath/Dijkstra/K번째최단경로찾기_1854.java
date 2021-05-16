package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class K번째최단경로찾기_1854 {
    int V, K;
    Set<Integer> getFromOther;
    ArrayList<EdgeA1854>[] graph;
    ArrayList<Integer>[] record;

    public K번째최단경로찾기_1854(int V, int K, Set<Integer> getFromOther, ArrayList<EdgeA1854>[] graph){
        this.V = V;
        this.K = K;
        this.getFromOther = getFromOther;
        this.graph = graph;

        record = new ArrayList[V + 1];
        for(int v = 0; v < V + 1; v++)
            record[v] = new ArrayList<>();
    }

    String solution() {
        PriorityQueue<EdgeA1854> pq = new PriorityQueue<>();
        pq.offer(new EdgeA1854(1, 0));

        EdgeA1854 now;
        int[] ks = new int[V + 1];
        Set<Integer> set = new HashSet<>(getFromOther);

        while (!pq.isEmpty()) {
            now = pq.poll();

            for(EdgeA1854 next: graph[now.vertex]){
                record[next.vertex].add(now.weight + next.weight);
                if(++ks[next.vertex] > K) set.add(next.vertex);
                if(set.size() == V) break;
                pq.add(new EdgeA1854(next.vertex, now.weight + next.weight));
            }
        }

        StringBuilder stb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            Collections.sort(record[i]);
            if(record[i].size() < K){
                if(K == 1 && i == 1) stb.append("0").append("\n");
                else stb.append("-1").append("\n");
            }
            else stb.append(record[i].get(K - 1)).append("\n");
        }
        return stb.toString();
    }

    int[] dijkstra(int[] distance){
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        PriorityQueue<EdgeA1854> pq = new PriorityQueue<>();
        pq.offer(new EdgeA1854(1, 0));

        EdgeA1854 now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for(EdgeA1854 next: graph[now.vertex]){
                if(distance[next.vertex] > distance[now.vertex] + next.weight){
                    distance[next.vertex] = distance[now.vertex] + next.weight;
                    pq.add(new EdgeA1854(next.vertex, distance[next.vertex]));
                }
            }
        }
        return distance;
    }

}
class MainA1854{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = splitIntoIntArray(br.readLine());
        int V = tmp[0]; int E = tmp[1]; int K = tmp[2];

        ArrayList<EdgeA1854>[] graph = new ArrayList[V + 1];
        for(int v = 0; v < V + 1; v++)
            graph[v] = new ArrayList<>();

        Set<Integer> getFromOther = IntStream.rangeClosed(1, V).boxed().collect(Collectors.toCollection(HashSet::new));

        for (int e = 0; e < E; e++) {
            tmp = splitIntoIntArray(br.readLine());
            graph[tmp[0]].add(new EdgeA1854(tmp[1], tmp[2]));
            getFromOther.remove(tmp[1]);
        }

        System.out.print(new K번째최단경로찾기_1854(V, K, getFromOther, graph).solution());
    }

    static int[] splitIntoIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}

class EdgeA1854 implements Comparable<EdgeA1854>{
    int vertex;
    int weight;

    public EdgeA1854(int endV, int weight) {
        this.vertex = endV;
        this.weight = weight;
    }

    @Override
    public int compareTo(EdgeA1854 o) {
        return Integer.compare(weight, o.weight);
    }
}