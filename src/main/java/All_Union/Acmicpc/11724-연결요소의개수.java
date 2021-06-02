package All_Union.Acmicpc;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class 연결요소의개수_11724 {
    int V, E;
    int[] parent;
    int[][] conn;

    public 연결요소의개수_11724(int V, int E, int[][] conn){
        this.V = V;
        this.E = E;
        this.conn = conn;
        parent = IntStream.range(0, V).toArray();
    }

    int getAns(){
        for(int e = 0; e < E; e++)
            union(conn[e][0] - 1, conn[e][1] - 1);

        Set<Integer> set = new HashSet<>();
        for(int v = 0; v < V; v++){
            set.add(find(v));
        }

        return set.size();
    }

    void union(int a, int b){
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;
    }

    int find(int a){
        return (parent[a] = (a == parent[a] ? a : find(parent[a])));
    }
}

class MainA11724 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];
        int[][] conn = new int[E][2];
        for(int e = 0; e < E; e++){
            conn[e] = strToIntArr(br.readLine());
        }

        System.out.println(new 연결요소의개수_11724(V, E, conn).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}