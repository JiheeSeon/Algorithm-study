package Backtrack.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;

class Main15649{
    static int lastNumber, lengthOfSequence;
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        lastNumber = input[0]; lengthOfSequence = input[1];
        backtrack(1, new int[lengthOfSequence]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void backtrack(int currDigit, int[] numberToPrint){
        for (int num = 1; num <= lastNumber; num++){
            if (isInArray(num, numberToPrint)) continue;

            numberToPrint[currDigit - 1] = num;

            if (currDigit == lengthOfSequence){
                appendAllElements(numberToPrint);
                numberToPrint[currDigit - 1] = 0;
                continue;
            }

            backtrack(currDigit + 1, numberToPrint);
            numberToPrint[currDigit - 1] = 0;
        }
    }

    static boolean isInArray(int element, int[] array){
        for (int j : array) { if (element == j) return true; }
        return false;
    }

    static void appendAllElements(int[] array) {
        for (int i : array) { stb.append(i).append(" ");}
        stb.append("\n");
    }
}