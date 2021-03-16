package Backtrack.Acmicpc;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.io.*;

class Main1759_1 {
    /*
    Testcase 1
    [Input]
    3 5
    a b c d f

    [Output]
    abc
    abd
    abf
    acd
    acf
    adf
    */
    static int lengthOfSequence, numOfChars;
    static char[] alphabets;
    static StringBuilder totalStb = new StringBuilder();
    static LinkedList<Character> vowels = new LinkedList<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        lengthOfSequence = input[0];
        numOfChars = input[1];
        alphabets = br.readLine().replaceAll(" ", "").toCharArray();

        Arrays.sort(alphabets);
        solve();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        totalStb.setLength(totalStb.length() - 1);
        bw.write(totalStb.toString());
        bw.flush();
        bw.close();
    }

    static void solve() {
        for (int i = 0; i <= numOfChars - lengthOfSequence + 1; i++) {
            StringBuilder localStb;
            localStb = new StringBuilder();
            localStb.append(alphabets[i]);
            backtrack(1, i, localStb);
        }
    }

    static void backtrack(int digit, int index, StringBuilder localStb) {
        if (digit == lengthOfSequence) {
            if (fulfillsConstraint(localStb.toString()))
                totalStb.append(localStb.toString()).append("\n");
            return;
        }
        for (int i = index + 1; i <= numOfChars - lengthOfSequence + digit; i++) {
            localStb.append(alphabets[i]);
            backtrack(digit + 1, i, localStb);
            localStb.setLength(digit);
        }
    }

    static boolean isVowel(char c) {
        return vowels.contains(c);
    }

    static boolean fulfillsConstraint(String s) {
        int vowelCnt = 0, consonantCnt = 0;

        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) vowelCnt++;
            else consonantCnt++;
            if (consonantCnt >= 2 && vowelCnt >= 1) return true;
        }
        return false;
    }
}