package Roadmap.Lv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Objects;

class 피보나치수5_10870{
    static BigInteger[] dp = new BigInteger[1000];

    static BigInteger fibo(int N) {
        if(N == 0) return BigInteger.ZERO;
        else if(N == 1 || N == 2) return BigInteger.ONE;
        else if(!Objects.equals(dp[N], null)) return dp[N];

        dp[N] = fibo(N - 1).add(fibo(N - 2));
        return dp[N];
    }
}

class MainA10870{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(피보나치수5_10870.fibo(N));
    }
}