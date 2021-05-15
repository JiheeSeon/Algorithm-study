package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import java.util.*;

class 파티_1238_sol1 {
    ArrayList<Main_A1238.Edge>[] graph;
    int endV, vertexN;
    int[] gDist;
    int[] comingCost;

    public 파티_1238_sol1(ArrayList<Main_A1238.Edge>[] graph, int endV, int vertexN){
        this.graph = graph;
        this.endV = endV;
        this.vertexN = vertexN;
        setComingCost();
    }

    int solution(int startV){
        PriorityQueue<Main_A1238.Edge> pq = new PriorityQueue<>();
        pq.offer(new Main_A1238.Edge(startV, 0));

        gDist = new int[vertexN + 1];
        Arrays.fill(gDist, Integer.MAX_VALUE);
        gDist[startV] = 0;

        Main_A1238.Edge now;

        // 시작점에서 파티장소로 가는 길까지의 최단경로
        while(!pq.isEmpty()){
            now = pq.poll();
            for(Main_A1238.Edge next : graph[now.vertex]){
                if(gDist[next.vertex] > gDist[now.vertex] + next.weight){
                    gDist[next.vertex] = gDist[now.vertex] + next.weight;
                    pq.add(new Main_A1238.Edge(next.vertex, gDist[next.vertex]));
                }
            }
        }

        System.out.println(Arrays.toString(gDist));
        return gDist[endV] + comingCost[startV];
    }

    void setComingCost(){
        int[] cDist = new int[vertexN + 1];
        Arrays.fill(cDist, Integer.MAX_VALUE);
        cDist[endV] = 0;

        PriorityQueue<Main_A1238.Edge> pq = new PriorityQueue<>();
        pq.offer(new Main_A1238.Edge(endV, 0));

        Main_A1238.Edge now;
        while(!pq.isEmpty()){
            now = pq.poll();

            for(Main_A1238.Edge next : graph[now.vertex]){
                if(cDist[next.vertex] > cDist[now.vertex] + next.weight){
                    cDist[next.vertex] = cDist[now.vertex] + next.weight;
                    pq.add(new Main_A1238.Edge(next.vertex, cDist[next.vertex]));
                }
            }
        }
        this.comingCost = cDist;
    }
}

class Main_A1238 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = splitIntoIntArray(br.readLine());
        int vertexN = tmp[0]; int edgeN = tmp[1]; int endV = tmp[2];

        ArrayList<Main_A1238.Edge>[] graph = new ArrayList[vertexN + 1];
        for(int i = 0; i < vertexN + 1; i++)
            graph[i] = new ArrayList<>();

        for(int e = 0; e < edgeN; e++){
            tmp = splitIntoIntArray(br.readLine());
            graph[tmp[0]].add(new Main_A1238.Edge(tmp[1], tmp[2]));
        }

        파티_1238_sol1 s = new 파티_1238_sol1(graph, endV, vertexN);
        int max = 0;
        for(int i = 1; i <= vertexN; i++){
            if(i == endV) continue;
            max = Math.max(max, s.solution(i));
        }

        System.out.println(max);
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
    static class Edge implements Comparable<Edge>{
        int vertex, weight;

        public Edge(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge e) {
            return weight == e.weight
                    ? Integer.compare(vertex, e.vertex)
                    : Integer.compare(weight, e.weight);
        }
    }
}