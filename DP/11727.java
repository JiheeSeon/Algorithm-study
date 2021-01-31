import java.io.*;

class Main11727 {
    static final int MAX_SIZE = 1000;
    static int[] memo = new int[MAX_SIZE + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        memo[1] = 1;
        memo[2] = 3;

        for (int i = 3; i <= N; i++)
            solveWithDPBottomUp(i);

        bw.write(Integer.toString(memo[N]));
        bw.flush();
        bw.close();
    }

    static void solveWithDPBottomUp(int n) {
        memo[n] = (memo[n - 1] + 2 * memo[n - 2]) % 10007;
    }
}