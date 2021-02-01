import java.io.*;

class Main10844{
    static long [] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        memo = new long[N + 1];

        for (int i = 0 ; i <= N ; i++)
            solveWithDpBottomUp(i);

        bw.write(Long.toString(memo[N]));
        bw.flush();
        bw.close();
    }

    static void solveWithDpBottomUp(int n){
        int zeroOrNine = 0;

        if (n == 1) {
            memo[n] = 9;
        }else{
            memo[n] = 2 * memo[n-1];
        }
    }
}