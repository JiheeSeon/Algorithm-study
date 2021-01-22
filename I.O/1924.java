import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

/* 오늘은 2007년 1월 1일 월요일이다. 그렇다면 2007년 x월 y일은 무슨 요일일까?
 *  2007년에는 1, 3, 5, 7, 8, 10, 12월은 31일까지, 4, 6, 9, 11월은 30일까지, 2월은 28일까지 있다.
 * */
class Main {
    public static void main(String[] args) throws IOException {
        int[] numOfDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        IntStream.Builder builder = IntStream.builder();

        builder.add(0);
        for (int i = 1; i < numOfDay.length; i++) {
            builder.add(Arrays.stream(numOfDay).limit(i).sum());
        }
        int[] accumulatedDay = builder.build().toArray();

        String[] monthDay = new BufferedReader(new InputStreamReader(System.in)).readLine().split(" ");
        String[] dayOfWeek = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(dayOfWeek[(accumulatedDay[Integer.parseInt(monthDay[0]) - 1] + Integer.parseInt(monthDay[1])) % 7]);
        bw.flush();
        bw.close();
    }
}