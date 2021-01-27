import java.io.*;

class Main2941{
    public static void main(String[] args) throws IOException {
        String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        String output = input.replaceAll("c[=-]|s=|z=|d-|[ln]j|dz=", "*");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(Integer.toString(output.length()));
        bw.flush();
        bw.close();
    }
}