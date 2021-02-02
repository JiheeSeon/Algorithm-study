import java.io.*;
import java.util.regex.Pattern;

class Main11052 {
    static long[] price;
    static long[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        memo = new long[N + 1];
        price = Pattern.compile(" ").splitAsStream("0 " + br.readLine()).limit(N+1).mapToLong(Long::parseLong).toArray();

        for(int step = 1 ; step <= N ; step++)
            solveWithDpBottomUp(step);

        bw.write(Long.toString(memo[N]));
        bw.flush();
        bw.close();
    }

    static void solveWithDpBottomUp(int n){
        long max;

        if (n == 1)
            memo[n] = price[n];
        else{
            max = memo[n - 1] + price[1];

            for(int m = 0 ; m <= n ; m++){
                if(max < price[m] + memo[n - m])
                    max = price[m] + memo[n - m];
            }

            memo[n] = max;
        }
    }

    static long solveWithDpTopDown(int n) {
        long max;

        if (n == 1)
            return price[n];

        memo[1] = price[1];

        if (memo[n] == 0) {
            for (int i = 2; i <= n; i++) {
                max = solveWithDpTopDown(i - 1) + price[1];

                for (int j = 0; j <= i / 2; j++) {
                    if (max < price[j] + price[i - j])
                        max = price[j] + price[i - j];
                }

                memo[i] = max;
            }
        }
        return memo[n];
    }
}