package Backtrack.Acmicpc.resolveNM;

import java.io.*;
import java.util.regex.Pattern;
import java.util.*;

class Main9{
    /* < 중복 있는 > 입력 받은 자연수 중 M개를 고른 수열 */

    static int candidateN; // 올 수 있는 경우의 가짓수
    static int maxDepth; // 수열의 길이, 수열의 각 자리를 고르고 재귀호출 -> 호출 depth
    static int[] choiceIdx; // 각 자리마다 선택한 값에 해당되는 index를 저장한 배열
    static int[] numbers; // 실제 선택의 대상이 될 후보군 (사용자 입력)
    static StringBuilder stb = new StringBuilder();
    static StringBuilder localStb;

    static Set<String> set = new LinkedHashSet<>(); // 중복 제거 위함
    /* 중복 제거를 안한다면? -> 출력에서 중복
    1 7 9
    1 7 9
    1 9 7
    1 9 9
    1 9 7
    1 9 9
    7 1 9
    7 1 9
    7 9 1
    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        candidateN = input[0]; maxDepth = input[1];
        choiceIdx = new int[maxDepth];

        numbers = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);
        Arrays.fill(choiceIdx, -1);

        backtrack(0);

        for(String s: set) stb.append(s);

        System.out.println(stb.toString());
    }
    static void backtrack(int depth){
        if (depth == maxDepth){
            localStb = new StringBuilder(); //매 마지막 출력마다 초기화
            for (int i = 0; i < maxDepth; i++) localStb.append(numbers[choiceIdx[i]]).append(" ");
            localStb.append("\n");
            set.add(localStb.toString()); //바로 전체 출력에 붙이지 않고 set에 한번 넣어서 중복 제거
            return;
        }
        for(int candidateIdx = 0; candidateIdx < candidateN; candidateIdx++){
            // 앞에서 이미 선택된 것인지 index로 확인
            if (isAlreadyChosen(depth - 1, candidateIdx)) continue;

            choiceIdx[depth] = candidateIdx;
            backtrack(depth + 1);
            choiceIdx[depth] = -1;
        }
    }
    static boolean isAlreadyChosen(int previousDepth, int candidateIndex){
        for (int i = 0; i <= previousDepth; i++) {
            if (choiceIdx[i] == candidateIndex) return true;
        }
        return false;
    }
}