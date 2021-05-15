package Graph.ShortestPath.Dijkstra;
import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

// 두 지점을 경유할 때의 최단경로
// 어떤 지점을 먼저 경유할지에 따라 경우 나누기

class 특정최단경로_1504 {
    final static int INF = 999999;
    int V;
    ArrayList<Edge1504>[] graph;
    int[] dist, distA, distB;
    int passA, passB;

    public 특정최단경로_1504(int V, int passA, int passB, ArrayList<Edge1504>[] graph){
        this.V = V;
        this.passA = passA;
        this.passB = passB;
        this.graph = graph;

        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        distA = new int[V + 1];
        Arrays.fill(distA, INF);

        distB = new int[V + 1];
        Arrays.fill(distB, INF);
    }

    int solution(){
        dijkstra(1, dist);
        dijkstra(passA, distA);
        dijkstra(passB, distB);

        int toRet = Integer.MAX_VALUE;

        // 갈 수 없는 경로가 포함되어있는 경우 -1로 return

        if(dist[passA] != INF && distA[passB] != INF && distB[V] != INF)
            toRet = dist[passA] + distA[passB] + distB[V];

        if(dist[passB] != INF && distB[passA] != INF && distA[V] != INF)
            toRet = Math.min(toRet, dist[passB] + distB[passA] + distA[V]);

        return toRet == Integer.MAX_VALUE ? -1 : toRet;
    }

    void dijkstra(int startV, int[] dst){
        PriorityQueue<Edge1504> pq = new PriorityQueue<>();
        dst[startV] = 0;
        pq.offer(new Edge1504(startV, 0));

        // 1부터
        Edge1504 now;
        while(!pq.isEmpty()){
            now = pq.poll();

            for(Edge1504 next : graph[now.vertex]){
                if(dst[next.vertex] > dst[now.vertex] + next.weight){
                    dst[next.vertex] = dst[now.vertex] + next.weight;
                    pq.add(new Edge1504(next.vertex, dst[next.vertex]));
                }
            }
        }
    }
}

class MainA1504{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = splitIntoIntArray(br.readLine());
        int V = tmp[0]; int E = tmp[1];

        ArrayList<Edge1504>[] graph = new ArrayList[V + 1];
        for(int i = 0; i < V + 1; i++)
            graph[i] = new ArrayList<>();

        for(int e = 0; e < E; e++){
            tmp = splitIntoIntArray(br.readLine());
            // 양방향 그래프
            graph[tmp[0]].add(new Edge1504(tmp[1], tmp[2]));
            graph[tmp[1]].add(new Edge1504(tmp[0], tmp[2]));
        }
        tmp = splitIntoIntArray(br.readLine());

        System.out.println(new 특정최단경로_1504(V, tmp[0], tmp[1], graph).solution());
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
class Edge1504 implements Comparable<Edge1504>{
    int vertex; int weight;

    public Edge1504(int vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge1504 e){
        return e.weight == weight ? Integer.compare(vertex, e.vertex) : Integer.compare(weight, e.weight);
    }
}
