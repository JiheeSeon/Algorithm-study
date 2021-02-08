import java.util.*;
import java.io.*;

class Main2579 {
    static long[] memo;
    static long[] amount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        amount = new long[T + 1];
        memo = new long[T + 1];

        for (int i = 1; i <= T; i++) {
            amount[i] = Long.parseLong(br.readLine());
            solveWithDP(i);
        }

        bw.write(Long.toString(memo[T]));
        bw.flush();
        bw.close();
    }

    static void solveWithDP(int n) {
        if (n == 1)
            memo[n] = amount[1];
        else if (n == 2)
            memo[n] = amount[1] + amount[2];
        else
            memo[n] = Math.max(amount[n] + memo[n - 2], amount[n] + amount[n - 1] + memo[n - 3]);
    }
}