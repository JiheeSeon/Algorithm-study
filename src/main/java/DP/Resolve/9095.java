package DP.Resolve;

import java.io.*;

class Resolve9095 {
    static long[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            int NN = Integer.parseInt(br.readLine());

            memo = new long[NN + 1];
            memo[1] = 0; memo[2] = 1; memo[3] = 3;

            for(int k = 4; k <= NN ; k++)
                plus123Bu(k);

            stb.append(memo[NN]).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void plus123Bu(int n) {
        for(int i = 1 ; i <= 3; i++)
            memo[n] += (memo[n - i] + 1);
    }
}