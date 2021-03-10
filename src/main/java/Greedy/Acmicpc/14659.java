package Greedy.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;

class Main14659{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        int[] peaks = processInput(T);
        int max = 0; int temp;
        for (int i = 0; i < peaks.length; i++){
            temp = 0;
            for (int j = i + 1; j < peaks.length; j++){
                if (peaks[j] > peaks[i]) break;
                temp++;
            }
            if (temp > max) max = temp;
        }
        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }
    static int[] processInput(int limitSize) throws IOException{
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(limitSize).toArray();
    }
}