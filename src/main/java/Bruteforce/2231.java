package Bruteforce;

import java.io.*;

class Main2231 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        final int MAX_AVAILABLE = 1000054;

        int[] constructor = new int[MAX_AVAILABLE];

        int quotient, decomposeSum;

        for (int i = 1; i <= N; i++) {
            quotient = decomposeSum = i;

            while(true){
                decomposeSum += quotient % 10;
                if (quotient / 10 == 0) break;
                quotient /= 10;
            }

            if (decomposeSum > MAX_AVAILABLE) break;
            if (constructor[decomposeSum] == 0) constructor[decomposeSum] = i;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(constructor[N]));
        bw.flush();
        bw.close();
    }
}