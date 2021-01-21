import java.io.*;
import java.util.stream.IntStream;

class Main2739{
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        StringBuilder stb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        IntStream.rangeClosed(1,9).forEach(a-> stb.append(T).append(" * ").append(a).append(" = ").append(T * a).append("\n"));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}