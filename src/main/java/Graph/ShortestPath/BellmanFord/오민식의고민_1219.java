package Graph.ShortestPath.BellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

class 오민식의고민_1219 {
    static final int INF = -987654321;

    int V, S, E;
    int[] earnings;
    ArrayList<EdgeA1219> edges;

    int[] dist;

    public 오민식의고민_1219(int v, int s, int e, ArrayList<EdgeA1219> edges, int[] earnings) {
        V = v;
        S = s;
        E = e;
        this.edges = edges;
        this.earnings = earnings;

        dist = new int[V];
        Arrays.fill(dist, INF);
        dist[S] = earnings[S];
    }

    String solution() {
        boolean isUpdated = false;

        outerLoop:
        for(int v = 1; v <= V; v++){
            isUpdated = false;

            for(EdgeA1219 edge: edges){
                if(dist[edge.start] != INF && dist[edge.end] < dist[edge.start] + edge.cost + earnings[edge.end]){
                    dist[edge.end] = dist[edge.start] + edge.cost + earnings[edge.end];
                    isUpdated = true;

                    if(v == V) break outerLoop;
                }
            }
            if(!isUpdated) break;
        }

        if(dist[E] == INF) return "gg";
        if(isUpdated) return "Gee";
        return String.valueOf(dist[E]);
    }
}
class MainA1219{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = splitIntoIntArray(br.readLine());
        int V = tmp[0]; int S = tmp[1]; int E = tmp[2]; int M = tmp[3];

        ArrayList<EdgeA1219> edges = new ArrayList<>();
        for(int m = 0; m < M; m++){
            tmp = splitIntoIntArray(br.readLine());
            edges.add(new EdgeA1219(tmp[0], tmp[1], -tmp[2]));
        }

        int[] earnings = splitIntoIntArray(br.readLine());
        System.out.println(new 오민식의고민_1219(V, S, E, edges, earnings).solution());
    }

    static int[] splitIntoIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
class EdgeA1219{
    int start, end, cost;

    public EdgeA1219(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}