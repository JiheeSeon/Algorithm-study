package Backtrack.Acmicpc;

import java.io.*;

class Main9663{
    static int N;
    static int[] previous;
    static int result = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        previous = new int[N + 1];

        backtrack(1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();

    }
    static void backtrack(int y){
        for(int x = 1; x <= N; x++){
            // continue 조건
            if(!isAvailable(y, x)) continue;

            previous[y] = x;

            if (y == N) result++;
            else backtrack(y + 1);

            // clear
            previous[y] = 0;
        }
    }
    static boolean isAvailable(int y, int x){
        for(int prevX : previous){if (x == prevX) return false;}
        for (int prevY = 1; prevY < y; prevY++){
            if (Math.abs(previous[prevY] - x) == y - prevY) return false;
        }
        return true;
    }
}