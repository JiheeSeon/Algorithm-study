package All_Union.Acmicpc.Resolve;

import java.io.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class MainA2606{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        int[][] edges = new int[E][2]; // 프로그래머스 형태의 Input

        for(int e = 0; e < E; e++)
            edges[e] = strToIntArr(br.readLine());

        System.out.println(new 바이러스_2606(V, E, edges).solution());
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}

class 바이러스_2606{
    int V, E;
    int[][] edges;
    int[] parent;

    public 바이러스_2606(int V, int E, int[][] edges){
        this.V = V;
        this.E = E;
        this.edges = edges;
        parent = IntStream.range(0, V).toArray();
    }

    int solution(){
        for(int[] edge: edges)
            union(edge[0] - 1, edge[1] - 1);

        int res = 0;
        for(int v = 1; v < V; v++){
            if(find(v) == 0) res++;
        }

        return res;
    }

    void union(int a, int b){
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;
    }

    int find(int a){
        return parent[a] = (a == parent[a] ? a : find(parent[a]));
    }
}