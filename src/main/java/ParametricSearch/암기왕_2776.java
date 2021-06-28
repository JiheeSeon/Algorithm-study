package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

class 암기왕_2776 {
    int N; int[] arr;
    int K; int[] toFind;

    public 암기왕_2776(int n, int[] arr, int k, int[] toFind) {
        N = n;
        this.arr = arr;
        K = k;
        this.toFind = toFind;
    }

    String binarySearch(int target) {
        int low = 0; int high = arr.length - 1;
        int mid;

        while(low <= high){
            mid = (low + high) / 2;

            if(arr[mid] == target) return "1";
            else if(arr[mid] > target) high = mid - 1;
            else low = mid + 1;
        }

        return "0";
    }

    String getAns() {
        Arrays.sort(arr);
        StringBuilder stb = new StringBuilder();
        for (int i : toFind) {
            stb.append(binarySearch(i)).append("\n");
        }
        return stb.toString();
    }
}
class MainA2776{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N; int[] arr; int K; int[] toFind;

        StringBuilder stb = new StringBuilder();
        while(--T >= 0){
            N = Integer.parseInt(br.readLine());
            arr = strToIntArr(br.readLine());
            K = Integer.parseInt(br.readLine());
            toFind = strToIntArr(br.readLine());
            stb.append(new 암기왕_2776(N, arr, K, toFind).getAns());
        }
        System.out.print(stb);
    }

    static int[] strToIntArr(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}