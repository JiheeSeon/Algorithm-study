package Backtrack.Acmicpc.resolveNM;

import java.io.*;
import java.util.regex.Pattern;
import java.util.*;

class Main8{
    /* 입력 받은 자연수 중 M개를 고른 수열 + 같은 수를 여러번 골라도 됨 */

    static int candidateN; // 올 수 있는 경우의 가짓수
    static int maxDepth; // 수열의 길이, 수열의 각 자리를 고르고 재귀호출 -> 호출 depth
    static int[] choiceIdx; // 각 자리마다 선택한 값에 해당되는 index를 저장한 배열
    static int[] numbers; // 실제 선택의 대상이 될 후보군 (사용자 입력)
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        candidateN = input[0]; maxDepth = input[1];
        choiceIdx = new int[maxDepth];

        numbers = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);
        Arrays.fill(choiceIdx, -1);

        backtrack(0, 0); // 이전 index 이상이므로 0으로 초기 세팅
        System.out.println(stb.toString());
    }
    static void backtrack(int depth, int previousIdx){
        if (depth == maxDepth){
            for (int i = 0; i < maxDepth; i++) stb.append(numbers[choiceIdx[i]]).append(" ");
            stb.append("\n");
            return;
        }
        for(int candidateIdx = previousIdx; candidateIdx < candidateN; candidateIdx++){
            // 비내림차순 -> 이전 index 이상
            choiceIdx[depth] = candidateIdx;
            backtrack(depth + 1, candidateIdx);
            choiceIdx[depth] = -1;
        }
    }
}