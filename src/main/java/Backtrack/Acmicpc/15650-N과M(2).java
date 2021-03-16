package Backtrack.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;

class Main12650{
    static int maxNumber, lengthOfSequence;
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        maxNumber = input[0]; lengthOfSequence = input[1];

        backtrack(1, new int[lengthOfSequence]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
    static void backtrack(int currentNumber, int[] previous){
        for (int choice = (currentNumber == 1) ? 1 : (previous[currentNumber - 2] + 1); choice <= maxNumber; choice++){
            previous[currentNumber - 1] = choice;

            if (currentNumber == lengthOfSequence){
                appendAllToStringBuilder(previous);
                previous[currentNumber - 1] = 0;
                continue;
            }

            backtrack(currentNumber + 1, previous);
            previous[currentNumber - 1] = 0;
        }
    }
    static void appendAllToStringBuilder(int [] array){
        for(int i : array){  stb.append(i).append(" "); }
        stb.append("\n");
    }
}