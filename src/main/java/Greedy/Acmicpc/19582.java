package Greedy.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;

class Main19583 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] competition = new int[N][2];
        int[] accumulator = new int[N - 1];

        final int LIMIT = 0, PRICE = 1;

        int i, temp;
        int flag = -1;
        int maxIdx = 0;
        int excludeIdx = -1;

        for (i = 0; i < N; i++) {
            competition[i] = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

            if (i == 0)  continue;

            if (competition[maxIdx][PRICE] < competition[i][PRICE]) maxIdx = i;

            temp = i == 1 ? competition[i - 1][PRICE] : accumulator[i - 2] + competition[i - 1][PRICE];

            if (temp <= competition[i][LIMIT])
                accumulator[i - 1] = temp;
            else {
                if (flag == -1) {
                    if (i == 1) {
                        accumulator[i - 1] = 0;
                    } else {
                        accumulator[i - 1] = accumulator[i - 2];
                    }
                    excludeIdx = i - 1;
                    flag++;
                } else {
//                    accumulator[i] = competition[excludeIdx][PRICE]
                    flag = -2;
                }
            }
        }

        for (int k : accumulator)
            System.out.print(k + " ");

        if (flag != -2)
            bw.write("kkeo-eok");
        else
            bw.write("Zzz");
        bw.flush();
        bw.close();
    }
}