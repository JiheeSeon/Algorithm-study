package Resolve;

import java.io.*;
import java.util.regex.Pattern;

class Main9466Resolve{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {

    }
    static int[] getInputAndSplit() throws IOException{
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }
}