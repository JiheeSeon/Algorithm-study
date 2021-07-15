package Tree.MST;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class 최소스패닝트리_1197 {
    int V, E;
    int[][] input;

    public 최소스패닝트리_1197(int V, int E, int[][] input){
        this.V = V;
        this.E = E;
        this.input = input;
    }

    long solution_krukal(){
        long ans = 0;
        int[] parent = IntStream.rangeClosed(0, V).toArray();

        ArrayList<EdgeA1197> graph = new ArrayList<>();
        for(int e = 0; e < E; e++){
            graph.add(new EdgeA1197(input[e][0], input[e][1], input[e][2]));
        }
        Collections.sort(graph);

        int edgeCnt = 0;
        for(EdgeA1197 e : graph){
            if(!union(parent, e.v1, e.v2)) continue;

            ans += e.w;
            if(++edgeCnt == E - 1) return ans;
        }
        return ans;
    }

    boolean union(int[] parent, int a, int b){
        int pA = find(parent, a);
        int pB = find(parent, b);

        if(pA == pB) return false;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int[] parent, int a){
        return a == parent[a] ? a : (parent[a] = find(parent, parent[a]));
    }

    long solution_prim(){
        long ans = 0;

        return ans;
    }
}

class EdgeA1197 implements Comparable<EdgeA1197>{
    int v1, v2;
    long w;

    public EdgeA1197(int v1, int v2, int w){
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }

    @Override
    public int compareTo(EdgeA1197 o) {
        return Long.compare(w, o.w);
    }
}

class MainA1197{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmp = strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];
        int[][] input = new int[E][3];

        for(int e = 0; e < E; e++)
            input[e] = strToIntArr(br.readLine());

        System.out.println(new 최소스패닝트리_1197(V, E, input).solution_krukal());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}