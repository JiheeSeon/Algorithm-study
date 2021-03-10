package Greedy.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;

class Main11047{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int numOfCoins = input[0]; int totalValue = input[1];

        int [] valueOfCoins = new int[numOfCoins];

        for (int i = 0; i < numOfCoins; i++)
            valueOfCoins[i] = Integer.parseInt(br.readLine());

        int temp, res = 0;

        while(--numOfCoins >= 0){
            temp = totalValue / valueOfCoins[numOfCoins];
            if (temp > 0) {
                res += temp;
                totalValue -= (valueOfCoins[numOfCoins] * temp);
            }
        }

        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }
}