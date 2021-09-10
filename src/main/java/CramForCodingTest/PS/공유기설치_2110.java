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
        int nowDist, accDist;
        int selectedN;
        int breakVal;

        while (left <= right) {
            mid = (left + right) / 2;

            idx = 1; accDist = 0;
            selectedN = 1;
            breakVal = 0;

            while (idx < locations.length) {
                nowDist = locations[idx] - locations[idx - 1];

                if (breakVal != 0 || nowDist > mid) {
                    if(breakVal < nowDist) breakVal = nowDist;
                    idx++;
                    continue;
                }

                if(accDist + nowDist <= mid){
                    accDist += nowDist;
                } else{
                    selectedN++;
                    accDist = nowDist;
                }

                idx++;
            }

            if (accDist == mid) selectedN++;
            else if (accDist > mid) selectedN += 2;

            if(breakVal != 0 || selectedN > C) left = mid + 1;
            else right = mid - 1;
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