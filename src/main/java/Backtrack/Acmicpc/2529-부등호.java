package Backtrack.Acmicpc;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

class Main2529{
    static int signN;
    static String[] signs;

    static final int minNumber = 0;
    static final int maxNumber = 9;

    static String min = null;
    static String max = null;

    static int[] previous;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        signN = Integer.parseInt(br.readLine());
        signs = br.readLine().split(" ");

        previous = new int[signN + 1];
        Arrays.fill(previous, -1);
        backtrack(0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(max);
        bw.write("\n");
        bw.write(min);
        bw.flush();
        bw.close();
    }
    static void backtrack(int stage){
        int[] range = calculateRange(stage);
        int start = range[0]; int end = range[1];

        if(start > end) return;

        for(int i = start; i <= end; i++){
            if(!isAvailable(i, previous)) continue;
            previous[stage] = i;

            if(stage == signN){
                if(min == null) {
                    min = Arrays.stream(previous).mapToObj(Integer::toString).collect(Collectors.joining());
                }
                if(stage == signN)
                    max = Arrays.stream(previous).mapToObj(Integer::toString).collect(Collectors.joining());
            } else backtrack(stage + 1);

            previous[stage] = -1;
        }
    }
    static boolean isAvailable(int n, int[] array){
        for(int i : array){
            if(i == n) return false;
        }
        return true;
    }
    static int[] calculateRange(int stage){
        int startPoint, endPoint;
        if(stage != 0){
            if (signs[stage - 1].equals(">")) {
                startPoint = minNumber;
                endPoint = previous[stage - 1] - 1;
            }else{
                startPoint = previous[stage - 1] + 1;
                endPoint = maxNumber;
            }
        } else{
            startPoint = minNumber;
            endPoint = maxNumber;
        }
        return new int[]{startPoint, endPoint};
    }
}