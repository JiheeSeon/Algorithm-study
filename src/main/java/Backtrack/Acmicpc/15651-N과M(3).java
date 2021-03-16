package Backtrack.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;

class Main15651_3{
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

    static void backtrack(int current, int[] previous){
        for (int chosenN = 1; chosenN <= maxNumber; chosenN++){
            previous[current - 1] = chosenN;

            if (current == sequenceLength){
                appendToStringBuilder(previous);
                previous[current - 1] = 0;
                continue;
            }

            backtrack(current + 1, previous);
            previous[current - 1] = 0;
        }
    }

    static void appendToStringBuilder(int[] array){
        for(int i : array){stb.append(i).append(" ");}
        stb.append("\n");
    }
}