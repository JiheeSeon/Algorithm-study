package Tree.MST;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 복제로봇_1944 {
    int N, K;
    char[][] maze;
    int[][] check;

    PointA1944 startCoordinate;
    PriorityQueue<PointA1944> pq;

    final int[] dy = {-1, 1, 0, 0};
    final int[] dx = {0, 0, -1, 1};

    public 복제로봇_1944(int n, int k, char[][] maze, PointA1944 startCoordinate) {
        N = n;
        K = k;
        this.maze = maze;
        check = new int[N][N];
        this.startCoordinate = startCoordinate;
    }

    int solve() {
        pq = new PriorityQueue<>();
        pq.add(new PointA1944(startCoordinate.y, startCoordinate.x, 0));
        int ans = 0;

        PointA1944 p;
        Set<PointA1944> set = new HashSet<>();
        while (set.size() < K + 1) {
            if(pq.isEmpty()) return -1;

            p = pq.poll();
            if(set.contains(p)) continue;

            dfs(p.y, p.x, p.w);
            set.add(p);
            ans += p.w;
        }

        return ans;
    }

    void dfs(int y, int x, int w){
        if(y < 0 || x < 0 || y >= N || x >= N || maze[y][x] == '1' || (check[y][x] != 0 && check[y][x] < w)) return;

        check[y][x] = w;
        if(maze[y][x] == 'K') pq.add(new PointA1944(y, x, w));

        for(int i = 0; i < 4; i++){
            dfs(y + dy[i], x + dx[i], w + 1);
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

class PointA1944 implements Comparable<PointA1944>{
    int y, x, w;

    public PointA1944(int y, int x, int w) {
        this.y = y;
        this.x = x;
        this.w = w;
    }

    @Override
    public int compareTo(PointA1944 o) {
        return Integer.compare(w, o.w);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointA1944 that = (PointA1944) o;
        return y == that.y && x == that.x && w == that.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x, w);
    }

    @Override
    public String toString() {
        return "PointA1944{" +
                "y=" + y +
                ", x=" + x +
                ", w=" + w +
                '}';
    }
}

class MainA1944 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int N = tmp[0]; int K = tmp[1];

        PointA1944 startCoordinate = null;
        char[][] maze = new char[N][N];
        for(int y = 0; y < N; y++){
            maze[y] = br.readLine().toCharArray();
            for(int x = 0; x < N; x++)
                if(maze[y][x] == 'S') startCoordinate = new PointA1944(y, x, 0);
        }

        System.out.println(new 복제로봇_1944(N, K, maze, startCoordinate).solve());
    }
}