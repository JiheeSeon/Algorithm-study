package Backtrack.Acmicpc;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Main1062 {
    static int N, K;
    static String[] pureStr;

    static Stack<Character> previous = new Stack<>();
    static Set<Character> basic = new LinkedHashSet<>(Arrays.asList('a', 'c', 't', 'i', 'n'));
    static char[] candidates;

    static int numOfNewChars;
    static int moreReadableN;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        K = input[1];
        pureStr = new String[N];

        moreReadableN = K - 5;

        int i;
        Set<Character> temp = new LinkedHashSet<>();

        for (i = 0; i < N; i++) {
            String s = br.readLine();
            pureStr[i] = s.substring(4, s.length() - 4);
            temp.addAll(pureStr[i].chars().mapToObj((int l) -> (char) l).collect(Collectors.toList()));
        }
        temp.removeAll(basic);

        numOfNewChars = temp.size();
        candidates = new char[numOfNewChars];

        i = 0;
        for (char c : temp) { candidates[i++] = c; }

        if (K < 5) max = 0;
        else if (numOfNewChars <= K - 5) max = N;
        else backtrack(0, new int[numOfNewChars]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }

    static void backtrack(int currDigit, int[] index) {
        int res;

        for (int i = currDigit == 0 ? 0 : index[currDigit - 1] + 1; i < numOfNewChars; i++) {
            previous.push(candidates[i]);
            index[currDigit] = i;

            if (currDigit == moreReadableN - 1) {
                res = getMaxReadableString();
                max = Math.max(res, max);
            } else backtrack(currDigit + 1, index);

            previous.pop();
            index[currDigit] = 0;
        }
    }

    static int getMaxReadableString() {
        char c;
        int passed = 0;
        boolean flag;

        for (int i = 0; i < N; i++) {
            flag = true;
            for(int j = 0; j < pureStr[i].length(); j++){
                c = pureStr[i].charAt(j);
                if(!(basic.contains(c) || previous.contains(c))){
                    flag = false;
                    break;
                }
            }
            if(flag) passed++;
        }
        return passed;
    }
}