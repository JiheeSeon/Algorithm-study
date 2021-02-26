package Bruteforce;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

class Main9663 {
    static int N;
    static Stack<Integer> track = new Stack<>();
    static int[] tr;
    static int res = 0;
//    static boolean[][] columnValidity;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tr = new int[N];
        Arrays.fill(tr, -1);
//        columnValidity = new boolean[N][N];
//
//        for (int r = 0; r < N; r++){
//            for (int c = 0; c < N; c++)
//                columnValidity[r][c] = true;
//        }

        recursiveSolve(0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }

    static void recursiveSolve(int row) {
        if (row == N) {
            res++;
            return;
        }

        for (int col = 0; col < N; col++) {
//            if (!columnValidity[row][col]) continue;
            if (!isAvailable(col, row)) continue;

//            for (int rDist = 1; row + rDist < N; rDist++){
//                columnValidity[row + rDist][col] = false;
//                if (col - rDist >= 0) columnValidity[row + rDist][col - rDist] = false;
//                if (col + rDist < N) columnValidity[row + rDist][col + rDist] = false;
//            }

//            if (row == 2){
//                for (int r = 0; r < N; r++){
//                    for (int c = 0; c < N; c++) {
//                        System.out.print(columnValidity[r][c] ? "O " : "X ");
//                    }
//                    System.out.println();
//                }
//                System.out.println(" =========================== ");
//            }

//            track.push(col);
            tr[row] = col;
            recursiveSolve(row + 1);
//            track.pop();

//            for (int r = row + 1; r < N; r++){
//                for (int c = 0; c < N; c++) {
//                    columnValidity[r][c] = true;
//                }
//            }
        }
    }

/*
! O O O O O O O
X X ! O O O O O
X X X X ! O O O
X O X X X X O O
X O X O X X X O
X X X O X X X X
X O X O X O X X
X O X O X O O X
* */

    static boolean isAvailable(int col, int row) {
        int rowIdx = 0;

        for (int t : tr) {
            if (rowIdx >= row || t == -1) break;
            if (t == col || Math.abs(t - col) == (row - rowIdx)) {
                return false;
            }
            rowIdx++;
        }
        return true;
    }
}