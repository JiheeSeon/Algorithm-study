package Roadmap.Lv1;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 에라토스테네스의체_2960 {
    static int solve(int N, int K) {
        boolean[] isCompositeNumber = new boolean[N + 1];
        int cnt = 0;

        for (int i = 2; i <= N; i++) {
            if(isCompositeNumber[i]) continue;

            for (int j = i; j <= N; j += i) {
                if(isCompositeNumber[j]) continue; // 이미 다른 수에 의해 지워지지 않았는지 체크

                isCompositeNumber[j] = true;
                if(++cnt == K) return j;
            }
        }
        return 0;
    }
}

class MainA2960{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = InputProcessor.strToIntArr(br.readLine());
        System.out.println(에라토스테네스의체_2960.solve(input[0], input[1]));
    }
}