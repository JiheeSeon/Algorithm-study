package Greedy;

import java.io.*;

class Main2847{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int level = getIntegerInput();
        int[] score = new int[level];
        for (int i = 0; i < level; i++)
            score[i] = getIntegerInput();

        int result = 0;

        for (int i = 2; i <= score.length; i++) {
            if (score[score.length - i] >= score[score.length - i + 1]) {
                result += score[score.length - i] - score[score.length - i + 1] + 1;
                score[score.length - i] = score[score.length - i + 1] - 1;
            }
        }

        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
    static int getIntegerInput() throws IOException{
        return Integer.parseInt(br.readLine());
    }
}