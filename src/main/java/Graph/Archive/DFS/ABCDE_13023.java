package Graph.Archive.DFS;

import java.io.*;
import java.util.regex.Pattern;
import java.util.*;

class ABCDE_13023 {
    int V, E;
    ArrayList<Integer>[] graph;
    boolean[] check;

    public ABCDE_13023(int V, int E, int[][] relation){
        this.V = V;
        this.E = E;

        graph = new ArrayList[V];
        for(int v = 0; v < V; v++)
            graph[v] = new ArrayList<>();

        for(int e = 0; e < E; e++){
            graph[relation[e][0]].add(relation[e][1]);
            graph[relation[e][1]].add(relation[e][0]);
        }
    }

    int dfs(int nowV, int cnt){
        check[nowV] = true;
        cnt++;

        int max = cnt;
        for(int nextV : graph[nowV]){
            if(check[nextV]) continue;
            max = Math.max(max, dfs(nextV, cnt));
            if(max >=5) return max;
        }
        return max;
    }

    int getAns(){
        int max = 0;
        int ans = 0;

        for(int v = 0; v < V; v++){
            check = new boolean[V];
            max = Math.max(max, dfs(v, 0));
            if(max >= 5){
                ans = 1;
                break;
            }
        }

        return ans;
    }
}

class MainA13023 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];
        int[][] relation = new int[E][2];
        for(int e = 0; e < E; e++){
            relation[e] = strToIntArr(br.readLine());
        }

        System.out.println(new ABCDE_13023(V, E, relation).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}