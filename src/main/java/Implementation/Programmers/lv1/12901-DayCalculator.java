package Implementation.Programmers.lv1;

import java.io.*;
import java.util.regex.Pattern;

class Main12901{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(solution(input[0], input[1]));
        bw.flush();
        bw.close();
    }
    static String solution(int a, int b) {
        String[] days = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
        int[] numOfDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int extendedDay = b;
        for (int i = 0; i < a - 1; i++)
            extendedDay += numOfDays[i];

        return days[extendedDay % 7];
    }
}