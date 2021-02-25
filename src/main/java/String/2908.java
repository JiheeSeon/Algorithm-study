package String;
import java.io.*;

class Main2908 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        int length = input.length();
        char[] output = new char[length];

        for (int i = length - 1; i >= 0; i--) {
            output[length - 1 - i] = input.charAt(i);
        }

        String[] middle = String.valueOf(output).split(" ");

//        Arrays.toString(output).split(" ");
        bw.write((Integer.parseInt(middle[0]) > Integer.parseInt(middle[1]) ? middle[0] : middle[1]));
        bw.flush();
        bw.close();
    }
}