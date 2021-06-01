package Graph.Archive.BFS;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/*
7576 토마토

저장될 때부터 모든 토마토가 익어있는 상태 -> 0
체크 시점 :: 미리 입력을 받았을 때
토마토가 모두 익지 못하는 상황 -> -1
체크 시점 :: 탐색 후 unreachable point를 체크하는 것
*/

class 토마토_7576 {
    int yHeight, xWidth;
    int[][] graph;
    int[][] check;
    ArrayList<int[]> starts;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 토마토_7576(int yHeight, int xWidth, int[][] graph, ArrayList<int[]> starts){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.graph = graph;
        check = new int[yHeight][xWidth];
        this.starts = starts;
    }

    int solution_bfs(){
        Queue<Point> q = new LinkedList<>();
        for(int[] startP: starts){
            q.add(new Point(startP[0], startP[1]));
            check[startP[0]][startP[1]] = 1;
        }

        Point now;
        int nextY, nextX;

        int daysToRipe = 0;

        while(!q.isEmpty()){
            now = q.poll();

            for(int i = 0; i < 4; i++){
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth
                        || graph[nextY][nextX] != 0 || check[nextY][nextX] != 0) continue;

                check[nextY][nextX] = check[now.y][now.x] + 1;
                daysToRipe = check[nextY][nextX];
                q.add(new Point(nextY, nextX));
            }
        }

        return hasUnreachablePoint() ? -1 : daysToRipe - 1;
    }

    boolean hasUnreachablePoint(){
        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                if(check[y][x] == 0 && graph[y][x] == 0) return true; // 쉽게 하는 실수 :: graph value check X
            }
        }
        return false;
    }

    private class Point{
        int y; int x;

        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}


class MainA7576 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int yHeight = tmp[1]; int xWidth = tmp[0];
        int[][] graph = new int[yHeight][xWidth];

        ArrayList<int[]> starts = new ArrayList<>();
        boolean zeroFlag = true;

        for(int y = 0; y < yHeight; y++){
            graph[y] = strToIntArr(br.readLine());

            for(int x = 0; x < xWidth; x++){
                if(graph[y][x] == 1){
                    starts.add(new int[]{y, x});
                } else if (graph[y][x] == 0)
                    zeroFlag = false;
            }
        }

        if(zeroFlag){
            System.out.println(0);
            return;
        }

        System.out.println(new 토마토_7576(yHeight, xWidth, graph, starts).solution_bfs());
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}