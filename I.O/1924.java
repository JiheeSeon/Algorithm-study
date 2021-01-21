import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/* 오늘은 2007년 1월 1일 월요일이다. 그렇다면 2007년 x월 y일은 무슨 요일일까?
*  2007년에는 1, 3, 5, 7, 8, 10, 12월은 31일까지, 4, 6, 9, 11월은 30일까지, 2월은 28일까지 있다.
* */
class Main{
    public static void main(String[] args) throws IOException {
        IntStream ins = Pattern.compile(" ").splitAsStream(new BufferedReader(new InputStreamReader(System.in)).readLine()).mapToInt(Integer::parseInt);
    }
}