import java.io.*;
import java.util.regex.Pattern;

class Main10430{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        stb.append((input[0]%input[2] + input[1]%input[2])%input[2]).append("\n");
        stb.append((input[0]%input[2] + input[1]%input[2])%input[2]).append("\n");
        stb.append((input[0]*input[1])%input[2]).append("\n");
        stb.append((input[0]*input[1])%input[2]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}