package Roadmap.Lv1;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
문제 요약
주어진 범위 안의 수들 중에서 가장 많은 약수의 개수를 찾는 문제
*/
class DivisorsAgain_13226 {
    /*
    풀이 1. 모든 수의(약수의 후보) 배수들을 1씩 올림
    풀이 2. 합성수의 약수 후보들 중 약수 구하기 (일반적)
    */
    static int solve1(int L, int U) {
        int[] cnt = new int[U + 1];

        // i는 divisor가 될 수 있는 모든 수
        for (int i = 1; i <= U; i++) {
            // k는 divisor의 배수
            for (int k = i; k <= U; k += i) {
                if (k < L) continue;

                cnt[k]++;
            }
        }

        return Arrays.stream(cnt).max().getAsInt();
    }

    static int solve2(int L, int U) {
        int[] cnt = new int[U + 1];

        for (int i = L; i <= U; i++) { // 나누고자 하는 대상
            for (int j = 1; j * j <= i; j++) { // 약수 후보
                if (i % j == 0) {
                    cnt[i]++;
                    if (j != i / j) cnt[i]++;
                }
            }
        }
        return Arrays.stream(cnt).max().getAsInt();
    }
}

class MainA13226 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] tmp;
        StringBuilder stb = new StringBuilder();
        while (T-- > 0) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            stb.append(DivisorsAgain_13226.solve2(tmp[0], tmp[1])).append("\n");
        }
        System.out.print(stb);
    }
}
