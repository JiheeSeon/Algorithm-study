package DP;

import java.io.*;

public class Main2133 {
    static long[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        memo = new long[N + 1];
        for (int i = 2; i <= N; i++)
            solveWithDP(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(memo[N]));
        bw.flush();
        bw.close();
    }

    static void solveWithDP(int n) {
        switch (n) {
            case 0 -> memo[n] = 1;
            case 1 -> memo[n] = 0;
            case 2 -> memo[n] = 3;
            case 3 -> memo[n] = memo[n] = 3 * memo[n - 2];
            default -> {
                memo[n] = 3 * memo[n - 2];
                for (int i = n - 4; i >= 0; i -= 2)
                    memo[n] += 2 * memo[i];

            }
        }
    }
}
