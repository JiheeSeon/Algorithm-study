package Backtrack.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;

/*N Queen 과 유사한 문제*/
class 비숍_1799 {
    int N;
    int[][] graph;
    boolean[][] check;

    public 비숍_1799(int N, int[][] graph) {
        this.N = N;
        this.graph = graph;
        check = new boolean[N][N];
    }

    int backtrack(int nowYX, int cnt, boolean startsFromZero) {
        if (nowYX >= N * N) return cnt;

        int nowY = nowYX / N;
        int nowX = nowYX % N;

        int nextYX = nowYX + 2;
        if(N % 2 == 0 && (nextYX / N - nowY == 1)){
            if(startsFromZero){
                nextYX = nowY % 2 == 0 ? nowYX + 3 : nowYX + 1;
            }else{
                nextYX = nowY % 2 == 0 ? nowYX + 1 : nowYX + 3;
            }
        }

        if (!isAvailable(nowY, nowX)) return backtrack(nextYX, cnt, startsFromZero);


        int ret;
        if (graph[nowY][nowX] == 1){
            check[nowY][nowX] = true;
            ret = Math.max(cnt + 1, backtrack(nowYX + 2, cnt + 1, startsFromZero));
            check[nowY][nowX] = false;
            ret = Math.max(ret, backtrack(nowYX + 2, cnt, startsFromZero));
        } else{
            ret = Math.max(cnt, backtrack(nowYX + 2, cnt, startsFromZero));
        }

        return ret;
    }

    boolean isAvailable(int y, int x){
        if(check[y][x]) return false;

        int yy = y;
        int x1 = x; int x2 = x;

        while(--yy >= 0){
            if(--x1 >= 0){
                if(check[yy][x1]) return false;
            }
            if(++x2 < N){
                if(check[yy][x2]) return false;
            }
        }
        return true;
    }

    int getAns() {
        return backtrack(0, 0, true) + backtrack(1, 0, false);
    }
}

class MainA1799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];

        for (int i = 0; i < N; i++)
            graph[i] = strToIntArr(br.readLine());

        System.out.println(new 비숍_1799(N, graph).getAns());
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
/*
2
1 1
1 1
*/