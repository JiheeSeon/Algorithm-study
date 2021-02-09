import java.io.*;
import java.util.regex.Pattern;
//username
class Main1912_1 {
    static long[] input;
    static long[][] memo;
    static final int INCLUDE_X = 0;
    static final int INCLUDE_O = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        memo = new long[2][N + 1];
        input = Pattern.compile(" ").splitAsStream("0 " + br.readLine()).mapToLong(Long::parseLong).toArray();

        for (int i = 1; i <= N; i++)
            getSuccessiveSum(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(Math.max(memo[INCLUDE_X][N], memo[INCLUDE_O][N])));
        bw.flush();
        bw.close();
    }

    static void getSuccessiveSum(int n) {
        if (n == 1) {
            memo[INCLUDE_X][n] = input[n];
            memo[INCLUDE_O][n] = input[n];
        } else {
            memo[INCLUDE_X][n] = Math.max(memo[INCLUDE_X][n - 1], memo[INCLUDE_O][n - 1]);
            memo[INCLUDE_O][n] = Math.max(input[n], memo[INCLUDE_O][n - 1] + input[n]);
        }
    }
}