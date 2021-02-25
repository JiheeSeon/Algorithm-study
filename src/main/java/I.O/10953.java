package I.O;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;


class Main10953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = 0;
        int num = Integer.parseInt(br.readLine());
        while (i++ < num) {
            System.out.println(Pattern.compile(",").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).sum());
        }
    }
}