import java.io.*;

class Main1316 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testcaseN = Integer.parseInt(br.readLine());
        String input;
        boolean groupWordFlag = true;
        int result = 0;

        System.out.println("what the fuck");
        for (int i = 0; i < testcaseN; i++) {
            input = br.readLine();
            for (int k = 0; k < input.length(); i++) {
                if (input.matches(input.charAt(k) +"*"+ input.charAt(k))) {
                    groupWordFlag = false;
                    break;
                }
            }
            if (groupWordFlag)
                result += 1;

        }
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
}