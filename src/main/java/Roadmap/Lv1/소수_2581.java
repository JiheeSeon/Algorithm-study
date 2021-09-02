package Roadmap.Lv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 소수_2581 {
    static String solve(int M, int N) {
        boolean[] isCompositeNumber = new boolean[N + 1];
        long sum = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 2; i <= N; i++) { // 소수의 후보
            if(isCompositeNumber[i]) continue;

            if(M <= i){
                sum += i;
                if(min > i) min = i;
            }

            for (int k = 0; k <= N; k += i) { // 소수의 배수 = 합성수 지우기
                isCompositeNumber[k] = true;
            }
        }

        return sum == 0 ? "-1" : sum + "\n" + min;
    }
}
class MainA2581{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        System.out.println(소수_2581.solve(M, N));
    }
}
