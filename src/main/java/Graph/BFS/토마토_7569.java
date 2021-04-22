package Graph.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Pattern;

class 토마토_7569 {
    static int zHeight, yLength, xWidth;

    static int[][][] graph; // zHeight
    static int[][][] check;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] dz = {-1, 1};

    static Set<Point> startPoints = new HashSet<>();
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmp = strToIntArray(br.readLine());
        zHeight = tmp[2]; yLength = tmp[1]; xWidth = tmp[0];

        graph = new int[zHeight][yLength][xWidth];
        check = new int[zHeight][yLength][xWidth];

        Set<Integer> ripeCheck = new HashSet<>();

        for (int z = 0; z < zHeight; z++) {
            for (int y = 0; y < yLength; y++) {
                graph[z][y] = strToIntArray(br.readLine());

                for(int x = 0; x < xWidth; x++){
                    if(graph[z][y][x] == 1) startPoints.add(new Point(z, y, x));
                    if(graph[z][y][x] == -1) check[z][y][x] = -1;
                    ripeCheck.add(graph[z][y][x]);
                }
            }
        }

        if(!ripeCheck.contains(0) && ripeCheck.contains(1)) {
            System.out.println("0");
            return;
        }

        if(ripeCheck.size() == 1 && ripeCheck.contains(-1)){
            System.out.println("0");
            return;
        }

        bfs();

        boolean flag = true;

        for (int z = 0; z < zHeight; z++) {
            for (int y = 0; y < yLength; y++) {
                for (int x = 0; x < xWidth; x++) {
                    if(check[z][y][x] == 0){
                        flag = false;
                        break;
                    }
                }
            }
        }

        System.out.println(flag ? ans - 1 : -1);
    }

    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        for(Point p : startPoints) {
            q.add(p);
            check[p.z][p.y][p.x] = 1;
        }

        Point now;
        int nextZ, nextY, nextX;

        while (!q.isEmpty()) {
            now = q.poll();

            for (int i = 0; i < 4; i++) {
                nextZ = now.z;
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0
                        || nextY >= yLength || nextX >= xWidth
                        || check[nextZ][nextY][nextX] != 0 || graph[nextZ][nextY][nextX] != 0)
                    continue;

                q.add(new Point(nextZ, nextY, nextX));
                check[nextZ][nextY][nextX] = check[now.z][now.y][now.x] + 1;
                ans = Math.max(check[nextZ][nextY][nextX], ans);
            }

            for (int z = 0; z < 2; z++) {
                nextZ = now.z + dz[z];
                nextY = now.y;
                nextX = now.x;

                if(nextZ < 0 || nextZ >= zHeight
                        || check[nextZ][nextY][nextX] != 0 || graph[nextZ][nextY][nextX] != 0)
                    continue;

                q.add(new Point(nextZ, nextY, nextX));
                check[nextZ][nextY][nextX] = check[now.z][now.y][now.x] + 1;
                ans = Math.max(check[nextZ][nextY][nextX], ans);
            }
        }
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    private static class Point{
        int z, y, x;

        public Point(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }

    static void displayCheck() {
        System.out.println("display start");
        for (int z = 0; z < zHeight; z++) {
            for (int y = 0; y < yLength; y++) {
                for (int x = 0; x < xWidth; x++) {
                    System.out.print(check[z][y][x] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }
}
/*

3 3 1
0 0 0
0 0 1
1 0 0

output : 2


4 5 2
0 1 0 0
0 1 -1 1
-1 -1 1 -1
0 0 0 0
-1 -1 0 -1
-1 1 0 -1
0 0 -1 1
-1 0 1 0
0 0 0 -1
0 0 1 -1

output : 3


5 5 2
1 -1 1 -1 1
0 0 -1 -1 1
0 -1 -1 1 0
0 -1 0 0 1
0 0 1 -1 1
1 -1 -1 -1 -1
0 -1 0 1 1
0 1 0 0 -1
-1 -1 -1 -1 -1
-1 0 0 1 -1

output : 3
*/