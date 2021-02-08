import java.io.*;

class Main2156{
    static long[][] memo;
    static long[] amount;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        memo = new long[3][N + 1];
        amount = new long[N + 1];

        for(int i = 1 ; i <= N ; i++) {
            amount[i] = Long.parseLong(br.readLine());
            solveWithDp(i);
        }

        bw.write(Long.toString(Math.max(Math.max(memo[0][N], memo[1][N]), memo[2][N])));
        bw.flush();
        bw.close();
    }
    static void solveWithDp(int n){
        memo[0][n] = Math.max(Math.max(memo[0][n-1], memo[1][n-1]), memo[2][n-1]);
        memo[1][n] = memo[0][n-1] + amount[n];
        memo[2][n] = memo[1][n-1] + amount[n];
    }
}