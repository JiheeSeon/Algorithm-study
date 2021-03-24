package Backtrack.Acmicpc;

import java.io.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

class Main10597{
    static StringBuilder stb =  new StringBuilder();
    static String input;
    static int minN, maxN;
    static boolean flagToBreak = false;

    static LinkedList<Integer> previous = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        setRangeOfN();

        for(int currN = minN; currN <= maxN; currN++){
            backtrack(0, currN);
            if (!stb.isEmpty()) break;
        }
        System.out.println(stb.toString());
    }
    static void backtrack(int currDigit, int currN){
        int maxDist = 1; int currCandidate;
        if (currN >= 10 && currDigit != input.length() -1) maxDist = 2;

        for (int i = 1; i <= maxDist; i++){
            if (flagToBreak) break;
            currCandidate = Integer.parseInt(input.substring(currDigit, currDigit + i));
            if (currCandidate == 0 || currCandidate > currN) break;

            previous.add(currCandidate);

            if (currDigit + i >= input.length()){
                if (previous.size() == currN){
                    stb.append(previous.stream().map(s -> Integer.toString(s)).collect(Collectors.joining(" ")));
                    flagToBreak = true;
                }
            } else backtrack(currDigit + i, currN);

            previous.removeLast();
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

//        System.out.println("minN = " + minN);
//        System.out.println("maxN = " + maxN);
    }
}