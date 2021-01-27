import java.io.*;
import java.util.stream.IntStream;

class Main11720{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcaseN = Integer.parseInt(br.readLine());
        int sum = br.readLine().chars().mapToObj(a->(char)a).mapToInt(Character::getNumericValue).limit(testcaseN).sum();

        bw.write(Integer.toString(sum));
        bw.flush();
        bw.close();
    }
}