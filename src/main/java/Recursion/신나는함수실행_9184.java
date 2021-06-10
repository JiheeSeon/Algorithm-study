package Recursion;

import java.io.*;
import java.util.regex.Pattern;

class 신나는함수실행_9184 {
    int a, b, c;
    int[][][] dp;

    String getAns(int[] abc){
        a = abc[0];
        b = abc[1];
        c = abc[2];
        if(a >= 0 && b >= 0 && c >= 0) dp = new int[a + 1][b + 1][c + 1];
        if(a >= 0 && b >= 0 && c >= 0) dp[0][0][0] = 1;

        return new StringBuilder("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(recursion(a, b, c)).append("\n").toString();
    }

    int recursion(int a, int b, int c){
        if(a <= 0 || b <= 0 || c <= 0) return 1;

        if(dp[a][b][c] != 0) return dp[a][b][c];

        if(a > 20 || b > 20 || c > 20){
            dp[a][b][c] = 1048576;
        } else if(a < b && b < c){
            dp[a][b][c] = recursion(a, b, c-1) + recursion(a, b-1, c-1) - recursion(a, b-1, c);
        } else {
            dp[a][b][c] = recursion(a-1, b, c) + recursion(a-1, b-1, c) + recursion(a-1, b, c-1) - recursion(a-1, b-1, c-1);
        }
        return dp[a][b][c];
    }
}

class MainA9184 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        String input;

        신나는함수실행_9184 sol = new 신나는함수실행_9184();
        while(!(input = br.readLine()).equals("-1 -1 -1")){
            stb.append(sol.getAns(strToIntArr(input)));
        }
        System.out.print(stb);
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}