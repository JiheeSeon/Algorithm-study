package Backtrack.Acmicpc;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main15655_2 {
    static int numOfInput, maxDigit;
    static int[] numbers;
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        numOfInput = input[0]; maxDigit = input[1];
        numbers = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);

        backtrack(1, new int[maxDigit]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void backtrack(int currentDigit, int[] previousIndices) {
        for (int indexOfChoice = currentDigit == 1 ? 0 : previousIndices[currentDigit - 2] + 1;
             indexOfChoice < numOfInput; indexOfChoice++) {
            previousIndices[currentDigit - 1] = indexOfChoice;

            if (currentDigit == maxDigit) appendAll(previousIndices);
            else backtrack(currentDigit + 1, previousIndices);

            previousIndices[currentDigit - 1] = 0;
        }
    }

    static void appendAll(int[] indexArray) {
        for (int i : indexArray) {
            stb.append(numbers[i]).append(" ");
        }
        stb.append("\n");
    }
}