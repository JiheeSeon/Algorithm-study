package CramForCodingTest.PS;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class 공유기설치_2110 {
    int C;
    int min, max;
    int[] locations;

    public 공유기설치_2110(int c, int[] locations) {
        C = c;
        this.locations = locations;
        Arrays.sort(locations);
    }

    int solve() {
        int left = 1; int right = locations[locations.length - 1] - locations[0];
        int mid, idx;
        int prev;
        int cnt;
        int dist;

        while (left <= right) {
            mid = (left + right) / 2;

            prev = locations[0];
            idx = 1;
            cnt = 1;

            while (idx < locations.length) {
                dist = locations[idx] - prev;

                if(mid <= dist){
                    cnt++;
                    prev = locations[idx];
                }
                idx++;
            }

            if(cnt >= C) left = mid + 1; // 놓은 공유기 수가 필요한 공유기의 개수보다 여유 있을 때
            else right = mid - 1; // right에는 확실한게 국룰, 무조건 더 놔야 할 경우 right 조정(강한 조정)
        }

        return right;
    }
}

class MainA2110{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int N = tmp[0]; int C = tmp[1];
        int[] locations = new int[N];

        for (int i = 0; i < N; i++) {
            locations[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(new 공유기설치_2110(C, locations).solve());
    }
}