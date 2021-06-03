package Graph.Archive.BFS;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class Solution{
    int yHeight, xWidth;
    int[][] graph;
    int[][][] check;
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

    public Solution(int yHeight, int xWidth, int[][] graph, int[] src, int[] dst){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.graph = graph;
        check = new int[yHeight][xWidth][5];

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
        bfs();
        return check[dst[Y]][dst[X]][dst[D]] - 1;
    }

    void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(src);
        check[src[0]][src[1]][src[2]] = 1;

        int[] now;
        int nextY, nextX;

        while(!q.isEmpty()){
            now = q.poll();

            // 명령 1. 방향 전환
            for(int d = 1; d <= 4; d++){
                if(d == now[D]) continue;

                int toAdd = ((d == EAST && now[D] == WEST) || (d == WEST && now[D] == EAST)
                        || (d == SOUTH && now[D] == NORTH) || (d == NORTH && now[D] == SOUTH))
                        ? 2 : 1;

                if((check[now[Y]][now[X]][d] != 0 && check[now[Y]][now[X]][d] < check[now[Y]][now[X]][now[D]] + toAdd))
                    continue;

                check[now[Y]][now[X]][d] = check[now[Y]][now[X]][now[D]] + toAdd;
                q.add(new int[]{now[Y], now[X], d});
            }

            // 명령 2. 같은 방향으로 1-3만큼 갈 수 있다.
            for(int i = 1; i <= 3; i++){
                nextY = now[Y] + dy[now[D]] * i;
                nextX = now[X] + dx[now[D]] * i;

                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth
                        || (check[nextY][nextX][now[D]] != 0 && check[nextY][nextX][now[D]] < check[now[Y]][now[X]][now[D]] + 1))
                    continue;

                if(graph[nextY][nextX] == 1) break;

                check[nextY][nextX][now[D]] = check[now[Y]][now[X]][now[D]] + 1;
                q.add(new int[]{nextY, nextX, now[D]});
            }
        }
    }
}

class Main{
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

        System.out.println(new Solution(yHeight, xWidth, graph, src, dst).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}