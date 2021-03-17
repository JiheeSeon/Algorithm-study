package Backtrack.Acmicpc;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main15657_2 {
    static StringBuilder stb = new StringBuilder();
    static int N, maxDigit;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0]; maxDigit = input[1];
        numbers = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);

        backtrack(1, new int[maxDigit]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void backtrack(int currentDigit, int[] previousIndices) {
        for (int indexOfChoice = (currentDigit == 1 ? 0 : previousIndices[currentDigit - 2])
             ; indexOfChoice < N; indexOfChoice++) {
            previousIndices[currentDigit - 1] = indexOfChoice; // 일단 담아

            if(currentDigit == maxDigit) appendAll(previousIndices);
            else backtrack(currentDigit + 1, previousIndices);

            previousIndices[currentDigit - 1] = 0; // 다음 후보로 이동
        }
    }

    static void appendAll(int [] previousArray) {
        for(int i : previousArray){stb.append(numbers[i]).append(" ");}
        stb.append("\n");
    }
}