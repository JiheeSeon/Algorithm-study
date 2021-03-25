package Backtrack.Acmicpc.resolveNM;

import java.io.*;
import java.util.regex.Pattern;
import java.util.*;

class Main6{
    /* 입력 받은 자연수 중 M개를 고른 수열 + 오름차순 */

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

        backtrack(0, -1); // index는 0부터 시작해야하므로 -1로 trigger 설정
        System.out.println(stb.toString());
    }
    static void backtrack(int depth, int previousIndex){
        if (depth == maxDepth){
            // index 에 대응되는 값을 출력
            for (int i = 0; i < maxDepth; i++) stb.append(numbers[choiceIdx[i]]).append(" ");
            stb.append("\n");
            return;
        }
        for(int candidateIdx = previousIndex + 1; candidateIdx < candidateN; candidateIdx++){
            // 값이 아니라 index로 저장할 때의 효과 -> 조건문 쓰지 않고 for문 start로 조정 가능
            choiceIdx[depth] = candidateIdx;
            backtrack(depth + 1, candidateIdx);
            choiceIdx[depth] = -1;
        }
    }
}