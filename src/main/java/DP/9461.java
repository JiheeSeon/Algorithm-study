import java.io.*;

class Main9461{
    static long[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for(int i = 1 ; i <= N ; i++) {
            int n = Integer.parseInt(br.readLine());
            memo = new long[n + 1];

            for(int j = 1 ; j <= n ; j++)
                waveSpiralSequence(j);

            stb.append(memo[n]).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString()); //memo[N]
        bw.flush();
        bw.close();
    }

    static void waveSpiralSequence(int n){
        switch (n){
            case 1, 2, 3 -> memo[n] = 1;
            case 4, 5 -> memo[n] = 2;
            default -> {
                memo[n] = memo[n - 5] + memo[n - 1];
            }
        }
    }
}