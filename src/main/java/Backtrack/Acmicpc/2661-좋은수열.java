package Backtrack.Acmicpc;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

class Main2661 {
    static int maxDigit;
    static int[] previous;
    static String sequence = "";

    static boolean noLoopAnymore = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        maxDigit = Integer.parseInt(br.readLine());
        previous = new int[maxDigit];

        backtrack(1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sequence);
        bw.flush();
        bw.close();
    }

    static void backtrack(int currDigit) {
        for (int value = 1; value <= 3; value++) {
            if(noLoopAnymore) return;

            if (currDigit != 1 && (previous[currDigit - 2] == value || !checkIfGood(currDigit, value))) continue;

            // choice
            previous[currDigit - 1] = value;

            // set result string, no loop, backtrack anymore
            if (currDigit == maxDigit){
                sequence = Arrays.stream(previous).mapToObj(Integer::toString).collect(Collectors.joining());
                noLoopAnymore = true;
            }
            else backtrack(currDigit + 1);

            // clear
            previous[currDigit - 1] = 0;
        }
    }

    static boolean checkIfGood(int currentDigit, int candidateValue) {
        if (currentDigit <= 3) return true;

        boolean danger;

        int currentIdx = currentDigit - 1;

        for (int chunkSize = 2; chunkSize <= currentDigit / 2; chunkSize++) {
            danger = true;
            for (int j = 0; j < chunkSize; j++) {
                if (!((j == 0 ? candidateValue : previous[currentIdx - j]) == previous[currentIdx - j - chunkSize])) {
                    danger = false;
                    break;
                }
            }
            if (danger) return false;
        }
        return true;
    }
}