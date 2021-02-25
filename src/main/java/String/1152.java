package String;

import java.io.*;
import java.util.StringTokenizer;

class Main1152{
    public static void main(String[] args) throws IOException {
        StringTokenizer stb = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine(), " ");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(stb.countTokens()));
        bw.flush();
        bw.close();
    }
}