package Tree.MST;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

class 복제로봇_1944 {
    int N, K;
    char[][] maze;
    int[][] check;
    int[] parent;

    Point startCoordinate;
    LinkedList<Point> keyLocations;

    final int[] dy = {-1, 1, 0, 0};
    final int[] dx = {0, 0, -1, 1};


    public 복제로봇_1944(int n, int k, char[][] maze, Point startCoordinate, LinkedList<Point> keyLocations) {
        N = n;
        K = k;
        this.maze = maze;
        this.startCoordinate = startCoordinate;
        this.keyLocations = keyLocations;
        parent = IntStream.rangeClosed(0, K).toArray();
    }

    int solve() {
        PriorityQueue<KruskalEdgeIW> pq = new PriorityQueue<>();
        keyLocations.addFirst(startCoordinate);

        for (int idx = 0; idx < keyLocations.size() - 1; idx++) {
            check = new int[N][N];
            check[keyLocations.get(idx).y][keyLocations.get(idx).x] = 1;

            bfs(keyLocations.get(idx));

            for (int other = idx + 1; other < keyLocations.size(); other++) {
                pq.add(new KruskalEdgeIW(idx, other, check[keyLocations.get(other).y][keyLocations.get(other).x] - 1));
            }
        }

        int cnt = 0;
        int ans = 0;
        KruskalEdgeIW e;

        while (cnt < K) {
            if(pq.isEmpty()) return -1;

            e = pq.poll();

            if(!union(e.v1, e.v2)) continue;

            cnt++;
            ans += e.w;
        }

        return ans;
    }

    boolean union(int a, int b){
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return false;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }
    int find(int a) {
        return parent[a] == a ? a : (parent[a] = find(parent[a]));
    }

    void bfs(Point a) {
        Queue<Point> q = new LinkedList<>();
        q.add(a);

        Point now;
        int nextY, nextX;
        while (!q.isEmpty()) {
            now = q.poll();

            for(int i = 0; i < 4; i++){
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= N
                        || check[nextY][nextX] != 0 || maze[nextY][nextX] == '1') continue;

                check[nextY][nextX] = check[now.y][now.x] + 1;
                q.add(new Point(nextY, nextX));
            }
        }
    }

    void display(int[][] arr) {
        for(int[] i : arr){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}

class MainA1944 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int N = tmp[0]; int K = tmp[1];

        Point startCoordinate = null;
        LinkedList<Point> keyLocations = new LinkedList<>();
        char[][] maze = new char[N][N];
        for(int y = 0; y < N; y++){
            maze[y] = br.readLine().toCharArray();
            for(int x = 0; x < N; x++) {
                if (maze[y][x] == 'S') startCoordinate = new Point(y, x);
                if (maze[y][x] == 'K') keyLocations.add(new Point(y, x));
            }
        }

        System.out.println(new 복제로봇_1944(N, K, maze, startCoordinate, keyLocations).solve());
    }
}