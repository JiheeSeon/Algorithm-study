package Resolve;

import java.io.*;

class Resolve2156 {
    static final int endsWithX = 0;
    static final int endsWithO = 1;
    static final int maxAmountOfWine = 2;

    static long[] amountOfWine;
    static long[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        amountOfWine = new long[N + 1];
        memo = new long[3][N + 1];

        for (int i = 1; i <= N; i++) {
            amountOfWine[i] = Long.parseLong(br.readLine());
            calculateMaxWine(i);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(memo[maxAmountOfWine][N]));
        bw.flush();
        bw.close();
    }

    static void calculateMaxWine(int index) {
        switch (index) {
            case 1 -> {
                memo[endsWithX][index] = 0;
                memo[endsWithO][index] = amountOfWine[index];
                memo[maxAmountOfWine][index] = memo[1][index];
            }
            case 2 -> {
                memo[endsWithX][index] = amountOfWine[1];
                memo[endsWithO][index] = amountOfWine[1] + amountOfWine[2];
                memo[maxAmountOfWine][index] = memo[1][index];
            }
            default -> {
                memo[endsWithX][index] = memo[maxAmountOfWine][index - 1];
                memo[endsWithO][index] =
                        Math.max(
                                memo[endsWithX][index - 2] + amountOfWine[index - 1] + amountOfWine[index],
                                memo[endsWithX][index - 1] + amountOfWine[index]);
                memo[maxAmountOfWine][index] = Math.max(memo[endsWithX][index], memo[endsWithO][index]);
            }
        }
    }
}