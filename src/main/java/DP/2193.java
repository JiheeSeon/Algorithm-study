import java.io.*;

class Main2193 {
    static long[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        memo = new long[N+1];

        for (int i = 1; i <= N ; i++)
            solveWithDpBottomUp(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(memo[N]));
        bw.flush();
        bw.close();
    }

    static void solveWithDpBottomUp(int n) {
        if (n == 1 | n == 2)
            memo[n] = 1;
        else
            memo[n] = memo[n - 1] + memo[n - 2];
    }
}