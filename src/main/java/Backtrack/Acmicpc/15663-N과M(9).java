package Backtrack.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;
import java.util.*;

class Main15663{
    static int N, maxDigit;
    static int[] numbers;
    static LinkedHashSet<String> set = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0]; maxDigit = input[1];
        numbers = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);

        int[] previous = new int[maxDigit];
        Arrays.fill(previous, -1);

        backtrack(1, previous);
        StringBuilder stb = new StringBuilder();
        for (String s: set){ stb.append(s); }
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void backtrack(int currentDigit, int[] previousIndices){
        for(int i = 0; i < N; i++){
            if (contains(i, previousIndices)) continue;

            previousIndices[currentDigit - 1] = i;

            if (currentDigit == maxDigit) putInSet(previousIndices);
            else backtrack(currentDigit + 1, previousIndices);

            previousIndices[currentDigit - 1] = -1;
        }
    }
    static void putInSet(int[] prev){
        StringBuilder localStb = new StringBuilder();
        for(int i : prev){
            localStb.append(numbers[i]).append(" ");
        }
        localStb.append("\n");
        set.add(localStb.toString());
    }

    static boolean contains(int i, int[] prev){
        for(int p : prev){ if(i == p) return true;}
        return false;
    }
}