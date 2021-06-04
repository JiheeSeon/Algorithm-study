package Graph.Archive.BDFS;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
9466 텀프로젝트
*/

class 텀프로젝트_9466 {
    int N;
    int[] graph;
    boolean[] visited;
    boolean[] check;

    TreeSet<Integer> solos = new TreeSet<>();

    public 텀프로젝트_9466(int N, int[] graph){
        this.N = N;
        this.graph = new int[N + 1];
        this.visited = new boolean[N + 1];
        this.check = new boolean[N + 1];

        for(int i = 1; i <= N; i++) {
            this.graph[i] = graph[i - 1];
            if(i == this.graph[i]){
                solos.add(i);
                visited[i] = true;
                check[i] = true;
            }
        }
    }

    int getAns(){
        for(int i = 1; i <= N; i++){
            if(!visited[i]) {
                dfs(i, false);
            }
        }

        int cnt = 0;
        for(int i = 1; i <= N; i++){
            if(check[i]){
                if(solos.contains(i)) continue;
                cnt++;
            }
        }
        return cnt;
    }

    boolean dfs(int now, boolean flag) {
        if(visited[now]) return check[now];

        visited[now] = true;
//        if(solos.contains(now)) flag = true;

        return check[now] = dfs(graph[now], flag);
    }
}

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int N; int[] wanted;
        StringBuilder stb = new StringBuilder();
        for(int t = 0; t < T; t++){
            N = Integer.parseInt(br.readLine());
            wanted = strToIntArr(br.readLine());
            stb.append(new 텀프로젝트_9466(N, wanted).getAns()).append("\n");
        }
        System.out.print(stb);
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
/*
1
5
3 3 1 2 1
0
*/