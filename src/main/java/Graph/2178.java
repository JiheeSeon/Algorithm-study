package Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class Main2178 {
    static int[][] graph;
    static int[][] check;

    static int numberOfRow;
    static int numberOfColumn;

    static int[] dy = {1, -1, 0, 0}; //하상좌우
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();

        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        numberOfRow = input[0];
        numberOfColumn = input[1];

        graph = new int[numberOfRow][numberOfColumn];
        check = new int[numberOfRow][numberOfColumn];

        for (int i = 0; i < numberOfRow; i++)
            graph[i] = Pattern.compile("").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(numberOfColumn).toArray();
        
        bfs();

        bw.write(Integer.toString(check[numberOfRow - 1][numberOfColumn - 1]));
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        check[0][0] = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];

                if (nextY < 0 || nextX < 0 || nextY >= numberOfRow || nextX >= numberOfColumn
                        || graph[nextY][nextX] == 0
                        || check[nextY][nextX] != 0) continue;

                check[nextY][nextX] = check[now.y][now.x] + 1;
                q.add(new Point(nextY, nextX));
            }
        }

//        for(int i = 0; i < numberOfRow; i++) {
//            for (int j = 0; j < numberOfColumn; j++) {
//                System.out.print(check[i][j]);
//            }
//            System.out.println();
//        }
    }

    static private class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}