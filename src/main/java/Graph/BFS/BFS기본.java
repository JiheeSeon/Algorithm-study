package Graph.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class BFS기본 {
    static int xWidth, yHeight;
    static int[][] graph;
    static int[][] check;
    static int[] dy = {-1, 1, 0, 0}; // U D L R
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArray(br.readLine(), " ");
        yHeight = tmp[0]; xWidth = tmp[1];
        graph = new int[yHeight][xWidth];
        check = new int[yHeight][xWidth];

        for (int h = 0; h < yHeight; h++)
            graph[h] = strToIntArray(br.readLine(), "");

        bfs();

        System.out.println(check[yHeight - 1][xWidth - 1]);
    }
    static int[] strToIntArray(String s, String delimiter){
        return Pattern.compile(delimiter).splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        check[0][0] = 1;

        Point now;
        int nextX, nextY;
        while (!q.isEmpty()) {
            now = q.poll();

            for (int i = 0; i < 4; i++) {
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextX < 0 || nextY < 0 || nextX >= xWidth || nextY >= yHeight
                        || check[nextY][nextX] != 0 || graph[nextY][nextX] == 0)
                    continue;

                check[nextY][nextX] = check[now.y][now.x] + 1;
                q.add(new Point(nextY, nextX));
            }
        }
    }

    static private class Point{
        private int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
