package Graph.ShortestPath.BellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

class 오민식의고민_1219 {
    static final long INF = -99876543210L;

    int V, S, E;
    long[] earnings;
    ArrayList<EdgeA1219> edges;

    long[] dist;

    public 오민식의고민_1219(int v, int s, int e, ArrayList<EdgeA1219> edges, long[] earnings) {
        V = v;
        S = s;
        E = e;
        this.edges = edges;
        this.earnings = earnings;

        dist = new long[V];
        Arrays.fill(dist, INF);
        dist[S] = earnings[S];
    }

    String solution() {
        boolean isUpdated = false;
        Set<Integer> inCycle = new HashSet<>();

        for(int v = 1; v <= V; v++){
            isUpdated = false;

            for(EdgeA1219 edge: edges){
//                if(v == V){
//                if(edge.end == 3){
//                    System.out.println(edge);
//                    System.out.println(dist[edge.start]);
//                    System.out.println(dist[edge.end] + " vs " + (dist[edge.start] + earnings[edge.end] + edge.cost));
//                }

                if(dist[edge.start] != INF && dist[edge.end] <= dist[edge.start] + earnings[edge.end] + edge.cost){
                    if(v == V){
                        if(dist[edge.end] != dist[edge.start] + earnings[edge.end] + edge.cost) inCycle.add(edge.end);
                    }

                    dist[edge.end] = dist[edge.start] + edge.cost + earnings[edge.end];
                    isUpdated = true;
                }
            }
            if(!isUpdated) break;
        }
        System.out.println(inCycle);
        System.out.println(Arrays.toString(dist));
        if(dist[E] == INF) return "gg";
        if(isUpdated && inCycle.contains(E)) return "Gee";
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
            edges.add(new EdgeA1219(tmp[0], tmp[1], (long)-1 * tmp[2]));
        }

        long[] earnings = splitIntoLongArray(br.readLine());
        System.out.println(new 오민식의고민_1219(V, S, E, edges, earnings).solution());
    }

    static int[] splitIntoIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
    static long[] splitIntoLongArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToLong(Long::parseLong).toArray();
    }
}
class EdgeA1219{
    int start, end;
    long cost;

    public EdgeA1219(int start, int end, long cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "(" + start + " to " + end +
                ":" + cost +
                ')';
    }
}

/*
4 0 3 4
0 1 0
1 2 0
2 1 0
0 3 10
10 10 10 10

10

4 0 3 4
0 1 0
0 3 5
1 2 0
2 1 0
0 5 5 10

4 1 3 4
0 1 0
0 1 100000
1 2 3
2 3 4
2 2 2 2

-1

4 0 3 4
0 1 0
1 2 0
2 1 0
0 3 10
10 10 10 10

10

4 1 3 4
0 1 0
1 2 0
2 1 0
0 3 10
10 10 10 10

gg

3 0 2 4
0 1 9999
1 2 9999
1 1 9999
0 2 0
10000 10000 10000

Gee
*/