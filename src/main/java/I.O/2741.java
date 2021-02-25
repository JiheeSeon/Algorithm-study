package I.O;

import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main2741 {
    public static void main(String[] args) throws IOException {
        IntStream ins = IntStream.rangeClosed(1, Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(ins.mapToObj(Integer::toString).collect(Collectors.joining("\n")));
        bw.flush();
        bw.close();
    }
}