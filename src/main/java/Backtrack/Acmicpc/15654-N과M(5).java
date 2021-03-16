package Backtrack.Acmicpc;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main15654_5 {
    static int numOfInput, maxDigit;
    static int[] numbers;
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        numOfInput = input[0]; maxDigit = input[1];
        numbers = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).sorted().toArray();
        Arrays.sort(numbers);

        backtrack(1, new int[maxDigit]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
    static void backtrack(int currentDigit, int[] previous){
        for (int i = 0; i < numOfInput; i++){
            if (isInArray(numbers[i], previous)) continue;

            previous[currentDigit - 1] = numbers[i];

            if (currentDigit == maxDigit){
                appendAllElementsToStb(previous);
                previous[currentDigit - 1] = 0;
                continue;
            }

            backtrack(currentDigit + 1, previous);
            previous[currentDigit - 1] = 0;
        }
    }
    static void appendAllElementsToStb(int [] array){
        for(int i : array){stb.append(i).append(" ");}
        stb.append("\n");
    }
    static boolean isInArray(int num, int[] array){
        for(int i : array){
            if(num == i) return true;
        }
        return false;
    }
}