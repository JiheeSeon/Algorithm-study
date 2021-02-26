package Bruteforce;

import java.io.*;
import java.util.regex.Pattern;

class Main2098{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] input = new int[N][N];
        for(int i = 0; i < N; i++)
            input[i] = Pattern.compile(" ").splitAsStream(" ").mapToInt(Integer::parseInt).toArray();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(" ");
        bw.flush();
        bw.close();
    }

}