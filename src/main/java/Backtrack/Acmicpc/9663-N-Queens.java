package Backtrack.Acmicpc;

import java.io.*;
import java.util.*;

class Main9663{
    static int N;
    static boolean[][][] diagonallyConstricted;
    static int result = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        diagonallyConstricted = new boolean[N + 1][N + 1][N + 1]; // [y] [y Who've Recorded] [x(unavailable)]

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

            // 안되는 X 기록, 어차피 뭐가 선택되었는지는 중요하지 않음.
            writeUnavailablePoint(y, x);

            if (y == N) result++;
            else backtrack(y + 1);

            // clear
            for(int affectedY = y + 1; affectedY <= N; affectedY++){
                diagonallyConstricted[affectedY][y] = new boolean[N + 1];
            }
        }
    }
    static void writeUnavailablePoint(int y, int x){
        for(int delta = 1; delta <= N - y; delta++){
            diagonallyConstricted[y + delta][y][x] = true;

            if (x + delta <= N)
                diagonallyConstricted[y + delta][y][x + delta] = true;

            if (x - delta >= 1)
                diagonallyConstricted[y + delta][y][x - delta] = true;
        }
    }
    static boolean isAvailable(int y, int x){
        for(int i = 1; i <= N; i++){ // who've recorded // unavailable
            if (diagonallyConstricted[y][i][x]) return false;
        }
        return true;
    }
    static private class UnAvailablePoint{
        int x, markedFrom;
        public UnAvailablePoint(int x, int markedFrom){
            this.x = x;
            this.markedFrom = markedFrom;
        }
    }
}