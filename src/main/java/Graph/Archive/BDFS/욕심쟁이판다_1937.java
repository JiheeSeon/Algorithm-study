package Graph.Archive.BDFS;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

/*
1937 욕심쟁이 판다
- 최장경로 : backtrack vs dfs

timeout solution
- check 배열의 용도 : 몇번째로 왔다는 것을 기록

right solution
- dist 배열의 용도 : dp, 최장거리 기록
*/

class 욕심쟁이판다_1937 {
    int N;
    int[][] graph;
    int[][] dist; // 여기서 시작해서 최대 얼마나 갈 수 있는지
    int ans = 0;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 욕심쟁이판다_1937(int N, int[][] graph){
        this.N = N;
        this.graph = graph;
        dist = new int[N][N];
    }

    int getAns(){
        for(int y = 0; y < N; y++){
            for(int x = 0; x < N; x++){
                if(dist[y][x] == 0) {
                    ans = Math.max(ans, dfs(y, x, -1));
//                    display(); System.out.println();
                }
            }
        }
        return ans;
    }

    int dfs(int y, int x, int prevVal){
        if(y < 0 || x < 0 || y >= N || x >= N || graph[y][x] <= prevVal) return 0;
        if(dist[y][x] != 0) return dist[y][x];

        dist[y][x] = 1;

        for(int i = 0; i < 4; i++) {
            dist[y][x] = Math.max(dist[y][x], dfs(y + dy[i], x + dx[i], graph[y][x]) + 1);
        }

        return dist[y][x];
    }

    void display(){
        for(int y = 0; y < N; y++)
            System.out.println(Arrays.toString(dist[y]));
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

/*
2
2 2
2 2
*/