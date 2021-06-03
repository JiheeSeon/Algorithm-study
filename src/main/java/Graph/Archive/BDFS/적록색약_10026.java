package Graph.Archive.BDFS;

import java.util.*;
import java.io.*;

/*
10026 적록색약
기본 그래프 문제에서는 갈 수 있는지 없는지 (0 or 1)만 고려해 탐색 진행
이제는 색깔(3개)마다 다른 영역으로 할지, RG는 통합할지 등의 변수 고려해야
*/

class 적록색약_10026 {
    char[][] graph;
    int[][] colorCk; // 여기에 첫번째 돌 때 color를 기록
    boolean[][] visited;
    int L;

    Map<Character, Integer> colorMap = new HashMap<>();

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 적록색약_10026(char[][] graph, int L){
        this.graph = graph;
        this.L = L;
        colorCk = new int[L][L];
        visited = new boolean[L][L];

        colorMap.put('R', 1);
        colorMap.put('G', 2);
        colorMap.put('B', 3);
    }

    String getAns(){
        int normal_areaN = 0;
        for(int y = 0; y < L; y++){
            for(int x = 0; x < L; x++){
                if(colorCk[y][x] == 0){
                    normal_dfs(y, x, colorMap.get(graph[y][x]));
                    normal_areaN++;
                }
            }
        }

        int rg_areaN = 0;
        for(int y = 0; y < L; y++){
            for(int x = 0; x < L; x++){
                if(!visited[y][x]){
                    rg_dfs(y, x, colorCk[y][x]);
                    rg_areaN++;
                }
            }
        }

        return new StringBuilder(Integer.toString(normal_areaN)).append(" ").append(rg_areaN).toString();
    }

    void normal_dfs(int y, int x, int color){
        if(y < 0 || x < 0 || y >= L || x >= L || colorCk[y][x] != 0 || colorMap.get(graph[y][x]) != color) return;

        colorCk[y][x] = color;
        for(int i = 0; i < 4; i++)
            normal_dfs(y + dy[i], x + dx[i], color);
    }

    void rg_dfs(int y, int x, int color){
        if(y < 0 || x < 0 || y >= L || x >= L || visited[y][x]) return;

        if((color == 1 || color == 2) && colorCk[y][x]== 3) return;
        else if((color == 3) && colorCk[y][x] != 3) return;

        visited[y][x] = true;

        for(int i = 0; i < 4; i++)
            rg_dfs(y + dy[i], x + dx[i], color);
    }
}

class MainA10026 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        char[][] graph = new char[L][L];

        for(int i = 0; i < L; i++){
            graph[i] = br.readLine().toCharArray();
        }

        System.out.println(new 적록색약_10026(graph, L).getAns());
    }
}