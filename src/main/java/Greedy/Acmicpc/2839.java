package Greedy.Acmicpc;

import java.io.*;

class Main2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int i, j;

        int[] greedy = new int[N + 1];

        int[] temp = {0, -1, -1, 1, -1, 1, 2, -1, 2, 3, 2};

        int candidate;

        if (N % 5 == 0) greedy[N] = N / 5;
        else {
            for (i = 1; i <= N; i++) {
                if (i < temp.length) greedy[i] = temp[i];
                else {
                    for (j = 1; j < i / 3; j++) {
                        if (i % 5 == 0) greedy[i] = i / 5;
                        else {

                            if (greedy[i - 3 * j] != -1) {
                                candidate = greedy[3 * j] + greedy[i - 3 * j];

                                if (greedy[i] == 0 || greedy[i] > candidate) {
                                    greedy[i] = candidate;
                                }
                            }
                        }
                    }
                }
            }
        }

        bw.write(Integer.toString(greedy[N]));
        bw.flush();
        bw.close();
    }
}