package Graph.Archive.BDFS;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class 촌수계산_2644_BFS {
    int V, E, S, D;
    int[] check;
    ArrayList<Integer>[] graph;
    int ans;

    public 촌수계산_2644_BFS(int V, int E, int S, int D, int[][] connection, int[] check){
        this.V = V;
        this.E = E;
        this.S = S;
        this.D = D;
        this.check = check;

        graph = new ArrayList[V];
        for(int v = 0; v < V; v++)
            graph[v] = new ArrayList<>();

        for(int e = 0; e < E; e++){
            graph[connection[e][0] - 1].add(connection[e][1] - 1);
            graph[connection[e][1] - 1].add(connection[e][0] - 1);
        }
    }

    void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(S);
        check[S] = 1;

        int now;

        while(!q.isEmpty()){
            now = q.poll();

            for(int next: graph[now]){
                if(check[next] != 0) continue; //안 넣으면 무한루프

                check[next] = check[now] + 1;

                if(next == D){
                    ans = check[D] - 1;
                    return;
                }
                q.add(next);
            }
        }
    }

    int getAns(){
        return ans == 0 ? -1 : ans;
    }
}

class 촌수계산_2644_DFS_1 {
    int V, E, S, D;
    int[] check;
    ArrayList<Integer>[] graph;
    int ans = -1;

    public 촌수계산_2644_DFS_1(int V, int E, int S, int D, int[][] connection, int[] check){
        this.V = V;
        this.E = E;
        this.S = S;
        this.D = D;
        this.check = check;

        graph = new ArrayList[V];
        for(int v = 0; v < V; v++)
            graph[v] = new ArrayList<>();

        for(int e = 0; e < E; e++){
            graph[connection[e][0] - 1].add(connection[e][1] - 1);
            graph[connection[e][1] - 1].add(connection[e][0] - 1);
        }
    }

    void dfs(int now, int prev){
        if(check[now] != 0) return;

        if(now == S) check[now] = 0;
        else check[now] = check[prev] + 1;

        if(now == D) ans = check[now];

        for(int next: graph[now]){
            dfs(next, now);
        }
    }

    int getAns(){
        return ans;
    }
}

class 촌수계산_2644_DFS_2 {
    int V, E, S, D;
    ArrayList<Integer>[] graph;
    int ans = -1;
    boolean[] check;

    public 촌수계산_2644_DFS_2(int V, int E, int S, int D, int[][] connection){
        this.V = V;
        this.E = E;
        this.S = S;
        this.D = D;

        check = new boolean[V];

        graph = new ArrayList[V];
        for(int v = 0; v < V; v++)
            graph[v] = new ArrayList<>();

        for(int e = 0; e < E; e++){
            graph[connection[e][0] - 1].add(connection[e][1] - 1);
            graph[connection[e][1] - 1].add(connection[e][0] - 1);
        }
    }

    void dfs(int now, int dist){
        if(check[now]) return;

        check[now] = true;

        if(now == D){
            ans = dist;
            return;
        }

        for(int next: graph[now]) dfs(next, dist + 1);
    }

    int getAns(){
        return ans;
    }
}

class MainA2644 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int[] check = new int[V];

        int[] tmp = strToIntArr(br.readLine());
        int S = tmp[0] - 1; int D = tmp[1] - 1;

        int E = Integer.parseInt(br.readLine());
        int[][] connection = new int[E][2];
        for(int e = 0; e < E; e++){
            connection[e] = strToIntArr(br.readLine());
        }

        촌수계산_2644_BFS s_bfs = new 촌수계산_2644_BFS(V, E, S, D, connection, check);
        s_bfs.bfs();
        System.out.println(s_bfs.getAns());

        촌수계산_2644_DFS_1 s_dfs_1 = new 촌수계산_2644_DFS_1(V, E, S, D, connection, new int[V]);
        s_dfs_1.dfs(S, -1);
        System.out.println(s_dfs_1.getAns());

        촌수계산_2644_DFS_2 s_dfs_2 = new 촌수계산_2644_DFS_2(V, E, S, D, connection);
        s_dfs_2.dfs(S, 0);
        System.out.println(s_dfs_2.getAns());
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}