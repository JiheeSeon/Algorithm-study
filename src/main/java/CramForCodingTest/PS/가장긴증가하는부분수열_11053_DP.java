package CramForCodingTest.PS;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 가장긴증가하는부분수열_11053_DP {
    int N;
    int[] arr;

    public 가장긴증가하는부분수열_11053_DP(int n, int[] arr) {
        N = n;
        this.arr = arr;
    }

    int solve() {
        int[] dp = new int[N]; // i번째 원소를 마지막 원소로 가지는 최장수열의 길이
        int max = 0;

        for (int i = 0; i < N; i++) {
            if(dp[i] == 0) dp[i] = 1; // 초기화

            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j]) { // 내가 j를 마지막으로 하는 수열에 들어갈 수 있는 경우
                    if (dp[i] < dp[j] + 1) dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}

class MainA11053_DP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = InputProcessor.strToIntArr(br.readLine());

        System.out.println(new 가장긴증가하는부분수열_11053_DP(N, arr).solve());
    }
}