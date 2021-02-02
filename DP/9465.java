import java.io.*;
import java.util.regex.Pattern;

class Main9465 {
    static long[][] score;  // 2 X T+1
    static long[][] memo;

//    final static int RESULT = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int T;
//        memo = new int[4][N + 1];


        for (int i = 1; i <= N; i++) {
            T = Integer.parseInt(br.readLine());
            score = new long[2][T + 1];
            memo = new long[3][T + 1];

            score[0] = Pattern.compile(" ").splitAsStream("0 " + br.readLine()).mapToLong(Long::parseLong).limit(T + 1).toArray();
            score[1] = Pattern.compile(" ").splitAsStream("0 " + br.readLine()).mapToLong(Long::parseLong).limit(T + 1).toArray();

            for (int j = 1; j <= T; j++)
                solveWithDpBottomUp(j);

            stb.append(Long.toString(
                    Math.max(
                            Math.max(memo[0][T], memo[1][T])
                            , memo[2][T])))
                    .append("\n");
        }
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void solveWithDpBottomUp(int n) {
        memo[0][n] = Math.max(Math.max(memo[0][n - 1], memo[1][n - 1]), memo[2][n - 1]);
        memo[1][n] = Math.max(memo[0][n - 1], memo[2][n - 1]) + score[0][n];
        memo[2][n] = Math.max(memo[0][n - 1], memo[1][n - 1]) + score[1][n];
    }
}

//        switch (n) {
//            case 1 -> {
//                memo[0][n] = score[0][n];
//                memo[1][n] = score[1][n];
//                memo[2][n] = 0;
//                score[RESULT][n] = Math.max(memo[0][n], memo[1][n]);
//            }
//            case 2 -> {
//                memo[0][n] = memo[0][n - 1] + score[1][n];
//                memo[1][n] = memo[1][n - 1] + score[0][n];
//                memo[RESULT][n] = Math.max(memo[0][n], memo[1][n]);
//            }
//            default -> {
//                memo[0][n] = memo[0][n - 1] + score[(n - 1) % 2][n];
//                memo[1][n] = memo[1][n - 1] + score[n % 2][n];
//                memo[2][n] = memo[2][n - 2] + score[n][n];
//            }
//        }
