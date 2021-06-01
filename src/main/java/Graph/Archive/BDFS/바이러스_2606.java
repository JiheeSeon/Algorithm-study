package Graph.Archive.BDFS;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

class 바이러스_2606 {
    int V;
    boolean[] check;
    ArrayList<Integer>[] graph;

    public 바이러스_2606(int V, boolean[] check, ArrayList<Integer>[] graph){
        this.V = V;
        this.check = check;
        this.graph = graph;
    }

    int solution_dfs(int nowV, int cnt){
        if(check[nowV]) return cnt;

        check[nowV] = true;
        cnt++;

        for(int nextV : graph[nowV])
            cnt = solution_dfs(nextV, cnt);

        return cnt;
    }

    int solution_bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        check[0] = true;

        int res = 0;
        int nowV;

        while(!q.isEmpty()){
            nowV = q.poll();

            for(int nextV: graph[nowV]){
                if(check[nextV]) continue;

                check[nextV] = true;
                res++;
                q.add(nextV);
            }
        }

        return res;
    }
}


class MainA2606 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[V];
        for(int v = 0; v < V; v++){
            graph[v] = new ArrayList<>();
        }
        boolean[] check = new boolean[V];

        int[] tmp;
        for(int e = 0; e < E; e++){
            tmp = strToIntArr(br.readLine());
            graph[tmp[0] - 1].add(tmp[1] - 1);
            graph[tmp[1] - 1].add(tmp[0] - 1);
        }

        System.out.println(new 바이러스_2606(V, check, graph).solution_dfs(0, 0) - 1);
        // System.out.println(new Solution_2606(V, check, graph).solution_bfs());
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}