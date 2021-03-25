package Backtrack.Acmicpc.resolveNM;

import java.io.*;
import java.util.regex.Pattern;

class Main2{
    /* 1-N 까지의 자연수 중 중복 없이 M개를 고른 수열 + 오름차순 */

    static int candidateN; // 올 수 있는 경우의 가짓수, 올 수 있는 최대 숫자
    static int maxDepth; // 수열의 길이, 수열의 각 자리를 고르고 재귀호출 -> 호출 depth
    static int[] choice; // 각 자리마다 선택한 수를 저장한 배열
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        candidateN = input[0]; maxDepth = input[1];
        choice = new int[maxDepth];

        backtrack(0, 0); //previousChoice의 초기값은 실제 와야하는 값보다 1 작도록
        System.out.println(stb.toString());
    }
    static void backtrack(int depth, int previousChoice){
        if (depth == maxDepth){
            for (int i = 0; i < maxDepth; i++) stb.append(choice[i]).append(" ");
            stb.append("\n");
            return;
        }
        for(int candidate = (previousChoice + 1); candidate <= candidateN; candidate++){
            // 오름차순이라는 점을 이용해 for 문 시작점을 이전 선택한 값보다 무조건 하나 많게 걸어줌
            // -> for 문 안에서 굳이 조건 체크를 하지 않을 수 있음.
            choice[depth] = candidate;
            backtrack(depth + 1, candidate);
            choice[depth] = 0;
        }
    }
}