import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

class Main1699 {
    static long[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        memo = new long[N + 1];
        Arrays.fill(memo, 1000000);

        for (int i = 1; i <= N; i++)
            getSquareSum(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(memo[N]));
        bw.flush();
        bw.close();
    }

    static void getSquareSum(int n) {
        switch (n) {
            case 1, 2, 3 -> memo[n] = n;
            default -> {
                if (Math.sqrt(n) == Math.floor(Math.sqrt(n)))
                    memo[n] = 1;
                else {
                    for (int i = 1; i <= n / 2; i++)
                        memo[n] = Math.min(memo[n], memo[i] + memo[n - i]);
                }
            }
        }
    }
}