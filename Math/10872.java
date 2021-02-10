import java.io.*;

class Main10872 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(factorial(Integer.parseInt(br.readLine()))));
        bw.flush();
        bw.close();
    }

    static long factorial(int n) {
        long res = 1;
        for (int i = n; i >= 1; i--)
            res *= i;
        return res;
    }
}