package Greedy.Acmicpc;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class Main1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long res = 0;

        int N = Integer.parseInt(br.readLine());
        Alphabet[] arr = new Alphabet[26];

        for (int i = 0; i < 26; i++)
            arr[i] = new Alphabet(i);

        String temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine();

            for (int j = 0; j < temp.length(); j++)
                arr[(int) temp.charAt(j) - 65].appearance.add(temp.length() - j);
        }

        for (int i = 0; i < 26; i++)
            arr[i].appearance.sort(Comparator.reverseOrder());

        Arrays.sort(arr, Comparator.comparing((Alphabet a) -> a.appearance.peek(), Comparator.reverseOrder()).thenComparing((Alphabet a) -> a.appearance.size(), Comparator.reverseOrder()));

        for (Alphabet a : arr) {
            System.out.println(a);
            for (int b : a.appearance)
                System.out.println(b);
        }


        int weight;
        for(int idx = 0; idx < 9; idx++){
            if(arr[idx].appearance.peek() == 0) break;
            System.out.println(arr[idx] + ": " + (9-idx));

            weight = 9 - idx;
            for (int k : arr[idx].appearance){
                if (k == 0) break;
                res += (weight * Math.pow(10, k - 1));
            }
            System.out.println("res = " + res);
        }
        bw.write(Long.toString(res));
        bw.flush();
        bw.close();
    }

    private static class Alphabet {
        int asciiValue;
        LinkedList<Integer> appearance;

        public Alphabet(int asciiValue) {
            this.asciiValue = asciiValue;
            appearance = new LinkedList<>();
            appearance.add(0);
        }

        @Override
        public String toString() {
            return "asciiValue=" + (char)(65 + asciiValue);
        }
    }
}