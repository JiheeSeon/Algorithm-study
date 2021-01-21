import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        bw.write(Integer.toString(T));
        bw.newLine();
        bw.write(IntStream.range(1, T).map(i -> T - i).mapToObj(Integer::toString).collect(Collectors.joining("\n")));
        bw.flush();
        bw.close();
    }
}