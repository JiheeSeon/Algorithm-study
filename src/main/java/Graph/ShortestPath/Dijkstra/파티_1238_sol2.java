package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

/*
Dijkstra -> one to All
즉 하나의 시작점에서 모든 점에 대한 최단경로를 구하는 방식

1, 3, 4 에서 2로 갈 때 모든 최단경로를 구하는 것보다는 간선을 반대로 저장해서
한번만 할 수 있도록!
*/
class 파티_1238_sol2 {
    ArrayList<Edge1238>[] revGraph, graph;
    int[] distToParty, distFromParty;
    int partyV, vertexN;

    public 파티_1238_sol2(int vertexN, int partyV, ArrayList<Edge1238>[] revGraph, ArrayList<Edge1238>[] graph) {
        this.vertexN = vertexN;
        this.partyV = partyV;

        this.revGraph = revGraph;
        this.graph = graph;

        distToParty = new int[vertexN + 1];
        distFromParty = new int[vertexN + 1];
        Arrays.fill(distToParty, Integer.MAX_VALUE);
        Arrays.fill(distFromParty, Integer.MAX_VALUE);
    }

    int solution() {
        setDistToParty();
        setDistFromParty();

        int max = 0;
        for (int i = 1; i <= vertexN; i++) {
            max = Math.max(distToParty[i] + distFromParty[i], max);
        }
        return max;
    }

    void setDistToParty(){
        // revGraph 이용
        PriorityQueue<Edge1238> pqToParty = new PriorityQueue<>();
        pqToParty.offer(new Edge1238(partyV, 0));
        distToParty[partyV] = 0;

        Edge1238 now;
        while (!pqToParty.isEmpty()) {
            now = pqToParty.poll();

            for (Edge1238 next : revGraph[now.vertex]) {
                if (distToParty[next.vertex] > distToParty[now.vertex] + next.weight) {
                    distToParty[next.vertex] = distToParty[now.vertex] + next.weight;
                    pqToParty.add(new Edge1238(next.vertex, distToParty[next.vertex]));
                }
            }
        }
    }

    void setDistFromParty(){
        // graph 이용
        PriorityQueue<Edge1238> pqFromParty = new PriorityQueue<>();
        pqFromParty.offer(new Edge1238(partyV, 0));
        distFromParty[partyV] = 0;

        Edge1238 now;
        while (!pqFromParty.isEmpty()) {
            now = pqFromParty.poll();

            for (Edge1238 next : graph[now.vertex]) {
                if (distFromParty[next.vertex] > distFromParty[now.vertex] + next.weight) {
                    distFromParty[next.vertex] = distFromParty[now.vertex] + next.weight;
                    pqFromParty.add(new Edge1238(next.vertex, distFromParty[next.vertex]));
                }
            }
        }
    }
}
class MainA1238_2{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = splitIntoIntArray(br.readLine());
        int vertexN = tmp[0]; int edgeN = tmp[1]; int endV = tmp[2];

        ArrayList<Edge1238>[] graph = new ArrayList[vertexN + 1];
        ArrayList<Edge1238>[] revGraph = new ArrayList[vertexN + 1];
        for(int i = 0; i < vertexN + 1; i++) {
            graph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }

        for(int e = 0; e < edgeN; e++){
            tmp = splitIntoIntArray(br.readLine());
            graph[tmp[0]].add(new Edge1238(tmp[1], tmp[2]));
            revGraph[tmp[1]].add(new Edge1238(tmp[0], tmp[2]));
        }
        System.out.println(new 파티_1238_sol2(vertexN, endV, revGraph, graph).solution());
    }

    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}

class Edge1238 implements Comparable<Edge1238>{
    int vertex, weight;

    public Edge1238(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge1238 e) {
        return weight == e.weight
                ? Integer.compare(vertex, e.vertex)
                : Integer.compare(weight, e.weight);
    }
}