package Greedy.Acmicpc;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main20044{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numOfTeam = Integer.parseInt(br.readLine());
        int[] competency = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(numOfTeam * 2).toArray();
        Arrays.sort(competency);

        int result = 200000, temp;
        for (int i = 0; i < competency.length; i++){
            temp = competency[i] + competency[competency.length - i - 1];
            if (temp < result) result = temp;
        }
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
}