package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// 여러 최단경로 중 G, H를 경유하는 경로로 책정될 수도, 아닐 수도
class 미확인도착지_9370 {
    int V, S, G, H, gToH;
    int[] ends;
    ArrayList<EdgeA9370>[] graph;
    int[] dist;
    int[] prev;

    public 미확인도착지_9370(int v, int s, int g, int h, int[] ends, ArrayList<EdgeA9370>[] graph) {
        V = v; S = s; G = g; H = h;
        this.ends = ends;
        this.graph = graph;

        prev = new int[V + 1];
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;
    }

    String solution() {
        dijkstra();

        System.out.println(Arrays.toString(dist));
        System.out.println(Arrays.toString(prev));
        LinkedList<Integer> res = new LinkedList<>();
        int node;

        for (int end : ends) {
            if(dist[end] == Integer.MAX_VALUE) continue;

            Stack<Integer> stack = new Stack<>();
            node = end;

            while(node != S){
                if(node != end && ((stack.peek() == H && node == G) || (stack.peek() == G && node == H))){
                    res.add(end);
                    break;
                }
                stack.push(node);
                node = prev[node];
            }
            stack.push(S);
            if((stack.peek() == H && S == G) || (stack.peek() == G && S == H)) res.add(end);
        }

        return res.stream().sorted().map(String::valueOf).collect(Collectors.joining(" "));
    }

    void dijkstra() {
        PriorityQueue<EdgeA9370> pq = new PriorityQueue<>();
        pq.offer(new EdgeA9370(S, 0));

        EdgeA9370 now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for(EdgeA9370 next : graph[now.vertex]){
                if (dist[next.vertex] > dist[now.vertex] + next.weight) {
                    dist[next.vertex] = dist[now.vertex] + next.weight;
                    pq.add(new EdgeA9370(next.vertex, next.weight));
                    prev[next.vertex] = now.vertex;
                }
            }
        }
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
                graph[tmp[0]].add(new EdgeA9370(tmp[1], tmp[2]));
                graph[tmp[1]].add(new EdgeA9370(tmp[0], tmp[2]));
            }

            for (int i = 0; i < endCandidateN; i++)
                ends[i] = Integer.parseInt(br.readLine());

            stb.append(new 미확인도착지_9370(V, S, G, H, ends, graph).solution()).append("\n");
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