package Graph.Archive.BDFS;

import java.io.*;
import java.util.regex.Pattern;

/*
1937 욕심쟁이 판다
- 최장경로 : backtrack vs dfs
*/

class 욕심쟁이판다_1937 {
    int N;
    int[][] graph;
    int[][] check;
    int ans = 0;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 욕심쟁이판다_1937(int N, int[][] graph){
        this.N = N;
        this.graph = graph;
        check = new int[N][N];
    }

    int getAns(){
        for(int y = 0; y < N; y++){
            for(int x = 0; x < N; x++){
                if(check[y][x] == 0)
                    ans = Math.max(ans, dfs(y, x, 0, 1));
            }
        }
        return ans;
    }

    int dfs(int y, int x, int prevVal, int depth){
        if(y < 0 || x < 0 || y >= N || x >= N
                || graph[y][x] <= prevVal || check[y][x] >= depth) return depth;

        depth++;
        check[y][x] = depth;

        int max = depth;
        for(int i = 0; i < 4; i++)
            max = Math.max(max, dfs(y + dy[i], x + dx[i], graph[y][x], depth));

        return max;
    }
}

class MainA1937 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        for(int i = 0; i < N; i++)
            graph[i] = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        System.out.println(new 욕심쟁이판다_1937(N, graph).getAns());
    }
}