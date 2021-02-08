package Resolve;

import java.io.*;

class Resolve11726 {
    static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        memo = new int[N + 1];

        for (int i = 1; i <= N; i++)
            twoXNTilingBu(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(memo[N]%10007));
        bw.flush();
        bw.close();
    }

    static void twoXNTilingBu(int n) {
        if (n == 1 || n == 2) //1 <= n && n <= 2
            memo[n] = n;
        else
            memo[n] = (memo[n - 2] + memo[n - 1]) % 10007;
    }
}