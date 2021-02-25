package Graph.Resolve;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class Main7576Resolve {
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int yHeight, xWidth;
    static int[][] graph;
    static int[][] visited;
    static Queue<Point> willVisit = new LinkedList<>();

    static int[] input;
    static int output;
    static boolean containsZero = false;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public String toString() {
            return "(y: " + y + ", x: " + x + ")";
        }
    }

    public static void main(String[] args) throws IOException {
        input = processInput(2);
        yHeight = input[1];
        xWidth = input[0];

        graph = new int[yHeight][xWidth];
        visited = new int[yHeight][xWidth];

        for (int h = 0; h < yHeight; h++)
            graph[h] = processInput(xWidth);

        bfs();

        for (int y = 0; y < yHeight; y++) {
            for (int x = 0; x < xWidth; x++) {
                System.out.print(visited[y][x] + " ");
                if (visited[y][x] == 0)
                    output = -1;
                if (graph[y][x] == 0)
                    containsZero = true;
            }
            System.out.println();
        }


        if (!containsZero)
            bw.write("0");
        else if (output == -1)
            bw.write("-1");
        else
            bw.write(Integer.toString(visited[0][0] - 1));

        bw.flush();
        bw.close();
    }

    static int[] processInput(int limitSize) throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(limitSize).toArray();
    }

    static void bfs() {
        int temp, nowX, nowY, nextX, nextY;
        Point visiting;

        willVisit.add(new Point(yHeight - 1, xWidth - 1));
        visited[yHeight - 1][xWidth - 1] = 1;

        while (!willVisit.isEmpty()) {
            temp = 0;

            visiting = willVisit.poll();
            nowX = visiting.x;
            nowY = visiting.y;
            System.out.println("visiting = " + visiting);

            for (int i = 0; i < 4; i++) {
                nextX = nowX + dx[i]; nextY = nowY + dy[i];

                if (!(nextX < 0 || nextX >= xWidth || nextY < 0 || nextY >= yHeight)) {
                    if(graph[nextY][nextX] == -1) {
                        visited[nextY][nextX] = -1;
                    }
                    else if (visited[nextY][nextX] == 0) {
                        if (temp == 0) temp = visited[nowY][nowX] + 1;
                        willVisit.add(new Point(nextY, nextX));
                        visited[nextY][nextX] = temp;
                        System.out.println("next = (" + nextY + "," + nextX +") " + visited[nextY][nextX]);
                    }
                }
            }
        }
    }
}