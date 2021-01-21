import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class Main8393{
    public static void main(String[] args) throws IOException {
        System.out.println(IntStream.rangeClosed(1, Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine())).sum());
    }
}