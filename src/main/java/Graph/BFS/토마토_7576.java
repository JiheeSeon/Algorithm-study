package Graph.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

/*문제분석
시작할 수 있는 곳이 여러 개 -> 병렬적으로 탐색해야 함.
그냥 자기 주변을 1 씩 늘리게 하면 됨.
BFS + 해당 칸에 토마토가 없을 때 탐색 멈춰
*/
class 토마토_7576 {
    static int yHeight, xWidth;
    static int[][] graph;
    static int[][] check;
    static ArrayList<Point> starts = new ArrayList<>();
    static int number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmp = strToIntArray(br.readLine());
        xWidth = tmp[0]; yHeight = tmp[1];
        check = new int[yHeight][xWidth];
        graph = new int[yHeight][xWidth];

        for (int y = 0; y < yHeight; y++) {
            graph[y] = strToIntArray(br.readLine());

            for (int x = 0; x < xWidth; x++) {
                if(graph[y][x] == 1) starts.add(new Point(y, x));
                if(graph[y][x] == -1) check[y][x] = -1;
            }
        }

        bfs();

        for (int y = 0; y < yHeight; y++) {
            for (int x = 0; x < xWidth; x++) {
                if(check[y][x] == 0){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(number - 1);
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static void bfs() {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        Queue<Point> q = new LinkedList<>();

        int startN = starts.size();

        for(Point p: starts){
            q.add(p);
            check[p.y][p.x] = 1;
        }
        number = 1;

        Point now;
        int nextY, nextX;

        while (!q.isEmpty()) {
            now = q.poll();

            for (int i = 0; i < 4; i++) {
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if (nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth
                        || graph[nextY][nextX] == -1 || check[nextY][nextX] != 0) continue;

                q.add(new Point(nextY, nextX));
                check[nextY][nextX] = check[now.y][now.x] + 1;
                number = Math.max(check[nextY][nextX], number);
            }
        }
    }

    static private class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
