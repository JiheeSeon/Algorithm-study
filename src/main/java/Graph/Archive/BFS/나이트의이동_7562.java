package Graph.Archive.BFS;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class 나이트의이동_7562{
    int L;
    Point src, dst;
    int[][] check;

    int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};

    public 나이트의이동_7562(int L, int[] srcL, int[] dstL){
        this.L = L;
        src = new Point(srcL[0], srcL[1]);
        dst = new Point(dstL[0], dstL[1]);
        check = new int[L][L];
    }

    int solution_bfs(){
        Queue<Point> q = new LinkedList<>();
        q.add(src);
        check[src.y][src.x] = 1;

        Point now;
        int nextY, nextX;
        while(!q.isEmpty()){
            now = q.poll();

            for(int i = 0; i < 8; i++){
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= L || nextX >= L || check[nextY][nextX] != 0) continue;

                check[nextY][nextX] = check[now.y][now.x] + 1;
                q.add(new Point(nextY, nextX));
            }
        }

        return check[dst.y][dst.x] - 1;
    }

    private class Point{
        int y, x;

        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}

class MainA7562{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int L;
        int[] src; int[] dst;

        StringBuilder stb = new StringBuilder();

        for(int t = 0; t < T; t++){
            L = Integer.parseInt(br.readLine());
            src = strToIntArr(br.readLine());
            dst = strToIntArr(br.readLine());
            stb.append(new 나이트의이동_7562(L, src, dst).solution_bfs()).append("\n");
        }
        System.out.print(stb);
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}