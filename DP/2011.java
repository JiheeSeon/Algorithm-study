import java.io.*;

class Main2011 {
    static long[] memo;
    static String input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        int length = input.length();
        memo = new long[length + 1];

        for (int i = 1; i <= length; i++)
            getCaseNumberOfDecode(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(memo[length] % 1000000));
        bw.flush();
        bw.close();
    }

    static void getCaseNumberOfDecode(int n) {
        long lastDecodeVal;

        switch (n) {
            case 1 -> {
                lastDecodeVal = Long.parseLong(input.substring(0, n));
                memo[n] = lastDecodeVal == 0 ? 0 : 1;
            }
            case 2 -> {
                lastDecodeVal = Long.parseLong(input.substring(0, n));
                memo[n] = (lastDecodeVal >= 1 && lastDecodeVal <= 26) ? 2 : 1;
            }
            default -> {
                lastDecodeVal = Long.parseLong(input.substring(n - 2, n));
                memo[n] = memo[n - 1] % 1000000;

                if (1 <= lastDecodeVal && lastDecodeVal <= 26)
                    memo[n] = (memo[n] % 1000000 + memo[n - 2] % 1000000) % 1000000;
            }
        }
    }
}