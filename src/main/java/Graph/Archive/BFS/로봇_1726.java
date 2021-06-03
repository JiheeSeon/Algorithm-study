package Graph.Archive.BFS;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class 로봇_1726 {
    int yHeight, xWidth;
    int[][] graph;
    boolean[][][] check;
    int[] src, dst;

    int[] dy = {-2, 0, 0, 1, -1};
    int[] dx = {-2, 1, -1, 0, 0};

    final static int Y = 0;
    final static int X = 1;
    final static int D = 2;

    final static int EAST = 1;
    final static int WEST = 2;
    final static int SOUTH = 3;
    final static int NORTH = 4;

    public 로봇_1726(int yHeight, int xWidth, int[][] graph, int[] src, int[] dst){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.graph = graph;
        check = new boolean[yHeight][xWidth][5];

        this.src = new int[3];
        for(int i = 0; i < 2; i++){
            this.src[i] = src[i] - 1;
        }
        this.src[2] = src[2];

        this.dst = new int[3];
        for(int i = 0; i < 2; i++){
            this.dst[i] = dst[i] - 1;
        }
        this.dst[2] = dst[2];
    }

    int getAns(){
        Queue<Vector> q = new LinkedList<>();
        q.add(new Vector(src[0], src[1], src[2], 1));
        check[src[0]][src[1]][src[2]] = true;

        Vector now;
        int nextY, nextX;

        while(!q.isEmpty()){
            now = q.poll();

            if(now.y == dst[0] && now.x == dst[1] && now.d == dst[2])
                return now.distance - 1;

            // 명령 1. 방향 전환
            for(int newD = 1; newD <= 4; newD++){
                if(newD == now.d) continue;

                int toAdd
                        = ((newD == EAST && now.d == WEST) || (newD == WEST && now.d == EAST)
                        || (newD == SOUTH && now.d  == NORTH) || (newD == NORTH && now.d == SOUTH))
                        ? 2 : 1;

                if(check[now.y][now.x][newD]) continue;

                check[now.y][now.x][newD] = true;
                q.add(new Vector(now.y, now.x, newD, now.distance + toAdd));
            }

            // 명령 2. 같은 방향으로 1-3만큼 갈 수 있다.
            for(int i = 1; i <= 3; i++){
                nextY = now.y + dy[now.d] * i;
                nextX = now.x + dx[now.d] * i;

                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth) continue;
                if(graph[nextY][nextX] == 1) break;
                if(check[nextY][nextX][now.d]) continue;

                check[nextY][nextX][now.d] = true;
                q.add(new Vector(nextY, nextX, now.d, now.distance + 1));
            }
        }
        return -1;
    }

    private class Vector{
        int y, x, d, distance;

        public Vector(int y, int x, int d, int distance) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.distance = distance;
        }
    }
}

class MainA1726 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int yHeight = tmp[0]; int xWidth = tmp[1];

        int[][] graph = new int[yHeight][xWidth];
        for(int y = 0; y < yHeight; y++){
            graph[y] = strToIntArr(br.readLine());
        }
        int[] src = strToIntArr(br.readLine());
        int[] dst = strToIntArr(br.readLine());

        System.out.println(new 로봇_1726(yHeight, xWidth, graph, src, dst).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
