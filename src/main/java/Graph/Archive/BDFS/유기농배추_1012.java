package Graph.Archive.BDFS;

import java.io.*;
import java.util.regex.Pattern;

/*
1012 유기농배추
연결요소의 개수 -> DFS
*/
class 유기농배추_1012_DFS {
    int yHeight, xWidth;
    boolean[][] graph;
    boolean[][] check;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 유기농배추_1012_DFS(int yHeight, int xWidth, int K, int[][] position){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        graph = new boolean[yHeight][xWidth];
        check = new boolean[yHeight][xWidth];

        for(int k = 0; k < K; k++){
            graph[position[k][1]][position[k][0]] = true;
        }
    }

    public int solution(){
        int cnt = 0;
        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                if(!check[y][x] && graph[y][x]){
                    dfs(y, x); cnt++;
                }
            }
        }

        return cnt;
    }

    public void dfs(int y, int x){
        if(y < 0 || x < 0 || y >= yHeight || x >= xWidth || check[y][x] || !graph[y][x]) return;

        check[y][x] = true;
        for(int i = 0; i < 4; i++){
            dfs(y + dy[i], x + dx[i]);
        }
    }
}

class MainA1012 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder stb = new StringBuilder();
        int yHeight, xWidth, K;
        int[] tmp;
        int[][] position;

        for(int t = 0; t < T; t++){
            tmp = strToIntArr(br.readLine());
            yHeight = tmp[1]; xWidth = tmp[0]; K = tmp[2];

            position = new int[K][2];
            for(int k = 0; k < K; k++){
                position[k] = strToIntArr(br.readLine());
            }

            stb.append(new 유기농배추_1012_DFS(yHeight, xWidth, K, position).solution()).append("\n");
        }
        System.out.print(stb);
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}