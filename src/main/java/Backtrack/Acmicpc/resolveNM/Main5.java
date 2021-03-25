package Backtrack.Acmicpc.resolveNM;

import java.io.*;
import java.util.regex.Pattern;
import java.util.*;

class Main5{
    /* 입력 받은 자연수 중 M개를 고른 수열 */

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

        backtrack(0);
        System.out.println(stb.toString());
    }
    static void backtrack(int depth){
        if (depth == maxDepth){
            // index 에 대응되는 값을 출력
            for (int i = 0; i < maxDepth; i++) stb.append(numbers[choiceIdx[i]]).append(" ");
            stb.append("\n");
            return;
        }
        for(int candidateIdx = 0; candidateIdx < candidateN; candidateIdx++){
            // 값 자체를 넣지 않고 index 저장, 앞에 저장한 index 중 있으면 안됨
            if(isAlreadyChosen(depth - 1, numbers[candidateIdx])) continue;

            choiceIdx[depth] = candidateIdx;
            backtrack(depth + 1);
            choiceIdx[depth] = -1;
        }
    }
    static boolean isAlreadyChosen(int lastIdxToCheck, int candidate){
        for (int i = 0; i <= lastIdxToCheck; i++){
            if (numbers[choiceIdx[i]] == candidate) return true;
        }
        return false;
    }
}