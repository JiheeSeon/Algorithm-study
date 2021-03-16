package Backtrack.Acmicpc;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main15655{
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
    static void backtrack(int currentDigit, int[] previous){
        for (int indexOfChoice = 0; indexOfChoice < numOfInput; indexOfChoice++){
            if (currentDigit != 1 && numbers[indexOfChoice] <= previous[currentDigit - 2]) continue;
            previous[currentDigit - 1] = numbers[indexOfChoice];

            if (currentDigit == maxDigit)  appendAll(previous);
            else backtrack(currentDigit + 1, previous);

            previous[currentDigit - 1] = 0;
        }
    }
    static void appendAll(int [] array){
        for(int i : array){stb.append(i).append(" ");}
        stb.append("\n");
    }
}