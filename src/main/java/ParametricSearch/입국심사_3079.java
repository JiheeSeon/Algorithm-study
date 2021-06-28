package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

class 입국심사_3079 {
    int N; long M;
    long[] arr;

    public 입국심사_3079(int n, long m, long[] arr) {
        N = n;
        M = m;
        this.arr = arr;
    }

    long getAns() {
        Arrays.sort(arr);

        if(N == 1) return arr[0] * M; // 심사대가 하나인 경우
        else return parametricSearch();
    }

    long parametricSearch() {
        long sum;
        long low = arr[0];
        long high = arr[N - 1] * M;
        long mid;
        long peopleN;

        while(low <= high){
            mid = (low + high) / 2;
            peopleN = M;

            for(long t : arr){
                peopleN -= mid / t;
                if(peopleN <= 0) break;
            }

            if(peopleN > 0){ // 해당 mid는 너무 작다
                low = mid + 1;
            } else{ // 너무 크다, 혹은 딱 맞으나 좀 더 최솟값을 보겠다.
                high = mid - 1;
            }
        }
        return low;
    }
}

class MainA3079{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();
        int N = (int)tmp[0]; long M = tmp[1];
        long[] arr = new long[N];
        for (int i = 0; i < N; i++)
            arr[i] = Long.parseLong(br.readLine());

        System.out.println(new 입국심사_3079(N, M, arr).getAns());
    }
}