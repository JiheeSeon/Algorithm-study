package Graph.Archive.BFS;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

/*
2178 미로탐색
- 인접한 칸으로만 이동 가능 -> 특정 기준으로 탐색
- check 배열에 distance를 업데이트하는 방식
- 최단거리인건 맞으나 BFS만으로 충분히 가능, 한번의 탐색으로 최단거리가 나옴.
*/

class 미로탐색_2178 {
    int yHeight, xWidth;
    int[][] graph;
    int[][] check;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 미로탐색_2178(int yHeight, int xWidth, int[][] graph){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.graph = graph;
        check = new int[yHeight][xWidth];
    }

    public int solution_bfs() {
        Queue<Point> q = new LinkedList<Point>();
        q.add(new Point(0, 0));
        check[0][0] = 1;

        Point now;
        int nextY, nextX;

        while(!q.isEmpty()){
            now = q.poll();

            for(int i = 0; i < 4; i++){
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth || check[nextY][nextX] != 0 || graph[nextY][nextX] == 0) continue;

                check[nextY][nextX] = check[now.y][now.x] + 1;
                q.add(new Point(nextY, nextX));
            }
        }

        return check[yHeight - 1][xWidth - 1];
    }

    private class Point{
        int y, x;

        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}

class MainA2178{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmp = strToIntArr(br.readLine(), " ");
        int yHeight = tmp[0]; int xWidth = tmp[1];
        int[][] graph = new int[yHeight][xWidth];

        for(int y = 0; y < yHeight; y++)
            graph[y] = strToIntArr(br.readLine(), "");

        System.out.println(new 미로탐색_2178(yHeight, xWidth, graph).solution_bfs());
    }
    static int[] strToIntArr(String s, String delimiter){
        return Pattern.compile(delimiter).splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}