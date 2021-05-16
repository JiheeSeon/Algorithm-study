package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class K번째최단경로찾기_1854 {
    int[] dst;
    int V, K;
    ArrayList<EdgeA1854>[] graph;
    ArrayList<Integer>[] record;

    public K번째최단경로찾기_1854(int V, int K, ArrayList<EdgeA1854>[] graph){
        this.V = V;
        this.K = K;
        this.graph = graph;
        dst = new int[V + 1];
        Arrays.fill(dst, Integer.MAX_VALUE);
        dst[1] = 0;
        record = new ArrayList[V + 1];
        for(int v = 0; v < V + 1; v++)
            record[v] = new ArrayList<>();
    }

    void dijkstra() {
        PriorityQueue<EdgeA1854> pq = new PriorityQueue<>();
        Set<EdgeA1854> set = new HashSet<>();
        set.add(new EdgeA1854(1, 1, 0));
        pq.offer(new EdgeA1854(1, 1, 0));

        EdgeA1854 now;
        int[] ks = new int[V + 1];

        ArrayList<Integer> path;

        while (!pq.isEmpty()) {
            now = pq.poll();

            for(EdgeA1854 next: graph[now.endV]){
                record[next.endV].add(dst[now.endV] + next.weight);

                if(dst[next.endV] > dst[now.endV] + next.weight){
                    dst[next.endV] = dst[now.endV] + next.weight;
                    ks[next.endV]++;
                }
//                ks[next.endV]++;

                EdgeA1854 edgeA1854;
                if(ks[next.endV] < K) {
                    edgeA1854 = new EdgeA1854(now.endV, next.endV, dst[now.endV] + next.weight);
                    if(!set.contains(edgeA1854)) {
                        pq.add(edgeA1854);
                        set.add(edgeA1854);
                    }
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            Collections.sort(record[i]);
            System.out.print(i + " : ");
            System.out.println(record[i]);
        }
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

        for (int e = 0; e < E; e++) {
            tmp = splitIntoIntArray(br.readLine());
            graph[tmp[0]].add(new EdgeA1854(tmp[0], tmp[1], tmp[2]));
        }

        new K번째최단경로찾기_1854(V, K, graph).dijkstra();
    }

    static int[] splitIntoIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}

class EdgeA1854 implements Comparable<EdgeA1854>{
    int startV, endV;
    int weight;

    public EdgeA1854(int startV, int endV, int weight) {
        this.startV = startV;
        this.endV = endV;
        this.weight = weight;
    }

    @Override
    public int compareTo(EdgeA1854 o) {
        return Integer.compare(weight, o.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeA1854 edgeA1854 = (EdgeA1854) o;
        return startV == edgeA1854.startV && endV == edgeA1854.endV && weight == edgeA1854.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startV, endV, weight);
    }
}