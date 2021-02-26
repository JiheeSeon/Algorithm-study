package Bruteforce;

import java.io.*;

class Main2231{
    static int[] res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int inputN = Integer.parseInt(inputStr);

        int length = inputStr.length();
        int[] toMultiply = new int[length];

        int i;

        /* 1. 생성자가 몇자리 수로 이루어져있는지 계산
        입력으로 받은 수의 자리수보다 작은 자리수로 이룰 수 있는 결과의 최대값을 벤치마크로 계산
        ex. 1034 -> 999 + 9 + 9 + 9
        */
        int benchmark = 9 * length; // a + b + c => 9 + 9 + 9 = 9 * length

        for (i = 0; i < length; i++)
            benchmark += 9 * Math.pow(10, i);

        if (inputN <= benchmark) length--; // 생성자의 length fix

        for (i = length; i > 1; i--){
            toMultiply[i - 1] = 1 << (i-1) | 1;
        }
        toMultiply[0] = 2;

        res = new int[length];
        int temp = inputN;

        for (i = 0; i < length; i++){
            res[i] = temp / toMultiply[i];
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(" ");
        bw.flush();
        bw.close();
    }

    static void recursive(int N, int idx){ // 현재 남은 수, 몇번째 자리,
        if (idx == 0) {
            res[idx] = N;
//            return 1;
        }
        else{
            for (int i = 0; i < 10; i++){

            }
        }
    }
}