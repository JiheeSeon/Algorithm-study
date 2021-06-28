package ParametricSearch;

import java.io.*;
import java.util.regex.Pattern;
import java.util.Arrays;

/*
K개의 공유기 개수 제한에 얽매이지 말고
간격대로 설치했을 때 공유기가 몇 대 나올지를 생각하는 게 어려웠음
*/
class 공유기설치_2110{
    int N, K;
    long[] arr;

    public 공유기설치_2110(int N, int K, long[] arr){
        this.N = N;
        this.K = K;
        this.arr = arr;
    }

    long getAns(){
        Arrays.sort(arr);
        return parametricSearch();
    }

    long parametricSearch(){
        long low = 1;
        long high = arr[N - 1] - arr[0];
        long mid;
        long dst;
        int cnt; long start;

        while(low <= high){
            mid = (low + high) / 2;
            cnt = 1;
            start = arr[0];

            for(int i = 1; i < N; i++){
                dst = arr[i] - start;

                // 간격 dst를 기준으로 공유기 설치
                if(mid <= dst){
                    cnt++;
                    start = arr[i];
                }
            }

            if(cnt >= K) low = mid + 1;
            else high = mid - 1;
        }

        return high;
    }
}

class MainA2110{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int N = tmp[0]; int K = tmp[1];
        long[] arr = new long[N];
        for(int i = 0; i < N; i++)
            arr[i] = Long.parseLong(br.readLine());

        System.out.println(new 공유기설치_2110(N, K, arr).getAns());
    }
}