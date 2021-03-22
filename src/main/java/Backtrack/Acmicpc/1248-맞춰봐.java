package Backtrack.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main1248{
    // -10 ~ 10
    static StringBuilder stb = new StringBuilder();
    static int N; // 수열의 크기 :: 1-10
    static int[] A; // - 10 ~ 10
    static char[] S;
    static char[] signOfDigit; // index -> + - 0 중 하나
    static int[] mark; // 0 1 2 3 -> 0 4 7 9

    static int[] previous;
    static boolean flagToBreak = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 종이에 쓴 숫자의 개수
        S = br.readLine().toCharArray();

        previous = new int[N];
        mark = new int[N];
        signOfDigit = new char[N];

        signOfDigit[0] = S[0]; mark[0] = 0;
        int acc = 0; int toAdd = N;

        for(int i = 1; i < N; i++){
            acc += toAdd--;
            mark[i] = acc;
            signOfDigit[i] = S[acc];
        }

        backtrack(0);

        System.out.println(stb.toString());
    }
    static void backtrack(int currentDigit){
        int[] range = getRangeForLoop(currentDigit);
        int start = range[0]; int end = range[1];

        for(int i = start; i <= end; i++){
            if (flagToBreak) break;
            if (!isValid(currentDigit, i)) continue;

            previous[currentDigit] = i;

            if (currentDigit == N - 1){
                flagToBreak = true;
                for (int prev : previous){stb.append(prev).append(" ");}
                break;
            } else backtrack(currentDigit + 1);

            previous[currentDigit] = 0;
        }

    }
    static int[] getRangeForLoop(int digit){
        int[] res = new int[2];
        char signOfCurrentDigit = signOfDigit[digit];

        switch (signOfCurrentDigit){
            case '+':
                res[0] = 1; res[1] = 10;
                break;
            case '-':
                res[0] = -10; res[1] = -1;
                break;
        }
        return res;
    }
    static boolean isValid(int currentDigit, int toChoose){
        int pad = 1;
        int indexToCheck;
        char sign;
        int valueToCheck;

        for (int j = currentDigit - 1; j >= 0; j--){
            indexToCheck = (mark[j] + pad++);
            sign = S[indexToCheck];
            valueToCheck = 0;

            for(int k = j; k <= currentDigit - 1; k++){
                valueToCheck += previous[k];
            }
            valueToCheck += toChoose;

            if (valueToCheck > 0){
                if(sign != '+') return false;
            } else if (valueToCheck == 0){
                if(sign != '0') return false;
            } else{
                if(sign != '-') return false;
            }
        }
        return true;
    }
}