package DP;

import java.io.*;

class Main10844 {
    static long[][] memo; //memo[n][k] 는 k로 시작하는 n자리 수의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        long sum = 0;

        memo = new long[N + 1][10];

        for (int i = 1; i <= N ; i++)
            solveWithDpBottomUp(i);

        for (int j = 1; j <= 9; j++)
            sum += memo[N][j];

        bw.write(Long.toString(sum % 1000000000));
        bw.flush();
        bw.close();
    }

    static void solveWithDpBottomUp(int n) {
        if (n == 1) // 1의 자리수
            memo[1] = new long[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        else if (n == 2) { // 2의 자리수
            memo[2] = new long[]{1, 2, 2, 2, 2, 2, 2, 2, 2, 1};
        } else { // n의 자리수
            memo[n - 1][0] = memo[n - 2][1] %  1000000000;

            for (int k = 1; k <= 8; k++)
                memo[n][k] = (memo[n - 1][k - 1] + memo[n - 1][k + 1]) % 1000000000;

            memo[n][9] = memo[n - 1][8];
        }
    }
}