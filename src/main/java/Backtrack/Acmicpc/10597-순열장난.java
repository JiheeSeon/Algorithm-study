package Backtrack.Acmicpc;

import java.io.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

//1111112211111111111111

class Main10597{
    static StringBuilder stb =  new StringBuilder();
    static String input;
    static int minN, maxN;
    static boolean flagToBreak = false;

    static int maxValue=0;

    static LinkedList<Integer> previous = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        setRangeOfN();

        backtrack(0);

        System.out.println(stb.toString());
    }
    static void backtrack(int currDigit){
        int maxDist = 1; int currCandidate;
        if (currDigit != input.length() -1) maxDist = 2;

        for (int i = 1; i <= maxDist; i++){
            if (flagToBreak) break;
            currCandidate = Integer.parseInt(input.substring(currDigit, currDigit + i));
            if (currCandidate == 0 || currCandidate > maxN) break;

            int existedMax = maxValue;
            maxValue = Math.max(existedMax, currCandidate);
            previous.add(currCandidate);

            if (currDigit + i >= input.length()){
                if (previous.size() == maxValue){
                    stb.append(previous.stream().map(s -> Integer.toString(s)).collect(Collectors.joining(" ")));
                    System.out.println("maxValue = " + maxValue);
                    System.out.println("previous.size() = " + previous.size());
                    flagToBreak = true;
                }
            } else backtrack(currDigit + i);

            previous.removeLast();
            if (existedMax != maxValue) maxValue = existedMax;
        }
    }
    static void setRangeOfN(){
        char previous = ' '; char current = ' ';
        int currentIntValue = -1;
        StringBuilder localStb = new StringBuilder();

        for(int i = 0; i < input.length(); i++){
            current = input.charAt(i);
            if (current == '0'){
                minN = Math.max(minN, Integer.parseInt(localStb.append(previous).append(current).toString()));
                localStb.setLength(0);
            } else {
                currentIntValue = Integer.parseInt(String.valueOf(current));
                minN = Math.max(minN, currentIntValue);
            }
            previous = current;
        }
        maxN = Math.max(minN, input.length());

        System.out.println("minN = " + minN);
        System.out.println("maxN = " + maxN);
    }
}