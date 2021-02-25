package DP;

import java.io.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class Main11057 {
    static long[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        long sum = 0;

        memo = new long[N + 1][10];

        for (int i = 1; i <= N; i++)
            solveWithDpBottomUp(i);

        for (int j = 0; j <= 9; j++)
            sum += memo[N][j] % 10007;

        bw.write(Long.toString(sum % 10007));
        bw.flush();
        bw.close();
    }

    static void solveWithDpBottomUp(int n) {
        if (n == 1)
            memo[1] = LongStream.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1).toArray();
        else if (n == 2)
            memo[2] = LongStream.rangeClosed(1, 10).toArray();
        else {
            for (int k = 0; k <= 9; k++) {
                for (int j = 0; j <= k; j++)
                    memo[n][k] += (memo[n - 1][j]) % 10007;
            }
        }
    }
}