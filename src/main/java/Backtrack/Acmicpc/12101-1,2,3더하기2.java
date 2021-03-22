package Backtrack.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.regex.Pattern;

class Main12101{
    static StringBuilder stb = new StringBuilder();
    static LinkedList<Integer> previous = new LinkedList<>();

    static int N, K;
    static int maxDigit;
    static int currentRank = 0;
    static boolean stopFlag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0]; K = input[1];

        maxDigit = N - 1;
        backtrack(N);

        if(stb.length() > 0) {
            stb.setLength(stb.length() - 1);
            System.out.println(stb.toString());
        } else{
            System.out.println(-1);
        }
    }
    static void backtrack(int number){
        for(int i = 1; i <= 3; i++){
            if(stopFlag) break;
            if(number - i < 0) continue;

            previous.add(i);

            if (number - i == 0){
                if(++currentRank == K){
                    stopFlag = true;
                    for(int prev : previous){stb.append(prev).append("+");}
                    break;
                }
            } else backtrack(number - i);

            previous.removeLast();
        }
    }
}