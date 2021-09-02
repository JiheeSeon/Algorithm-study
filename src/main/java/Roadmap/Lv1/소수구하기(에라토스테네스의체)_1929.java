package Roadmap.Lv1;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
start부터 end까지의 수들 중 소수인 것을 판별
->  에라토스테네스의 체 : 대량의 소수를 한꺼번에 판별하고자 할 때 사용
*/
class 소수구하기_2960 {
    static String solve(int start, int end) {
        boolean[] isCompositeNumber = new boolean[end + 1];
        ArrayList<Integer> primeNumbers = new ArrayList<>();

        for (int i = 2; i <= end; i++) {
            if(isCompositeNumber[i]) continue;
            else if(i >= start) primeNumbers.add(i);

            // 소수의 배수
            for (int j = i + i; j <= end; j += i) isCompositeNumber[j] = true;
        }

        StringBuilder stb = new StringBuilder();
        primeNumbers.forEach(s -> stb.append(s).append("\n"));
        return stb.toString();
    }

    // check if it is prime number (for only one value)
    static boolean isPrimeNumber(int N) {
        for (int i = 2; i * i <= N; i++) {
            if(N % i == 0) return false;
        }
        return true;
    }
}

class MainA1929{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] range = InputProcessor.strToIntArr(br.readLine());
        System.out.print(소수구하기_2960.solve(range[0], range[1]));
    }
}