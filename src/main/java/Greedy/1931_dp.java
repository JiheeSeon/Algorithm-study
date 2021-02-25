package Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main1931Dp {
    static final int START = 0;
    static final int END = 1;

    static int[][] schedule;
    static int[] memo;
    static int maxStart;
    static int max = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numOfMeeting = Integer.parseInt(br.readLine());

        schedule = new int[numOfMeeting][2];
        memo = new int[numOfMeeting];

        int i, j;

        for (i = 0; i < numOfMeeting; i++)
            schedule[i] = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(schedule, (o1, o2) -> o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));

        for (j = 0; j < numOfMeeting; j++)
            setScheduleWithDp(j);

        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }

    static void setScheduleWithDp(int idx) {
        if (idx == 0) {
            memo[idx] = 1;
            return;
        }

        int nowStart = schedule[idx][START];
        int otherStart, otherEnd;

        maxStart = 0;

        for (int i = idx - 1; i >= 0; i--) {
            otherStart = schedule[i][START];
            otherEnd = schedule[i][END];

            if (otherEnd <= nowStart) {
                if (maxStart == 0) maxStart = otherStart;
                if (otherEnd <= maxStart) continue;
                if (memo[i] + 1 > memo[idx])
                    memo[idx] = memo[i] + 1;
            }
        }

        if (memo[idx] == 0) memo[idx] = 1;
        if (memo[idx] > max) max = memo[idx];
    }
}