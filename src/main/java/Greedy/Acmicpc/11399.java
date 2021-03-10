package Greedy.Acmicpc;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main11399{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] withdrawalTime = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Withdrawal[] withdrawals = new Withdrawal[N];

        for(int i = 0; i < N; i++)
            withdrawals[i] = new Withdrawal(i, withdrawalTime[i]);

        Arrays.sort(withdrawals);

        int res = 0;
        for(int i = 0; i < N ; i++)
            res += (N - i) * withdrawals[i].time;

        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }
    private static class Withdrawal implements Comparable<Withdrawal>{
        int pNum, time;

        public Withdrawal(int pNum, int time){
            this.pNum = pNum;
            this.time = time;
        }
        @Override
        public int compareTo(Withdrawal o) {
            return this.time > o.time ? 1 : -1;
        }
    }
}