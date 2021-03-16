package Backtrack.Acmicpc;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main1759_2{
    static int lengthOfSequence, numberOfAlphabets;
    static char[] alphabets;

    static StringBuilder totalStb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        lengthOfSequence = input[0]; numberOfAlphabets = input[1];
        alphabets = br.readLine().replaceAll(" ", "").toCharArray();
        Arrays.sort(alphabets);


        backtrack(1, -1, new StringBuilder());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(totalStb.toString());
        bw.flush();
        bw.close();
    }

    static void backtrack(int currDigit, int prevIndex, StringBuilder localStb){
        for(int i = prevIndex + 1; i <= numberOfAlphabets - lengthOfSequence + currDigit - 1; i++){
            localStb.append(alphabets[i]);

            if(currDigit == lengthOfSequence) {
                if(isMeaningful(localStb.toString()))
                    totalStb.append(localStb.toString()).append("\n");
                localStb.deleteCharAt(localStb.length() - 1);
                continue;
            }

            backtrack(currDigit + 1, i, localStb); // 재귀로 들어가서 마지막에 닿을 때 출력할 것
            localStb.deleteCharAt(localStb.length() - 1); // stack에서 pop하는 개념(이번에 선택한 문자 제거)
        }
    }

    static boolean isVowel(char c){
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    static boolean isMeaningful(String s){
        int countOfVowel = 0;
        int countOfConsonants = 0;

        for (int i = 0; i < s.length(); i++){
            if(isVowel(s.charAt(i))) countOfVowel++;
            else countOfConsonants++;

            if(countOfVowel >= 1 && countOfConsonants >=2) return true;
        }
        return false;
    }
}