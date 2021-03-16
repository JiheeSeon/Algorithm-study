package Math.Acmicpc;

import java.io.*;

class Main11653 {
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        factorization(Integer.parseInt(br.readLine()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void factorization(int n) {
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                stb.append(i).append("\n");
                n /= i;
            }
        }

        if (n > 1)
            stb.append(n).append("\n");
    }
}