package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class 미확인도착지_9370 {
    int V, S, G, H, gToH;
    int[] ends;
    ArrayList<EdgeA9370>[] graph;

    public 미확인도착지_9370(int v, int s, int g, int h, int gToH, int[] ends, ArrayList<EdgeA9370>[] graph) {
        V = v; S = s; G = g; H = h;
        this.gToH = gToH;
        this.ends = ends;
        this.graph = graph;

    }

    String solution() {
        int[] distS = new int[V + 1];
        Arrays.fill(distS, Integer.MAX_VALUE);
        distS[S] = 0;
        distS = dijkstra(S, distS);

        // S -> G -> H -> ?
        int[] distH = new int[V + 1];
        Arrays.fill(distH, Integer.MAX_VALUE);
        distH[H] = 0;
        distH = dijkstra(H, distH);

        // S -> H -> G -> ?
        int[] distG = new int[V + 1];
        Arrays.fill(distG, Integer.MAX_VALUE);
        distG[G] = 0;
        distG = dijkstra(G, distG);

        Map<Integer, Integer> res = new TreeMap<>();

        for(int end : ends){
            if(distS[G] != Integer.MAX_VALUE && distH[end] != Integer.MAX_VALUE)
                res.put(end, distS[G] + gToH + distH[end]);

            if(distS[H] != Integer.MAX_VALUE && distG[end] != Integer.MAX_VALUE)
                res.put(end, Math.min(res.getOrDefault(end, Integer.MAX_VALUE), distS[H] + gToH + distG[end]));
        }
        for(Map.Entry entry: res.entrySet())
            System.out.println(entry.getKey() + " -> "+ entry.getValue());

        return res.keySet().stream().map(String::valueOf).collect(Collectors.joining(" "));
//        return res.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(e-> String.valueOf(e.getKey())).collect(Collectors.joining(" "));
    }

    int[] dijkstra(int startV, int[] dist) {
        PriorityQueue<EdgeA9370> pq = new PriorityQueue<>();
        pq.offer(new EdgeA9370(startV, 0));

        EdgeA9370 now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for(EdgeA9370 next : graph[now.vertex]){
                if (dist[next.vertex] > dist[now.vertex] + next.weight) {
                    dist[next.vertex] = dist[now.vertex] + next.weight;
                    pq.add(new EdgeA9370(next.vertex, next.weight));
                }
            }
        }
        return dist;
    }


}
class MainA9370{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int t = 0;

        int[] tmp, ends;
        int V, E, endCandidateN, S, G, H, gToH;
        ArrayList<EdgeA9370>[] graph;

        StringBuilder stb = new StringBuilder();

        while(t++ < T) {
            // 교차로의 개수를 정점의 개수와 동일시해서 생각해도 될까?
            tmp = splitIntoIntArray(br.readLine());
            V = tmp[0]; E = tmp[1];
            endCandidateN = tmp[2];
            gToH = 0;

            ends = new int[endCandidateN];
            graph = new ArrayList[V + 1];
            for (int v = 0; v < V + 1; v++)
                graph[v] = new ArrayList<>();

            // 꼭 지나가야 하는 도로
            tmp = splitIntoIntArray(br.readLine());
            S = tmp[0]; G = tmp[1]; H = tmp[2];

            for (int e = 0; e < E; e++) {
                tmp = splitIntoIntArray(br.readLine());
                if((tmp[0] == G && tmp[1] == H) || (tmp[1] == G && tmp[0] == H)) gToH = tmp[2];

                graph[tmp[0]].add(new EdgeA9370(tmp[1], tmp[2]));
                graph[tmp[1]].add(new EdgeA9370(tmp[0], tmp[2]));
            }

            for (int i = 0; i < endCandidateN; i++)
                ends[i] = Integer.parseInt(br.readLine());

            stb.append(new 미확인도착지_9370(V, S, G, H, gToH, ends, graph).solution()).append("\n");
        }

        System.out.print(stb);
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
class EdgeA9370 implements Comparable<EdgeA9370>{
    int vertex, weight;

    public EdgeA9370(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(EdgeA9370 o) {
        return Integer.compare(weight, o.weight);
    }
}