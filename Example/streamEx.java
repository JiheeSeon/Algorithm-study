import java.io.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MainStreamExample {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        String s = new BufferedReader(new InputStreamReader(System.in))
                .lines().collect(Collectors.joining("\n"));

        int idx = 1;
        while(idx < T){
            Stream<String> stream = Pattern.compile(" ").splitAsStream(br.readLine());
            stb.append("Case #").append(idx++).append(": ").append(stream.collect(Collectors.joining(" + "))).append(" = ").append("\n");
        }

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}