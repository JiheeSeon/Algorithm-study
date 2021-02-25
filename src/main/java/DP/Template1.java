package DP;

import java.io.*;
import java.util.regex.Pattern;

class Guide1{
    static long[] input;
    static long[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        input = Pattern.compile(" ").splitAsStream("0 "+br.readLine()).mapToLong(Long::parseLong).limit(N + 1).toArray();

        for(int i = 1 ; i <= N ; i++)
            solveWithDp(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(" "); //memo[N]
        bw.flush();
        bw.close();
    }

    static void solveWithDp(int n){

    }
}