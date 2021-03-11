package Sort.Programmers.Lv1;

import java.util.Arrays;

class Main42748 {
    public int[] solution(int[] array, int[][] commands) {
        int[] test;
        int[] result = new int[commands.length];
        int k = 0;

        for (int[] i : commands) {
            test = Arrays.copyOf(array, array.length); // O(N)

            Arrays.sort(test, i[0] - 1, i[1]); //O(NlogN)
            result[k++] = test[i[0] + i[2] - 2];
        }
        return result;
    }
}