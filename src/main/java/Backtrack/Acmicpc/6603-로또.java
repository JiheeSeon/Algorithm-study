package Backtrack.Acmicpc;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class Main6603{
    /*문제 요약
    입력 :: 고를 수의 개수  [white space] 고를 수들

    */
    static int[] input;
    static int[] previous;
    static final int chooseN = 6;

    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            previous = new int[chooseN];
            if(input[0] == 0) break;
            backtrack(1);
            stb.append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
    static void backtrack(int currDigit){
        for(int i = currDigit == 1 ? 0 : previous[currDigit - 2] + 1; i < input[0]; i++){
            previous[currDigit - 1] = i;

            if(currDigit == chooseN) appendAll();
            else backtrack(currDigit + 1);

            previous[currDigit - 1] = 0;
        }
    }
    static void appendAll(){
        for(int i : previous) { stb.append(input[i + 1]).append(" "); }
        stb.append("\n");
    }
}