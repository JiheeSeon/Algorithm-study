package CramForCodingTest.PS;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class 가장긴증가하는부분수열2_12015 {
    int N;
    int[] arr;
    ArrayList<Integer> sequence;

    public 가장긴증가하는부분수열2_12015(int N, int[] arr){
        this.N = N;
        this.arr = arr;
        sequence = new ArrayList<>();
        sequence.add(arr[0]);
    }

    int solve() {
        // 증가하는 부분 수열 = 현재 값이 앞에 있는 값보다 큰 수열
        for (int i = 1 ; i < arr.length; i++) { //O(n) * O(log n) = O(nlog n)
            int lbIdx = lowerBound(0, sequence.size(), arr[i]);

            // 무조건 대체하는게 맞는가? -> 어차피 뒷쪽 sequence에 영향 안감.
            if(lbIdx < sequence.size()) sequence.set(lbIdx, arr[i]);
            else sequence.add(arr[i]);
        }
        return sequence.size();
    }

    // key 보다 같거나 큰 값의 위치를 반환해줌.
    int lowerBound(int left, int right, int key) {
        int mid;

        while(left < right){
            mid = (left + right) / 2;

            if(sequence.get(mid) < key) left = mid + 1;
            else right = mid;
        }

        return right;
    }
}

class MainA12015{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = InputProcessor.strToIntArr(br.readLine());
        System.out.println(new 가장긴증가하는부분수열2_12015(N, arr).solve());
    }
}