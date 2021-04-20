package Graph.Both;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class 단지번호붙이기_2667_DFS {
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

        int apt = 1;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(graph[y][x] == 1 && check[y][x] == 0)
                    dfs(new Point(y, x), apt++);
            }
        }

        StringBuilder stb = new StringBuilder();
        stb.append(apt - 1).append("\n");
        aptStatus.values().stream().sorted().forEach(x -> stb.append(x.intValue()).append("\n"));
        System.out.print(stb);
    }

    static void dfs(Point now, int apt) {
        if(now.y < 0 || now.x < 0 || now.y >= N || now.x >= N
                || check[now.y][now.x] != 0 || graph[now.y][now.x] == 0) return;

        check[now.y][now.x] = apt;
        aptStatus.put(apt, aptStatus.getOrDefault(apt, 0) + 1);

        int nextY, nextX;

        for (int i = 0; i < 4; i++) {
            nextY = now.y + dy[i];
            nextX = now.x + dx[i];

            dfs(new Point(nextY, nextX), apt);
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
