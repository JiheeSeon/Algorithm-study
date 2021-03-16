package Math.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;

class Main2609{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int gcd = getGCD(input[0], input[1]);
        long lcm = (long) input[0] * input[1] / gcd; //LCM 범위는 int를 넘어갈 수 있음에 주의
        stb.append(gcd).append("\n");
        stb.append(lcm);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();

    }
    static int getGCD(int a, int b){
        if(b == 0)
            return a;
        else
            return getGCD(b, a%b);
    }
}