package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 적록색약_10026_DFS {
    static int N;
    static char[][] graph;
    static boolean[][] check;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new char[N][N];

        for (int y = 0; y < N; y++)
            graph[y] = br.readLine().toCharArray();

        StringBuilder stb = new StringBuilder();

        check = new boolean[N][N];
        int ans = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(!check[y][x]) {
                    dfsGeneral(new Point(y, x), graph[y][x]);
                    ans++;
                }
            }
        }

        stb.append(ans).append(" ");

        check = new boolean[N][N];
        ans = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(!check[y][x]) {
                    dfsRGColor(new Point(y, x), graph[y][x]);
                    ans++;
                }
            }
        }
        stb.append(ans);
        System.out.println(stb);
    }

    static void dfsGeneral(Point now, char alphbet) {
        if(now.y < 0 || now.x < 0 || now.y >= N || now.x >= N
                || check[now.y][now.x] || graph[now.y][now.x] != alphbet)
            return;

        check[now.y][now.x] = true;

        for (int i = 0; i < 4; i++)
            dfsGeneral(new Point(now.y + dy[i], now.x + dx[i]), alphbet);
    }

    static void dfsRGColor(Point now, char alphbet) {
        if(now.y < 0 || now.x < 0 || now.y >= N || now.x >= N
                || check[now.y][now.x]) return;

        if((alphbet == 'R' || alphbet == 'G') && graph[now.y][now.x] == 'B') return;
        if(alphbet == 'B' && graph[now.y][now.x] != 'B') return;

        check[now.y][now.x] = true;

        for (int i = 0; i < 4; i++)
            dfsRGColor(new Point(now.y + dy[i], now.x + dx[i]), alphbet);
    }

    private static class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
