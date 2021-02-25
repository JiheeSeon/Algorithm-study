package String;
import java.io.*;

class Main2675 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            final String[] line = br.readLine().split(" ");
            int R = Integer.parseInt(line[0]);
            line[1].chars().mapToObj(a -> String.valueOf((char) a)).forEach(a -> stb.append(a.repeat(R)));
            stb.append("\n");
        }
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}