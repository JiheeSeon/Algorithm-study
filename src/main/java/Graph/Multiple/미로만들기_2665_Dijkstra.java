package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

//되도록 적은 수의 방의 색을 바꾸고 싶다. -> 최소 가중치

class 미로만들기_2665_Dijkstra {
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    int[][] room;
    int[][] dst;
    int N;

    public 미로만들기_2665_Dijkstra(int[][] room, int n) {
        this.room = room;
        N = n;
        dst = new int[N][N];
        for(int i = 0; i < N; i++)
            Arrays.fill(dst[i], Integer.MAX_VALUE);
    }

    int solution() {
        PriorityQueue<Point2665_Dijkstra> pq = new PriorityQueue<>();
        pq.offer(new Point2665_Dijkstra(0, 0));
        dst[0][0] = 0;

        Point2665_Dijkstra now;
        int nextY, nextX;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for (int i = 0; i < 4; i++) {
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) continue;

                if(dst[nextY][nextX] > dst[now.y][now.x] + (1 - room[nextY][nextX])){
                    dst[nextY][nextX] = dst[now.y][now.x] + (1 - room[nextY][nextX]);
                    pq.add(new Point2665_Dijkstra(nextY, nextX));
                }
            }
        }

        for (int y = 0; y < N; y++) {
            System.out.println(Arrays.toString(dst[y]));
        }

        return dst[N - 1][N - 1];
    }
}
class MainA2665_Dijkstra {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] room = new int[N][N];

        for(int n = 0; n < N; n++)
            room[n] = splitIntoIntArray(br.readLine());

        System.out.println(new 미로만들기_2665_Dijkstra(room, N).solution());
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile("").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}

class Point2665_Dijkstra implements Comparable<Point2665_Dijkstra> {
    int y, x;

    public Point2665_Dijkstra(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public int compareTo(Point2665_Dijkstra o) {
        return (o.y == y) ? Integer.compare(x, o.x) : Integer.compare(y, o.y);
    }
}