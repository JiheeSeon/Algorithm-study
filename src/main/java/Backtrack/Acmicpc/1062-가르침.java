package Backtrack.Acmicpc;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class Main1062 {
    static int N, K;
    static String[] pureStr;

    static Set<Character> previous;

    static int spare, localSpare;
    static int localRes = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0]; K = input[1];

        spare = K > 5 ? K - 5 : 0;

        pureStr = new String[N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            pureStr[i] = s.substring(4, s.length() - 4);
        }

        if (K < 5) max = 0;
        else {
            for (int i = 0; i < N; i++) {
                localSpare = spare;
                previous = new HashSet<>(Arrays.asList('a', 'c', 't', 'i', 'n'));
                backtrack(i);
//                System.out.println(localRes);
                localRes = 0;
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }

    static void backtrack(int current) {
        checkOwnValidity(current);

        for(int i = 0; i < N; i++) {
            if(i != current){
                checkOwnValidity(i);
            }
        }

        if(localRes > max) max = localRes;

        previous.removeIf(x -> x != 'a' || x != 'c' || x != 't' || x != 'i' || x != 'n');
    }

    static void checkOwnValidity(int current){
        for (int i = 0; i < pureStr[current].length(); i++) {
            char c = pureStr[current].charAt(i);

            if (previous.contains(c)) continue;

            if (localSpare <= 0) {
                previous.removeIf(x -> x != 'a' || x != 'c' || x != 't' || x != 'i' || x != 'n');
                return;
            }
//            printt();
            previous.add(c);
            localSpare--;

//            System.out.println("local spare = " + localSpare);
        }
        localRes++;
    }

    static void printt(){
        for(char i : previous){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}