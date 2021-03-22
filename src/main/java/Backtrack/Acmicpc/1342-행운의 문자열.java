package Backtrack.Acmicpc;

import java.io.*;
import java.util.*;

class Main1342{
    static String input;
    static int maxDigit;
    static char[] previous;

    static Map<Character, Integer> map = new TreeMap<>();
    static Set<String> set = new HashSet<>();

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        maxDigit = input.length();
        previous = new char[maxDigit];

        char c;
        for(int i = 0; i < maxDigit; i++) {
            c = input.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        backtrack(1);

        System.out.println(result);
    }
    static void backtrack(int currDigit){
        for(char c : map.keySet()){
            if ((currDigit != 1 && c == previous[currDigit - 2]) || map.get(c) == 0) continue;

            previous[currDigit - 1] = c;
            map.put(c, map.get(c) - 1);

            if (currDigit == maxDigit) result++;
            else backtrack(currDigit + 1);

            previous[currDigit - 1] = c;
            map.put(c, map.get(c) + 1);
        }
    }
}