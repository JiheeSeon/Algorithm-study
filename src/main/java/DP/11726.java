package DP;

import java.io.*;

class Main11726 {
    static int[] memo = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());


        int i = 1;
        for(; i <= 2; i++)
            memo[i] = i;

        for (; i <= N ; i++)
            solveWithDPBottomUp(i);

        bw.write(Integer.toString(memo[N]));
        bw.flush();
        bw.close();
    }

    static void solveWithDPBottomUp(int n) {
            memo[n] = (memo[n - 1] + memo[n - 2]) % 10007;
    }
}