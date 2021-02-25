import java.io.*;

class Main9095 {
    static final int MAX_SIZE = 11;
    static int[] memo = new int[MAX_SIZE];
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++)
            stb.append(Integer.toString(solveWithDpTopDown(Integer.parseInt(br.readLine())))).append("\n");

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static int solveWithDpTopDown(int n) {
        if (n == 0 | n == 1 | n == 2)
            return n;
        else if (n == 3)
            return 4;
        else {
            if (memo[n] == 0)
                memo[n] = solveWithDpTopDown(n - 1) + solveWithDpTopDown(n - 2) + solveWithDpTopDown(n - 3);
            return memo[n];
        }
    }
}