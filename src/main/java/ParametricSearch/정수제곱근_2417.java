package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 정수제곱근_2417 {
    long N;

    public 정수제곱근_2417(long n) {
        N = n;
    }

    long solution() {
        if(N == 0 || N == 1) return N;
        return recursion();
    }

    long recursion() {
        long low = 0;
        long high = (long) Math.pow(2, 31.5); // 음..어..웅,..
        long mid;

        while(low <= high){
            mid = (low + high) / 2;

            if(mid * mid >= N) high = mid - 1;
            else low = mid + 1;
        }
        return high + 1;
    }
}
class MainA2417{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        System.out.println(new 정수제곱근_2417(N).solution());
    }
}