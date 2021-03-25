package Backtrack.Acmicpc.resolveNM;

import java.io.*;
import java.util.regex.Pattern;
import java.util.*;

class Main10{
    /* < 중복 있는 > 입력 받은 자연수 중 M개를 고른 수열 + 비내림차순 */

    static int candidateN; // 올 수 있는 경우의 가짓수
    static int maxDepth; // 수열의 길이, 수열의 각 자리를 고르고 재귀호출 -> 호출 depth
    static int[] choiceIdx; // 각 자리마다 선택한 값에 해당되는 index를 저장한 배열
    static int[] numbers; // 실제 선택의 대상이 될 후보군 (사용자 입력)
    static StringBuilder stb = new StringBuilder();
    static StringBuilder localStb;

    static Set<String> set = new LinkedHashSet<>(); // 중복 제거

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        candidateN = input[0]; maxDepth = input[1];
        choiceIdx = new int[maxDepth];

        numbers = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);
        Arrays.fill(choiceIdx, -1);

        backtrack(0, -1);

        for(String s: set) stb.append(s);

        System.out.println(stb.toString());
    }
    static void backtrack(int depth, int previousIdx){
        if (depth == maxDepth){
            localStb = new StringBuilder();
            for (int i = 0; i < maxDepth; i++) localStb.append(numbers[choiceIdx[i]]).append(" ");
            localStb.append("\n");
            set.add(localStb.toString());
            return;
        }
        for(int candidateIdx = previousIdx + 1; candidateIdx < candidateN; candidateIdx++){
            choiceIdx[depth] = candidateIdx;
            backtrack(depth + 1, candidateIdx);
            choiceIdx[depth] = -1;
        }
    }
}