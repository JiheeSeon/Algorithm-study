package Backtrack.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;

class Main15652_4{
    static int maxNumber, sequenceLength;
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        maxNumber = input[0]; sequenceLength = input[1];

        backtrack(1, new int[sequenceLength]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void backtrack(int currentDigit, int[] previous){
        for (int choseN = ((currentDigit == 1) ? 1 :previous[currentDigit - 2]); choseN <= maxNumber; choseN++){
            previous[currentDigit - 1] = choseN;

            if (currentDigit == sequenceLength){
                appendToStb(previous);
                previous[currentDigit - 1] = 0;
                continue;
            }

            backtrack(currentDigit + 1, previous);
            previous[currentDigit - 1] = 0;
        }
    }

    static void appendToStb(int[] array){
        for (int i : array){stb.append(i).append(" ");}
        stb.append("\n");
    }
}