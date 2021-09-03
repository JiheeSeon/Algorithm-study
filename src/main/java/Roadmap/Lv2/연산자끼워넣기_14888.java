package Roadmap.Lv2;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

class 연산자끼워넣기_14888 {
    int N;
    int[] numbers, cntOfOperators;
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    public 연산자끼워넣기_14888(int n, int[] numbers, int[] cntOfOperators) {
        N = n;
        this.numbers = numbers;
        this.cntOfOperators = cntOfOperators;
    }

    String solve() {
        backtrack(0, new LinkedList<>());
        return max + "\n" + min;
    }

    void backtrack(int level, LinkedList<Integer> chosen){
        if(level == N - 1){
            int result = numbers[0];
            for (int i = 1; i < N; i++) {
                switch(chosen.get(i - 1)){
                    case 0 -> result += numbers[i];
                    case 1 -> result -= numbers[i];
                    case 2 -> result *= numbers[i];
                    case 3 -> result /= numbers[i];
                }
            }

            if(result > max) max = result;
            if(result < min) min = result;

            return;
        }
        for (int i = 0; i < 4; i++) {
            if(cntOfOperators[i] <= 0) continue;

            cntOfOperators[i]--;
            chosen.add(i);
            backtrack(level + 1, chosen);
            chosen.removeLast();
            cntOfOperators[i]++;
        }
    }
}

class MainA14888{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = InputProcessor.strToIntArr(br.readLine());
        int[] cntOfOperators = InputProcessor.strToIntArr(br.readLine());
        System.out.println(new 연산자끼워넣기_14888(N, numbers, cntOfOperators).solve());
    }
}