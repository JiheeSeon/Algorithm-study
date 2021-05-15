package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

class 특정거리의도시찾기_18352 {
    int V, S, K;
    ArrayList<Integer>[] graph;
    int[] dist;

    public 특정거리의도시찾기_18352(int v, int s, int k, ArrayList<Integer>[] graph) {
        V = v;
        S = s;
        K = k;
        this.graph = graph;
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
    }

    String solution() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        dist[S] = 0;
        pq.add(S);

        int now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for (int next : graph[now]) {
                if(dist[next] > dist[now] + 1){
                    dist[next] = dist[now] + 1;
                    pq.add(next);
                }
            }
        }

        StringBuilder stb = new StringBuilder();
        for(int i = 1; i <= V; i++){
            if(dist[i] == K) stb.append(i).append("\n");
        }

        return stb.length() == 0 ? "-1" : stb.toString();
    }
}
class MainA18352{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = splitIntoIntArray(br.readLine());
        int V = tmp[0]; int E = tmp[1]; int K = tmp[2]; int S = tmp[3];

        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for(int v = 0; v < V + 1; v++)
            graph[v] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            tmp = splitIntoIntArray(br.readLine());
            graph[tmp[0]].add(tmp[1]);
        }
        System.out.print(new 특정거리의도시찾기_18352(V, S, K, graph).solution());
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}