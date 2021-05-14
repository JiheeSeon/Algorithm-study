package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

class 알고스팟_1261 {
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};
    int[][] graph;
    int yHeight, xWidth;
    int[][] distance;

    public 알고스팟_1261(int[][] graph, int yHeight, int xWidth) {
        this.graph = graph;
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        distance = new int[yHeight][xWidth];
        for(int[] i : distance)
            Arrays.fill(i, Integer.MAX_VALUE);
        distance[0][0] = 0;
    }

    int solution() {
        PriorityQueue<MainA1261.Point> pq = new PriorityQueue<>();
        pq.offer(new MainA1261.Point(0, 0, graph[0][0]));

        MainA1261.Point now;
        int nextY, nextX;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for(int i = 0; i < 4; i++){
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth)
                    continue;

                if(distance[nextY][nextX] > distance[now.y][now.x] + graph[nextY][nextX]) {
                    distance[nextY][nextX] = distance[now.y][now.x] + graph[nextY][nextX];
                    pq.add(new MainA1261.Point(nextY, nextX, distance[nextY][nextX]));
                }
            }
        }

        return distance[yHeight - 1][xWidth - 1];
    }
}
class MainA1261 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = splitIntoIntArray(br.readLine(), " ");
        int yHeight = tmp[1]; int xWidth = tmp[0];
        int[][] graph = new int[yHeight][xWidth];
        for (int y = 0; y < yHeight; y++)
            graph[y] = splitIntoIntArray(br.readLine(), "");

        System.out.println(new 알고스팟_1261(graph, yHeight, xWidth).solution());
    }

    static int[] splitIntoIntArray(String s, String delimiter) {
        return Pattern.compile(delimiter).splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static class Point implements Comparable<Point>{
        int y, x, w;

        public Point(int y, int x, int w) {
            this.y = y;
            this.x = x;
            this.w = w;
        }

        @Override
        public int compareTo(Point o) {
            return w == o.w ? Integer.compare(y, o.y) : Integer.compare(w, o.w);
        }
    }
}
