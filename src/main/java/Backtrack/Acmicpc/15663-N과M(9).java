package Backtrack.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;
import java.util.*;

class Main15663{
    static int N, maxDigit;
    static int[] numbers;
    static LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0]; maxDigit = input[1];
        numbers = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);

        backtrack(1, new int[maxDigit]);

        bw.write("");
        bw.flush();
        bw.close();
    }

    static void backtrack(int currDigit, int[] previous){
        for (int i = 0; i < N; i++){
            if (isInArray(numbers[i], previous)) continue;
            previous[currDigit - 1] = numbers[i];

            if(currDigit == maxDigit) dealLast(previous);
            else backtrack(currDigit + 1, previous);

            previous[currDigit - 1] = 0;
        }
    }

    static void dealLast(int [] array){
        StringBuilder stb = new StringBuilder();
        for (int i : array){stb.append(i).append(" ");}
        stb.append("\n");
        linkedHashSet.add(stb.toString());
    }

    static boolean isInArray(int n, int[] array){
        for(int i : array){
            if(i == n) return true;
        }
        return false;
    }
}