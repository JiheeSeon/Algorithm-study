import java.io.*;
import java.util.regex.Pattern;

class Main4796{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] input;
        int L, P, V, i = 0;
        int quotient, to_add;
        while(true){
            input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 0 && input[1] == 0 && input[2] == 0)
                break;
            L = input[0]; P = input[1]; V = input[2];
            quotient = (V / P) * L;
            to_add = (V % P) / L >= 1 ? L : (V % P) % L;

            stb.append("Case ").append(++i).append(": ").append(quotient + to_add).append("\n");
        }

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}