package Graph.Archive.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class 최소스패닝트리_1197_Krukal {
    int V, E;
    Edge_1197[] edges;

    int[] parent;
    int totalCost = 0;

    public 최소스패닝트리_1197_Krukal(int v, int e, Edge_1197[] edges) {
        V = v;
        E = e;
        this.edges = edges;
        parent = IntStream.rangeClosed(0, V).toArray();
    }

    int getAns() {
        Arrays.sort(edges);

        int cnt = 0;
        for(Edge_1197 edge : edges){
            if(cnt == V - 1) break;
            if(!union(edge.start, edge.end)) continue;

            cnt++;
            totalCost += edge.cost;
        }

        return totalCost;
    }

    boolean union(int a, int b){
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return false;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int a) {
        return (a == parent[a]) ? a : find(parent[a]);
    }
}

class Edge_1197 implements Comparable<Edge_1197>{
    int start, end, cost;

    public Edge_1197(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge_1197 o) {
        return Integer.compare(cost, o.cost);
    }
}

class MainA1197{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];
        Edge_1197[] edges = new Edge_1197[E];

        for(int e = 0; e < E; e++){
            tmp = strToIntArr(br.readLine());
            edges[e] = new Edge_1197(tmp[0], tmp[1], tmp[2]);
        }

        System.out.println(new 최소스패닝트리_1197_Krukal(V, E, edges).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
/*
7 9
1 2 29
2 3 16
3 4 12
5 4 22
4 7 18
5 7 25
2 7 15
6 5 27
6 1 10
*/