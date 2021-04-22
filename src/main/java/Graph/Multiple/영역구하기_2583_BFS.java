package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class 영역구하기_2583_BFS {
    static int yLength, xWidth;
    static int[][] graph;
    static boolean[][] check;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static List<Integer> areaList = new ArrayList<>(); // 처음에 set 썼다가 중복 제거되어 틀림

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmp = strToIntArray(br.readLine());
        yLength = tmp[0]; xWidth = tmp[1];
        int K = tmp[2];

        graph = new int[yLength][xWidth];
        check = new boolean[yLength][xWidth];

        for (int k = 0; k < K; k++) {
            tmp = strToIntArray(br.readLine());

            for(int y = tmp[1]; y < tmp[3]; y++) {
                for (int x = tmp[0]; x < tmp[2]; x++) {
                    graph[y][x] = -1;
                }
            }
        }

        int ans = 0;
        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xWidth; x++) {
                if(!check[y][x] && graph[y][x] != -1) {
                    bfs(new Point(y, x)); ans++;
                }
            }
        }
        StringBuilder stb = new StringBuilder();
        stb.append(ans).append("\n");

        areaList.stream().sorted().mapToInt(Integer::valueOf).forEach(x -> stb.append(x).append(" "));
        System.out.println(stb);
    }

    static void bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        check[start.y][start.x] = true;
        int area = 1;

        Point now;
        int nextY, nextX;

        while (!q.isEmpty()) {
            now = q.poll();

            for (int i = 0; i < 4; i++) {
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= yLength || nextX >= xWidth
                        || check[nextY][nextX] || graph[nextY][nextX] == -1)
                    continue;

                check[nextY][nextX] = true;
                area++;
                q.add(new Point(nextY, nextX));
            }
        }

        areaList.add(area);
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
}