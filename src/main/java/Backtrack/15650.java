package Backtrack;

import java.io.*;
import java.util.regex.Pattern;

class Main15650 {
    static int N, M;
    static StringBuilder stbForResult = new StringBuilder();
    static StringBuilder stbForBranch = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];

        recursive(1, 0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stbForResult.toString());
        bw.flush();
        bw.close();
    }

    static void recursive(int depth, int previous) {
        if (depth > M){
            stbForResult.append(stbForBranch.toString()).append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (i <= previous) continue;
            stbForBranch.append(i).append(" ");
            recursive(depth + 1, i);
            stbForBranch.setLength(2 * (depth - 1));
        }
    }
}