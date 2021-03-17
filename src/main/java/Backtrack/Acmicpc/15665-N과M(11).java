package Backtrack.Acmicpc;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class Main15665{
    static int N, maxDigit;
    static int[] numbers;
    static LinkedHashSet<String> set = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0]; maxDigit = input[1];
        numbers = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);

        int[] prev = new int[maxDigit];
        Arrays.fill(prev, -1);

        backtrack(1, prev);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();
        for(String s : set){stb.append(s);}
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
    static void backtrack(int currentDigit, int[] prevIndices){
        for (int i = 0; i < N; i++){
            prevIndices[currentDigit - 1] = i;

            if(currentDigit == maxDigit) putOnSet(prevIndices);
            else backtrack(currentDigit + 1, prevIndices);

            prevIndices[currentDigit - 1] = -1;
        }
    }
    static void putOnSet(int[] prevIndices){
        StringBuilder localStb = new StringBuilder();
        for(int i : prevIndices){localStb.append(numbers[i]).append(" ");}
        localStb.append("\n");

        set.add(localStb.toString());
    }
}