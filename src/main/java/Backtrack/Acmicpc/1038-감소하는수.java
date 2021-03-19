package Backtrack.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main1038 {
    static int N;
    static int maxDigit;
    static long result = 0;

    static int[] record;
    static int currentRank = 9;

    static boolean flagToBreak = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 0 <= N <= 1,000,000
        if (N < 10) result = N;
        else {
            for (int i = 2; i <= 10; i++) {
                maxDigit = i;
                record = new int[maxDigit];
                backtrack(1);
                if (result != 0) break;
            }
        }
        if (N != 0 && result == 0) result = -1;
        System.out.println(result);
    }

    static void backtrack(int currDigit) {
        for (int i = 0; i <= 9; i++) {
            if(currDigit == 1 && i == 0) continue;
            if(currDigit != 1 && record[currDigit - 2] <= i) continue;

            if (flagToBreak) return;

            record[currDigit - 1] = i;

            if (currDigit == maxDigit) {
                currentRank++;

                if (currentRank == N) {
                    setResult();
                    flagToBreak = true;
                }
            } else {
                backtrack(currDigit + 1);
            }

            record[currDigit - 1] = 0;
        }
    }

    static void setResult() {
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < record.length; i++)
            stb.append(record[i]);
        result = Long.parseLong(stb.toString());
    }
}