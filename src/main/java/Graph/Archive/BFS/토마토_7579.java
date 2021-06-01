package Graph.Archive.BFS;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/*
7579 토마토

z축이 증가해서 dz가 생긴다는 것 말고는 7576 토마토 문제와 큰 차이 없음.
*/

class 토마토_7579 {
    int zHeight, yLength, xWidth;
    int[][][] graph;
    int[][][] check;
    ArrayList<int[]> starts;

    int[] dz = {0, 0, 0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0, 0, 0};
    int[] dx = {0, 0, -1, 1, 0, 0};

    public 토마토_7579(int zHeight, int yLength, int xWidth, int[][][] graph, ArrayList<int[]> starts){
        this.zHeight = zHeight;
        this.yLength = yLength;
        this.xWidth = xWidth;
        this.graph = graph;
        check = new int[zHeight][yLength][xWidth];
        this.starts = starts;
    }

    int solution_bfs(){
        Queue<Point> q = new LinkedList<>();
        for(int[] startP: starts){
            q.add(new Point(startP[0], startP[1], startP[2]));
            check[startP[0]][startP[1]][startP[2]] = 1;
        }

        Point now;
        int nextZ, nextY, nextX;

        int daysToRipe = 0;

        while(!q.isEmpty()){
            now = q.poll();

            for(int i = 0; i < 6; i++){
                nextZ = now.z + dz[i];
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextZ < 0 || nextY < 0 || nextX < 0 || nextZ >= zHeight || nextY >= yLength || nextX >= xWidth || graph[nextZ][nextY][nextX] != 0 || check[nextZ][nextY][nextX] != 0) continue;

                check[nextZ][nextY][nextX] = check[now.z][now.y][now.x] + 1;
                daysToRipe = check[nextZ][nextY][nextX];
                q.add(new Point(nextZ, nextY, nextX));
            }
        }

        return hasUnreachablePoint() ? -1 : daysToRipe - 1;
    }

    boolean hasUnreachablePoint(){
        for(int z = 0; z < zHeight; z++){
            for(int y = 0; y < yLength; y++){
                for(int x = 0; x < xWidth; x++){
                    if(check[z][y][x] == 0 && graph[z][y][x] == 0) return true;
                }
            }
        }
        return false;
    }

    private class Point{
        int z; int y; int x;

        public Point(int z, int y, int x){
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }
}


class MainA7579 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int zHeight = tmp[2]; int yLength = tmp[1]; int xWidth = tmp[0];
        int[][][] graph = new int[zHeight][yLength][xWidth];

        ArrayList<int[]> starts = new ArrayList<>();
        boolean zeroFlag = true;

        for(int z = 0; z < zHeight; z++){
            for(int y = 0; y < yLength; y++){
                graph[z][y] = strToIntArr(br.readLine());

                for(int x = 0; x < xWidth; x++){
                    if(graph[z][y][x] == 1){
                        starts.add(new int[]{z, y, x});
                    } else if (graph[z][y][x] == 0)
                        zeroFlag = false;
                }
            }
        }

        if(zeroFlag){
            System.out.println(0);
            return;
        }

        System.out.println(new 토마토_7579(zHeight, yLength, xWidth, graph, starts).solution_bfs());
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}