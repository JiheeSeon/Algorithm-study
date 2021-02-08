package Resolve;

import java.io.*;

class Resolve1463 {
    static int[] dp;
    static int MAX_SIZE = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[MAX_SIZE];

        for (int i = 2; i <= N; i++) {
            makeOneBu(i);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(dp[N]));
        bw.flush();
        bw.close();
    }

    static void makeOneBu(int n) {
        int min = 1 + dp[n - 1];

        if (n % 3 == 0)
            min = Math.min(1 + dp[n / 3], min);

        if (n % 2 == 0) // 실수 마킹 else if 로 습관적으로 달았음
            min = Math.min(1 + dp[n / 2], min);

        dp[n] = min;
    }
}