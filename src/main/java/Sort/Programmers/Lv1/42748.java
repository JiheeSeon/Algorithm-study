package Sort.Programmers.Lv1;

import java.util.Arrays;

class Main42748 {
    public int[] solution(int[] array, int[][] commands) {
        int[] test;
        int[] result = new int[commands.length];
        int k = 0;

        for (int[] i : commands) {
            test = Arrays.copyOfRange(array, i[0] - 1,  i[1]);
            Arrays.sort(test);
            result[k++] = test[i[2] - 1];

        }
        return result;
    }
}