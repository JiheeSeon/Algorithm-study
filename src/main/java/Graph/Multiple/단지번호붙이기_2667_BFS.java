package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class 단지번호붙이기_2667_BFS {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] graph;
    static int[][] check;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int aptNum = 0;
    static Map<Integer, Integer> aptStatus = new HashMap<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        setGraph();

        check = new int[N][N];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(graph[y][x] == 1 && check[y][x] == 0) bfs(new Point(y, x));
            }
        }

        StringBuilder stb = new StringBuilder();
        stb.append(aptNum).append("\n");
        aptStatus.values().stream().sorted().forEach(x -> stb.append(x.intValue()).append("\n"));
        System.out.print(stb);
    }

    static void bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        check[start.y][start.x] = ++aptNum;

        aptStatus.put(aptNum, 1);

        Point now;
        int nextY, nextX;
        while (!q.isEmpty()) {
            now = q.poll();

            for (int i = 0; i < 4; i++) {
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= N
                        || graph[nextY][nextX] == 0
                        || check[nextY][nextX] != 0) continue;

                check[nextY][nextX] = aptNum;
                q.add(new Point(nextY, nextX));
                aptStatus.put(aptNum, aptStatus.get(aptNum) + 1);
            }
        }
    }

    static private class Point {
        private int y, x;


        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static void setGraph() throws IOException {
        graph = new int[N][N];

        for (int i = 0; i < N; i++)
            graph[i] = Pattern.compile("").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }
}