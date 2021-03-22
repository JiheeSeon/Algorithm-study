package Backtrack.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main3980{
    static int result;
    static int[][] competence = new int[11][11]; // i : 선수 j : 포지션 -> 능력치의 합의 최댓값
    static int[] previous;                      // 선수 -> 능력치
    static boolean[] alreadyChosen;             // 포지션 -> 선택되었는지 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int numOfTestCase = Integer.parseInt(br.readLine());
        for(int t = 0; t < numOfTestCase; t++) {

            for (int player = 0; player < 11; player++) {
                competence[player] = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            }
            previous = new int[11];
            alreadyChosen = new boolean[11];
            result = 0;

            backtrack(0);
            stb.append(result).append("\n");
        }
        System.out.println(stb.toString());
    }
    static void backtrack(int playerN){
        int sum;
        for(int pos = 0; pos < 11; pos++){
            if(alreadyChosen[pos] || competence[playerN][pos] == 0) continue;

            previous[playerN] = competence[playerN][pos];
            alreadyChosen[pos] = true;

            if (playerN == 10){
                sum = Arrays.stream(previous).sum();
                if (sum > result) result = sum;
            } else backtrack(playerN + 1);

            previous[playerN] = 0;
            alreadyChosen[pos] = false;
        }
    }
}