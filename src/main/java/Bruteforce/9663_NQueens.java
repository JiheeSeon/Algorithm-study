package Bruteforce;

import java.io.*;
import java.util.Arrays;

class Main9663 {
    static int N;
    static int[] tr;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tr = new int[N];
        Arrays.fill(tr, -1);

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
            if (!isAvailable(col, row)) continue;

            tr[row] = col;
            recursiveSolve(row + 1);
        }
    }

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