package DP.Resolve;

import java.io.*;

class Resolve11727{
    static int[] memo;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        memo = new int[N + 1];

        for(int i = 1; i <= N; i++)
            twoXNTiling2(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(memo[N] % 10007));
        bw.flush();
        bw.close();
    }
    static void twoXNTiling2(int n){
        switch(n){
            case 1 -> memo[n] = n;
            case 2 -> memo[n] = 3;
            default -> memo[n] = (memo[n - 1] + 2 * memo[n - 2]) % 10007;
        }
    }
}