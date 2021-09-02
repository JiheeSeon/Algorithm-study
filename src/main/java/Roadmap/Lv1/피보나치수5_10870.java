package Roadmap.Lv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MainA10870{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        switch (N){
            case 0 -> {System.out.println(0); return;}
            case 1, 2 -> {System.out.println(1); return;}
            default ->{
                long[] fiboNumbers = new long[N + 1];
                fiboNumbers[1] = 1; fiboNumbers[2] = 1;
                for (int i = 3; i <= N; i++) {
                    fiboNumbers[i] = fiboNumbers[i - 1] + fiboNumbers[i - 2];
                }
                System.out.println(fiboNumbers[N]);
            }
        }
    }
}