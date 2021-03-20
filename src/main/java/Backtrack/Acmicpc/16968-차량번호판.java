package Backtrack.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main16968 {
    static String inputStr;

    static int defaultC = 26;
    static int defaultD = 10;


    static int result = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputStr = br.readLine();

        char before = ' ', current;
        for (int i = 0; i < inputStr.length(); i++) {
            current = inputStr.charAt(i);

            if (current == 'c') {
                if (before == current) result *= --defaultC;
                else{
                    defaultD = 10;
                    result *= defaultC;
                }
            } else {
                if (before == current) result *= --defaultD;
                else{
                    defaultC = 26;
                    result *= defaultD;
                }
            }

            before = current;
        }
        System.out.println(result);

    }

}