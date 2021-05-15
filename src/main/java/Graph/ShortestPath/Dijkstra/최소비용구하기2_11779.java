package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.regex.Pattern;

// 경로는 내가 꽂아줄 때(update) 기록

class 최소비용구하기2_11779 {
    int V, startV, endV;
    ArrayList<EdgeA11779>[] graph;
    int[] prev;
    int[] dist;

    public 최소비용구하기2_11779(int v, int startV, int endV, ArrayList<EdgeA11779>[] graph) {
        V = v;
        this.startV = startV;
        this.endV = endV;
        this.graph = graph;

        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        prev = new int[V + 1];
    }

    String solution() {
        StringBuilder stb = new StringBuilder();
        PriorityQueue<EdgeA11779> pq = new PriorityQueue<>();
        pq.offer(new EdgeA11779(startV, 0));
        dist[startV] = 0;

        EdgeA11779 now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for (EdgeA11779 next : graph[now.vertex]) {
                if(dist[next.vertex] > dist[now.vertex] + next.weight){
                    dist[next.vertex] = dist[now.vertex] + next.weight;
                    prev[next.vertex] = now.vertex;
                    pq.add(new EdgeA11779(next.vertex, dist[next.vertex]));
                }
            }
        }

        stb.append(dist[endV]).append("\n");

        int node = endV;
        Stack<Integer> stack = new Stack<>();

        while(node != startV){
            stack.push(node);
            node = prev[node];
        }
        stack.push(startV);

        stb.append(stack.size()).append("\n");

        while(!stack.isEmpty()){
            stb.append(stack.pop()).append(" ");
        }

        return stb.toString();
    }
}

class MainA11779 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        ArrayList<EdgeA11779>[] graph = new ArrayList[V + 1];
        for (int v = 0; v < V + 1; v++)
            graph[v] = new ArrayList<>();

        int[] tmp;
        for (int e = 0; e < E; e++) {
            tmp = splitIntoIntArray(br.readLine());
            graph[tmp[0]].add(new EdgeA11779(tmp[1], tmp[2]));
        }
        tmp = splitIntoIntArray(br.readLine());
        int startV = tmp[0]; int endV = tmp[1];

        System.out.println(new 최소비용구하기2_11779(V, startV, endV, graph).solution());
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
class EdgeA11779 implements Comparable<EdgeA11779>{
    int vertex, weight;

    public EdgeA11779(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight; // 들어오는 간선의 weight
    }

    @Override
    public int compareTo(EdgeA11779 o) {
        return Integer.compare(weight, o.weight);
    }
}