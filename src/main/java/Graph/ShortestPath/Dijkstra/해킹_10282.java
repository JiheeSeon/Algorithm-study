package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

// 문제 이해 잘못해서 계속 틀렸음
// 마지막으로 방문한 노드의 시간이 아니라, cost의 최댓값을 말하는 것이었음.

class 해킹_10282 {
    int V; int S;
    ArrayList<EdgeA10282>[] graph;
    int[] cost;

    public 해킹_10282(int V, int S, ArrayList<EdgeA10282>[] graph) {
        this.V = V;
        this.S = S;
        this.graph = graph;

        cost = new int[V + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[S] = 0;
    }

    String solution() {
        PriorityQueue<EdgeA10282> pq = new PriorityQueue<>();
        pq.offer(new EdgeA10282(S, 0));

        EdgeA10282 now;

        while (!pq.isEmpty()) {
            now = pq.poll();

            for (EdgeA10282 next : graph[now.vertex]) {
                if (cost[next.vertex] > cost[now.vertex] + next.weight) {
                    cost[next.vertex] = cost[now.vertex] + next.weight;
                    pq.add(new EdgeA10282(next.vertex, cost[next.vertex]));
                }
            }
        }
        int max = 0;
        int cnt = 0;
        for (int i = 1; i <= V; i++) {
            if(cost[i] == Integer.MAX_VALUE) continue;
            max = Math.max(cost[i], max);
            cnt++;
        }

        return cnt + " " + max;
    }
}
class MainA10282{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] tmp;
        int V, E, S;
        ArrayList<EdgeA10282>[] graph;
        StringBuilder stb = new StringBuilder();

        int t = 0;
        while(t++ < T){
            tmp = splitIntoIntArray(br.readLine());
            V = tmp[0]; E = tmp[1]; S = tmp[2];

            graph = new ArrayList[V + 1];
            for (int v = 0; v < V + 1; v++) {
                graph[v] = new ArrayList<>();
            }

            for (int e = 0; e < E; e++) {
                tmp = splitIntoIntArray(br.readLine());
                graph[tmp[1]].add(new EdgeA10282(tmp[0], tmp[2]));
            }

            stb.append(new 해킹_10282(V, S, graph).solution()).append("\n");
        }

        System.out.print(stb);
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}

class EdgeA10282 implements Comparable<EdgeA10282>{
    int vertex, weight;

    public EdgeA10282(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(EdgeA10282 o) {
        return Integer.compare(weight, o.weight);
    }
}