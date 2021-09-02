package Roadmap.Lv1;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MainA2460{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp;
        int cnt = 0;
        int max = -1;
        for (int i = 0; i < 10; i++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            cnt -= tmp[0];
            cnt += tmp[1];

            if(max < cnt) max = cnt;
        }
        System.out.println(max);
    }
}