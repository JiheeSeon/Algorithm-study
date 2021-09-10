package CramForCodingTest.PS;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 랜선자르기_1654 {
    int N, K, max;
    int[] lans;

    public 랜선자르기_1654(int n, int k, int max, int[] lans) {
        N = n;
        K = k;
        this.max = max;
        this.lans = lans;
    }

    int solve() {
        int left = 1; int right = max;
        int testingMid;
        long lanCnt;

        while (left <= right) { // 무한 루프 방지 위해 같은 경우도 포함, left > right일 때 break하는 것과 같음
            lanCnt = 0;
            testingMid = (left + right) / 2; // 1 차이 날 경우 무조건 왼쪽 꺼가 나올 것
            for (int lan : lans) lanCnt += (lan / testingMid);

            if(lanCnt < N) right = testingMid - 1; // 어차피 right는 맞는 것만 가르키게 되어있음.
            else left = testingMid + 1; // left를 mid와 같이 두면 계속 뺑뺑 돎
        }

        return right;
    }
}

class MainA1654{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int K = tmp[0]; int N = tmp[1];
        int[] lans = new int[K];
        int max = 0;
        for (int k = 0; k < K; k++) {
            lans[k] = Integer.parseInt(br.readLine());
            if(lans[k] > max) max = lans[k];
        }

        System.out.println(new 랜선자르기_1654(N, K, max, lans).solve());
    }
}
/*
4 4
2
1
2
1

Answer
1
*/