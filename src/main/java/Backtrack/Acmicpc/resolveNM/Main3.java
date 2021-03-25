package Backtrack.Acmicpc.resolveNM;

import java.io.*;
import java.util.regex.Pattern;

class Main3{
    /* 1-N 까지의 자연수 중 M개를 고른 수열 + 같은 수를 여러번 골라도 됨 */

    static int candidateN; // 올 수 있는 경우의 가짓수, 올 수 있는 최대 숫자
    static int maxDepth; // 수열의 길이, 수열의 각 자리를 고르고 재귀호출 -> 호출 depth
    static int[] choice; // 각 자리마다 선택한 수를 저장한 배열
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        candidateN = input[0]; maxDepth = input[1];
        choice = new int[maxDepth];

        backtrack(0);
        System.out.println(stb.toString());
    }
    static void backtrack(int depth){
        if (depth == maxDepth){
            for (int i = 0; i < maxDepth; i++) stb.append(choice[i]).append(" ");
            stb.append("\n");
            return;
        }
        for(int candidate = 1; candidate <= candidateN; candidate++) {
            // 가장 기본형. 1-candidateN 까지 모든 수를 선택 가능, 별도의 조건이 필요 없음
            choice[depth] = candidate;
            backtrack(depth + 1);
            choice[depth] = 0;
        }
    }
}