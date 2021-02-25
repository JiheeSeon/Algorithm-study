package DP;

import java.io.*;
import java.util.regex.Pattern;

class Main11053 {
    static long[] originalInput;
    static long[] maxLengthOfIncrementalSequenceEndsWithIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        maxLengthOfIncrementalSequenceEndsWithIndex = new long[N + 1];
        originalInput = Pattern.compile(" ").splitAsStream("0 " + br.readLine()).mapToLong(Long::parseLong).limit(N + 1).toArray();

        for (int i = 1; i <= N; i++)
            checkLongestIncrementalSequence(i);

        long result = 0;

        for(long k: maxLengthOfIncrementalSequenceEndsWithIndex)
            if (result < k)
                result = k;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(result));
        bw.flush();
        bw.close();
    }

    static void checkLongestIncrementalSequence(int n) {
        /*testcase 8   -- 10 25 20 50 30 23 40 70*/
        int maxIndex;
        long maxLength;

        switch (n) {
            case 1 -> maxLengthOfIncrementalSequenceEndsWithIndex[n] = 1;
            case 2 -> maxLengthOfIncrementalSequenceEndsWithIndex[n] = originalInput[n] > originalInput[1] ? 2 : 1;
            default -> {
                maxIndex = 0;
                maxLength = 1;

                for (int k = n - 1; k >= 1; k--) {
                    if (originalInput[k] < originalInput[n]
                            && originalInput[maxIndex] <= originalInput[k]) {
                        maxIndex = k;
                        maxLength = Math.max(maxLength, maxLengthOfIncrementalSequenceEndsWithIndex[maxIndex]);
                    }
                }
                maxLengthOfIncrementalSequenceEndsWithIndex[n] = maxIndex == 0 ? 1 : 1 + maxLength;
            }
        }
    }
}