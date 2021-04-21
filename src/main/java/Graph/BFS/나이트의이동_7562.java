package Graph.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class 나이트의이동_7562 {
    // dy, dx에만 변형이 있는 경우
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int L;
    static int[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        int[] tmp;
        Point start, target;
        for (int t = 0; t < T; t++) {
            L = Integer.parseInt(br.readLine());
            check = new int[L][L];

            tmp = strToIntArray(br.readLine());
            start = new Point(tmp[1], tmp[0]);

            tmp = strToIntArray(br.readLine());

            bfs(start);
            stb.append(check[tmp[1]][tmp[0]] - 1).append("\n");
        }
        System.out.println(stb);
    }

    static void bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        check[start.y][start.x] = 1;

        Point now;
        int nextY, nextX;

        while (!q.isEmpty()) {
            now = q.poll();

            for (int i = 0; i < 8; i++) {
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= L || nextX >= L
                        || check[nextY][nextX] != 0) continue;

                check[nextY][nextX] = check[now.y][now.x] + 1;
                q.add(new Point(nextY, nextX));
            }
        }
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    private static class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
