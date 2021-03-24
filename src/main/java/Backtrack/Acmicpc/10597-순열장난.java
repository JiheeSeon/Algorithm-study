package Backtrack.Acmicpc;

import java.io.*;

class Main10597{
    static StringBuilder stb =  new StringBuilder();
    static String input;
    static int N;
    static boolean flagToBreak = false;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        N = (input.length() < 10) ? input.length(): 9 + ((input.length() - 9) / 2);
        visited = new boolean[N + 1];
        visited[0] = true;

        backtrack(0);

        System.out.println(stb.toString());
    }
    static void backtrack(int currDigit){
        int currentIntValue;
        int end = currDigit == input.length() - 1 ? 1 : 2;

        for (int i = 1; i <= end; i++){
            currentIntValue = Integer.parseInt(input.substring(currDigit, currDigit + i));
            if (currentIntValue > N) break;
            if (visited[currentIntValue]) continue;
            
            stb.append(currentIntValue).append(" ");
            visited[currentIntValue] = true;

            if (currDigit + i >= input.length()){
                flagToBreak = true;
                break;
            } else backtrack(currDigit + i);

            if (flagToBreak) break;
            if (currentIntValue >= 10) stb.setLength(stb.length() - 3);
            else stb.setLength(stb.length() - 2);

            visited[currentIntValue] = false;
;        }
    }
}