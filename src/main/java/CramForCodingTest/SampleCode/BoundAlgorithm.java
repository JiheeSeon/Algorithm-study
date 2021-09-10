package CramForCodingTest.SampleCode;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class BoundAlgorithm {
    int[] arr;
    int key;

    public BoundAlgorithm(int[] arr, int key) {
        this.arr = arr;
        this.key = key;
    }

    // 찾고자 하는 값 이상이 처음 나타나는 위치
    int lowerBound() {
        int left = 0;
        int right = arr.length;
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            if(arr[mid] >= key) right = mid;
            else left = mid + 1;
        }

        return right;
    }

    // 찾고자 하는 값보다 처음으로 큰 값이 나온 위치
    int upperBound(){
        int left = 0;
        int right = arr.length;
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            if(arr[mid] <= key) left = mid + 1; // 같아도 왼쪽을 배제
            else right = mid;
        }

        return right;
    }
}

class BoundAlgorithmApp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = InputProcessor.strToIntArr(br.readLine());
        int key = Integer.parseInt(br.readLine());

        BoundAlgorithm solution = new BoundAlgorithm(arr, key);
        System.out.println(solution.lowerBound());
        System.out.println(solution.upperBound());
    }
}
//1 2 2 3 3 3 4 6 7
//3
//5
