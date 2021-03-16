package Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class GreedyBasics {
    static int lengthOfSequence, numOfChars;
    static char[] alphabets;
    static StringBuilder totalStb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        lengthOfSequence = input[0]; numOfChars = input[1];
        alphabets = br.readLine().replaceAll(" ", "").toCharArray();
        Arrays.sort(alphabets);

        solve();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        totalStb.setLength(totalStb.length() - 1);
        bw.write(totalStb.toString());
        bw.flush();
        bw.close();
    }

    static void solve(){
        for(int i = 0; i <= numOfChars - lengthOfSequence + 1; i++){
            StringBuilder localStb;
            localStb = new StringBuilder();
            localStb.append(alphabets[i]);
            backtrack( 1, i, localStb);
        }
    }

    static void backtrack(int digit, int index, StringBuilder localStb){
        if(digit == lengthOfSequence){
            totalStb.append(localStb.toString()).append("\n");
            return;
        }
        for(int i = index + 1; i <= numOfChars - lengthOfSequence + digit; i++){
            localStb.append(alphabets[i]);
            backtrack(digit + 1, i, localStb);
            localStb.setLength(digit);
        }
    }
}