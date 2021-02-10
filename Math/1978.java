import java.io.*;
import java.util.regex.Pattern;

class Main1978 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(N).toArray();

        int totalSum = 0;
        for (int inp : input) {
            if (checkPrimeNumber(inp))
                totalSum++;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(totalSum));
        bw.flush();
        bw.close();
    }

    static boolean checkPrimeNumber(int n) {
        if(n == 1)
            return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0)
                return false;
        return true;
    }
}