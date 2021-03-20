package Backtrack.Acmicpc;

import java.io.*;

class Main1038{
    static int N;
    static int currentN = -1;
    static int maxDigit;
    static int[] previous;
    static boolean terminateFlag = false;
    static String result = null;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= 10; i++){
            maxDigit = i; // change maxDigit (종료조건)
            previous = new int[maxDigit]; //size of previous
            recursive(1); // start with first digit
        }
        System.out.println(result == null ? -1 : result);
    }
    static void recursive(int currDigit){
        for (int i = 0; i <= (currDigit == 1 ? 9 : previous[currDigit - 2] - 1) ; i++){
            if (terminateFlag) break;

            previous[currDigit - 1] = i;

            if(currDigit == maxDigit){
                currentN++;
                if(N == currentN){
                    terminateFlag = true;
                    setResult();
                }
            }
            else recursive(currDigit + 1);

            previous[currDigit - 1] = 0;
        }
    }
    static void setResult(){
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < previous.length; i++)
            stb.append(previous[i]);
        result = stb.toString();
    }
}