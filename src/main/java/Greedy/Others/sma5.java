package Greedy.Others;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class sma5  {
    public static void main(String[] args) throws IOException {
        final int SCORE = 0; final int UPNUM = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N * N];

        HashMap<Integer, Integer> hm = new HashMap<>();

        int [] temp; int time;

        for(int i = 0; i < N*N; i++){
            temp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            score[i] = temp[SCORE];

            for(int j = 0; j < temp[UPNUM]; j++){
                time = temp[2 + j];
                if (!hm.containsKey(time))
                    hm.put(time, score[i]);

                else if(hm.get(time) < score[i])
                    hm.put(time, score[i]);
            }
        }

        int result = hm.values().stream().mapToInt(Integer::intValue).sum();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
}
