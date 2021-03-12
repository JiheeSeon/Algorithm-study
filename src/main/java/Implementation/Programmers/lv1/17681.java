package Implementation.Programmers.lv1;

import java.util.*;
import java.util.regex.Pattern;

class Main17681 {
    static StringBuilder stb;

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] res = new String[n];

        int[][] processedArr1 = new int[n][n];
        int[][] processedArr2 = new int[n][n];
        int[][] processedResult = new int[n][n];

        for(int i = 0; i < arr1.length; i++){
            stb = new StringBuilder();

            toBinary(arr1[i]);

            for(int k = 0; k < n - stb.length(); k++)
                processedArr1[i][k] = 0;

            for(int k = n - stb.length(); k < n; k++)
                processedArr1[i][k] = Integer.parseInt(stb.toString().substring(k - n + stb.length(), (k - n + stb.length() + 1)));

            stb = new StringBuilder();

            toBinary(arr2[i]);
            for(int k = 0; k < n - stb.length(); k++)
                processedArr2[i][k] = 0;

            for(int k = n - stb.length(); k < n; k++)
                processedArr2[i][k] = Integer.parseInt(String.valueOf((char)stb.toString().charAt(k - n + stb.length())));

        }

        for(int i = 0; i < n; i++){
            stb = new StringBuilder();

            for(int j = 0; j < n; j++){
                processedResult[i][j] = processedArr1[i][j] | processedArr2[i][j];

                if(processedResult[i][j] == 1)
                    stb.append("#");
                else
                    stb.append(" ");
            }

            res[i] = stb.toString();
        }
        return res;
    }

    static void toBinary(int n){
        if(n == 0) return;
        toBinary(n / 2);
        stb.append(n % 2);
    }

    static String[] otherSolution(int n, int[] arr1, int[] arr2) {
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
        }

        for (int i = 0; i < n; i++) {
            result[i] = String.format("%" + n + "s", result[i]);
            result[i] = result[i].replaceAll("1", "#");
            result[i] = result[i].replaceAll("0", " ");
        }

        return result;
    }
}