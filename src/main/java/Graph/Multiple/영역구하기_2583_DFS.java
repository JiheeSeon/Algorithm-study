package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class 영역구하기_2583_DFS {
    static int yLength, xWidth;
    static int[][] graph;
    static boolean[][] check;

    static int res = 0;

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
                    res = 0;
                    dfs(new Point(y, x));
                    areaList.add(res);
                    ans++;
                }
            }
        }
        StringBuilder stb = new StringBuilder();
        stb.append(ans).append("\n");

        areaList.stream().sorted().mapToInt(Integer::valueOf).forEach(x -> stb.append(x).append(" "));
        System.out.println(stb);
    }

    static void dfs(Point curr) {
        if (curr.y < 0 || curr.x < 0 || curr.y >= yLength || curr.x >= xWidth
                || check[curr.y][curr.x] || graph[curr.y][curr.x] == -1) return;

        check[curr.y][curr.x] = true;
        res++;

        for (int i = 0; i < 4; i++)
            dfs(new Point(curr.y + dy[i], curr.x + dx[i]));
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