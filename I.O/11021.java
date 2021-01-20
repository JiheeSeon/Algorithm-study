import java.io.*;
import java.util.regex.Pattern;

class Main11021{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int idx = 1;
        while(idx <= T) {
            stb.append("Case #").append(idx++).append(": ").append(Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).sum()).append("\n");
        }
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}