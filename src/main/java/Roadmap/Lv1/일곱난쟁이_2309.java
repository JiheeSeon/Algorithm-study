package Roadmap.Lv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class 일곱난쟁이_2309 {
    int[] heights;

    public 일곱난쟁이_2309(int[] heights) {
        this.heights = heights;
    }

    String solve() {
        LinkedList<Integer> chosenIndices = new LinkedList<>();
        backtrack(0, 0, 0, chosenIndices);
        StringBuilder stb = new StringBuilder();
        chosenIndices.forEach(idx -> stb.append(heights[idx]).append("\n"));
        return stb.toString();
    }

    private boolean backtrack(int previousIdx, int level, int sum, LinkedList<Integer> chosenIndices) {
        if(level == 7) return sum == 100;

        for (int i = previousIdx + 1; i <= 9; i++) {
            chosenIndices.add(i);
            if(backtrack(i, level + 1, sum + heights[i], chosenIndices)) return true;
            chosenIndices.removeLast();
        }
        return false;
    }
}

class MainA2309{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] heights = new int[10];
        for (int i = 1; i <= 9; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(heights);
        System.out.print(new 일곱난쟁이_2309(heights).solve());
    }
}