package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class 섬의개수_4963_BFS {
    static int[][] graph;
    static boolean[][] check;
    static int yHeight, xWidth;

    final static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    final static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        String input;
        int[] tmp;

        while (!(input = br.readLine()).equals("0 0")) {
            tmp = strToIntArray(input);
            yHeight = tmp[1]; xWidth = tmp[0];

            graph = new int[yHeight][xWidth];
            check = new boolean[yHeight][xWidth];

            for (int y = 0; y < yHeight; y++) {
                graph[y] = strToIntArray(br.readLine());
            }

            int result = 0;
            for (int y = 0; y < yHeight; y++) {
                for (int x = 0; x < xWidth; x++) {
                    if(graph[y][x] == 1 && !check[y][x]){
                        bfs(new Point(y, x));
                        result++;
                    }
                }
            }
            stb.append(result).append("\n");
        }
        System.out.println(stb);
    }

    static void bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        check[start.y][start.x] = true;

        Point now;
        while(!q.isEmpty()){
            now = q.poll();

            int nextY, nextX;
            for(int i = 0; i < 8; i++){
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth
                        || graph[nextY][nextX] == 0 || check[nextY][nextX])
                    continue;

                q.add(new Point(nextY, nextX));
                check[nextY][nextX] = true;
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
