package Graph.Archive.BDFS;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class 섬의개수_4885 {
    int yHeight, xWidth;
    int[][] graph;
    boolean[][] check;
    int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
    int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};

    public 섬의개수_4885(int yHeight, int xWidth, int[][] graph, boolean[][] check){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.graph = graph;
        this.check = check;
    }

    void solution_dfs(int y, int x){
        if(y < 0 || x < 0 || y >= yHeight || x >= xWidth || check[y][x] || graph[y][x] == 0) return;

        check[y][x] = true;

        int nextY, nextX;
        for(int i = 0; i < 8; i++){
            nextY = y + dy[i];
            nextX = x + dx[i];

            solution_dfs(nextY, nextX);
        }
    }

    void solution_bfs(int startY, int startX){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startY, startX));
        check[startY][startX] = true;

        Point now;
        int nextY, nextX;
        while(!q.isEmpty()){
            now = q.poll();

            for(int i = 0; i < 8; i++){
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth || check[nextY][nextX] || graph[nextY][nextX] == 0) continue;

                check[nextY][nextX] = true;
                q.add(new Point(nextY, nextX));
            }
        }
    }


    private class Point{
        int y, x;

        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}

class MainA4885 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String wh; int[] tmp;
        int[][] graph;
        boolean[][] check;

        int yHeight, xWidth;
        int cnt;
        StringBuilder stb = new StringBuilder();

        섬의개수_4885 sol;

        while(!(wh = br.readLine()).equals("0 0")){
            tmp = strToIntArr(wh);
            yHeight = tmp[1]; xWidth = tmp[0];
            graph = new int[yHeight][xWidth];
            check = new boolean[yHeight][xWidth];

            for(int y = 0; y < yHeight; y++)
                graph[y] = strToIntArr(br.readLine());

            sol = new 섬의개수_4885(yHeight, xWidth, graph, check);

            cnt = 0;
            for(int y = 0; y < yHeight; y++){
                for(int x = 0; x < xWidth; x++){
                    if(!check[y][x] && graph[y][x] == 1){
                        sol.solution_bfs(y, x);
                        cnt++;
                    }
                }
            }

            stb.append(cnt).append("\n");
        }

        System.out.print(stb);
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
