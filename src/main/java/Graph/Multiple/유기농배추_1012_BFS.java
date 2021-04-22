package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class 유기농배추_1012_BFS {
    // 진심 어이 없는 실수함, main에서 변수 선언 다시 해서 디버깅이 안되는 참사.
    static int yHeight;
    static int xWidth;

    static boolean[][] graph;
    static boolean[][] check;

    static int[] dy = {-1, 1, 0, 0}; // U D L R
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] tmp;

        for(int t = 0; t < T; t++) {
            tmp = strToIntArray(br.readLine()); // M N 배추 심어진 위치의 개수
            yHeight = tmp[1]; xWidth = tmp[0];
            int K = tmp[2];

            graph = new boolean[yHeight][xWidth];
            check = new boolean[yHeight][xWidth];

            for (int i = 0; i < K; i++) {
                tmp = strToIntArray(br.readLine()); // x y
                graph[tmp[1]][tmp[0]] = true;
            }

            int cnt = 0;

            for (int y = 0; y < yHeight; y++) {
                for (int x = 0; x < xWidth; x++) {
                    if(!check[y][x] && graph[y][x]) {
                        bfs(new Point(y, x));
                        cnt++;
                    }
                }
            }
            stb.append(cnt).append("\n");
        }
        System.out.println(stb);

    }

    static void bfs(Point p) {
        Queue<Point> q = new LinkedList<>();
        q.add(p);
        check[p.y][p.x] = true;

        Point now;
        int nextY, nextX;

        while (!q.isEmpty()) {
            now = q.poll();

            for (int i = 0; i < 4; i++) {
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth
                        || !graph[nextY][nextX] || check[nextY][nextX]) continue;

                check[nextY][nextX] = true;
                q.add(new Point(nextY, nextX));
            }
        }
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static private class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static void display(boolean[][] arr) {
        System.out.println();
        for (boolean[] i : arr) {
            for(boolean b : i){
                System.out.print((b ? 1 : 0 )+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
