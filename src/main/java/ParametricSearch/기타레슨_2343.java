package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class 기타레슨_2343 {
    int N, M;
    long[] arr;

    public 기타레슨_2343(int n, int m, long[] arr) {
        N = n;
        M = m;
        this.arr = arr;
    }

    long parametricSearch(){
        long low = 1;
        long high = 0;
        long max = 0;
        for(long element: arr){
            max = Math.max(max, element);
            high += element;
        }
        long mid, sum;
        int cnt;

        while(low <= high){
            mid = (low + high) / 2;

            cnt = 1; sum = 0;
            for(long element : arr){
                if(sum + element > mid){
                    sum = element;
                    cnt++;
                } else sum += element;
            }

            // 1. 너무 mid 를 크게 두어 적게 끊긴 경우
            // 2. cnt == mid -> 혹시나 더 작은 값이 있을까 탐색해야 함.
            if(cnt <= M) high = mid - 1;
            else low = mid + 1; // 너무 mid 가 작아서 많이 끊김
        }

        return Math.max(max, low); // 원칙적으로 low return하나 max와 비교해서 유효한 값인지도 봐야 함.
    }
}
class MainA2343{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int N = tmp[0]; int M = tmp[1];
        long[] arr = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();

        System.out.println(new 기타레슨_2343(N, M, arr).parametricSearch());
    }
}

/*
58% 반례 테스트케이스
원소 안에 있는 최대값보다야 커야 함.

7 6
100 400 300 100 500 101 400
500

7 7
5 9 6 8 7 7 5
9

7 7
1 5 9 9 9 2 9
9

8 7
3 3 10 10 3 2 6 2
 */
