package String;
import java.io.*;
import java.util.StringTokenizer;

class Main1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        String inputLine = br.readLine();
        int result = 0;

        st = new StringTokenizer(inputLine.substring(inputLine.indexOf("-")), "-+");

        if (inputLine.indexOf("-") != 0)
            result += Integer.parseInt(inputLine.split("-")[0]);


        if (inputLine.lastIndexOf("-") >= inputLine.lastIndexOf("+")) { //- 부호로 끝나는 경우
            st = new StringTokenizer(inputLine.substring(inputLine.indexOf("-"), inputLine.lastIndexOf("-")), "-+");
            result -= Integer.parseInt(inputLine.substring(inputLine.lastIndexOf("-") + 1));
            System.out.println(inputLine.substring(inputLine.lastIndexOf("-") + 1));
        }

        while (st.hasMoreTokens())
            result -= Integer.parseInt(st.nextToken());

        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
}