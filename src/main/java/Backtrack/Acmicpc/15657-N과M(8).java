package Backtrack.Acmicpc;

import java.io.IOException;
import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main15657{
    static int N, maxDigit;
    static int[] numbers;
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0]; maxDigit = input[1];
        numbers = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);

        backtrack(1, new int[maxDigit]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void backtrack(int digit, int[] previous){
        for (int i = 0; i < N; i++){
            if(digit != 1 && numbers[i] < previous[digit - 2]) continue;

            previous[digit - 1] = numbers[i];

            if (digit == maxDigit) appendAll(previous);
            else backtrack(digit + 1, previous);

            previous[digit - 1] = 0;
        }
    }
    static void appendAll(int [] array){
        for (int i: array) stb.append(i).append(" ");
        stb.append("\n");
    }
}