import java.io.*;
import java.util.regex.Pattern;

class Main2667 {
    static final int MAX = 1000000 + 1;
    static int[] check = new int[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        bw.write(Integer.toString(1));
        bw.flush();
        bw.close();
    }

}