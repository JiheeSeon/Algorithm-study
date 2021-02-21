package Resolve;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class Main2178Resolve {
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[] input;
    static int yHeight, xWidth;
    static int dist = 0;
    static int[][] graph;
    static int[][] isInQueue;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        input = processInput(" ", 2);
        yHeight = input[0];
        xWidth = input[1];
        graph = new int[yHeight][xWidth];
        isInQueue = new int[yHeight][xWidth];

        for (int y = 0; y < yHeight; y++)
            graph[y] = processInput("", xWidth);

        bfs();

        bw.write(Integer.toString(isInQueue[yHeight - 1][xWidth - 1]));
        bw.flush();
        bw.close();

    }

    static int[] processInput(String splitString, int limitSize) throws IOException {
        return Pattern.compile(splitString).splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(limitSize).toArray();
    }

    static void bfs() {
        Queue<Point> willVisit = new LinkedList<>();
        willVisit.add(new Point(0, 0));
        isInQueue[0][0] = 1;
        int temp;

        Point visiting;
        int k;
        int nowY, nowX, nextY, nextX;

        while(!willVisit.isEmpty()){
            visiting = willVisit.poll();
            nowY = visiting.y; nowX = visiting.x;

//            System.out.println("visiting = " + visiting);
            temp = 0;
            for (k = 0; k < 4; k++) {
               nextY = nowY + dy[k];  nextX = nowX + dx[k];

               if (!(nextY < 0 || nextY >= yHeight || nextX < 0 || nextX >= xWidth)) {
                   if (isInQueue[nextY][nextX] == 0 && graph[nextY][nextX] != 0) {
                       if (temp == 0) temp = isInQueue[nowY][nowX] + 1;
                       willVisit.add(new Point(nextY, nextX));
                       isInQueue[nextY][nextX] = temp;

//                       System.out.println("next added one = (y: " + nextY + ", x: " + nextX + ")");
//                       System.out.println("dist = " + dist);
                   }
               }
           }
        }
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public String toString(){
            return "(y: " + y + ", x: " + x + ")";
        }
    }
}