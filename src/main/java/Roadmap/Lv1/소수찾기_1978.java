package Roadmap.Lv1;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 소수찾기_1978 {
    static int solve(int[] input) {
        int ret = 0;
        for (int i : input) {
            ret += isPrime(i);
        }
        return ret;
    }

    private static int isPrime(int N) {
        if(N == 1) return 0;

        for (int i = 2; i * i <= N; i++) {
            if(N % i == 0) return 0;
        }
        return 1;
    }
}

class MainA1978{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] input = InputProcessor.strToIntArr(br.readLine());
        System.out.println(소수찾기_1978.solve(input));
    }
}