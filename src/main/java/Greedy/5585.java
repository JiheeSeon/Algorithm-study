package Greedy;

import java.io.*;

class Main5885{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] coins = {500, 100, 50, 10, 5, 1};
        int total = 1000 - Integer.parseInt(br.readLine());
        int res = 0;

        for (int coin : coins) {
            if (total / coin > 0) {
                res += (total / coin);
                total -= (coin * (total / coin));
            }
        }

        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }
}