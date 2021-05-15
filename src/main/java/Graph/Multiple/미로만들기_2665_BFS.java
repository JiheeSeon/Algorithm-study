package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class 미로만들기_2665_BFS {
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    int[][] room;
    int[][] check;
    int N;

    public 미로만들기_2665_BFS(int[][] room, int n) {
        this.room = room;
        N = n;
        check = new int[N][N];
        for(int i = 0; i < N; i++)
            Arrays.fill(check[i], Integer.MAX_VALUE);
        check[0][0] = 0;
    }

    int solution() {
        Queue<Point2665_Dijkstra> q = new LinkedList<>();
        q.offer(new Point2665_Dijkstra(0, 0));

        Point2665_Dijkstra now;
        int nextY, nextX;
        while (!q.isEmpty()) {
            now = q.poll();

            for (int i = 0; i < 4; i++) {
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) continue;

                if(room[nextY][nextX] == 1 && check[nextY][nextX] > check[now.y][now.x]){
                    check[nextY][nextX] = check[now.y][now.x];
                    q.add(new Point2665_Dijkstra(nextY, nextX));
                }
                else if(room[nextY][nextX] == 0 && check[nextY][nextX] > check[now.y][now.x] + 1) {
                    check[nextY][nextX] = check[now.y][now.x] + 1;
                    q.add(new Point2665_Dijkstra(nextY, nextX));
                }
            }
        }
        return check[N - 1][N - 1];
    }
}
class MainA2665_BFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] room = new int[N][N];

        for(int n = 0; n < N; n++)
            room[n] = splitIntoIntArray(br.readLine());

        System.out.println(new 미로만들기_2665_BFS(room, N).solution());
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile("").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}

class Point_BFS {
    int y, x;

    public Point_BFS(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
/*
8
11100110
11010010
10011010
11101100
01000111
00110001
11011000
11000111
[0, 0, 0, 1, 2, 2, 2, 3]
[0, 0, 1, 1, 2, 3, 2, 3]
[0, 1, 1, 1, 1, 2, 2, 3]
[0, 0, 0, 1, 1, 1, 2, 2]
[1, 0, 1, 2, 2, 1, 1, 1]
[2, 1, 1, 1, 2, 2, 2, 1]
[1, 1, 2, 1, 1, 2, 3, 2]
[1, 1, 2, 2, 2, 2, 2, 2]
2

Process finished with exit code 0

*/