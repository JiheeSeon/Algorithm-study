package Backtrack.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class Main15918{
    static int result = 0;
    static int maxDepth;
    static int N, x, y;
    static int[] spare;
    static int[] choice;
    static int toFill;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0]; x = input[1]; y = input[2];

        spare = new int[N + 1]; // 0은 무의미, 1부터 남은 spare 수
        for(int i = 0; i <= N; i++){
            if (i == 0 || i == (y - x - 1)) continue;
            spare[i] = 2;
        }

        maxDepth = 2 * N;
        choice = new int[maxDepth];
        choice[x - 1] = y - x - 1; choice[y - 1] = y - x - 1;
        toFill = maxDepth - 2;

        backtrack(0);

        System.out.println(result);
    }
    static void backtrack(int depth){
        if (depth == maxDepth){
            result++;
            return;
        }

        if (choice[depth] != 0
                || depth == (y - 1) || depth == (x - 1)
                || toFill == 0) {
            backtrack(depth + 1);
            return;
        }

        for (int candidate = 1; candidate <= N; candidate++) {
            if (spare[candidate] == 0) continue;

            if (depth + candidate + 1 >= choice.length
                    || choice[depth + candidate + 1] != 0) {
                if (toFill == 2) return;
                continue;
            }

            choice[depth] = candidate;
            choice[depth + candidate + 1] = candidate;
            spare[candidate] = 0;
            toFill -= 2;

            backtrack(depth + 1);

            toFill += 2;
            spare[candidate] = 2;
            choice[depth] = 0;
            choice[depth + candidate + 1] = 0;
        }
    }
}