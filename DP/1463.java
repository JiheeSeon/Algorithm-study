import java.io.*;

class Main1463 {
    static int MAX_SIZE = 1000001;
    static int[] dp = new int[MAX_SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        /*initialize DP array*/
        dp[2] = dp[3] = 1;

        for (int i = 4; i <= N; i++)
            solveWithDPBottomUp(i);

        bw.write(Integer.toString(dp[N]));
        bw.flush();
        bw.close();
    }

    static void solveWithDPBottomUp(int num) {
        int min = 1 + dp[num - 1];

        if (num % 3 == 0)
            min = Math.min(1 + dp[num / 3], min);

        if (num % 2 == 0)
            min  = Math.min(1 + dp[num / 2], min);

        dp[num] = min;
    }
}

//class SolutionWithDPTopDown {
//    static int[] dp = new int[1000001];
//
//    static int solveWithDPTopDown(int n) {
//        /*terminating condition*/
//        if (n == 1)
//            return 0;
//
//        if (n == 3 | n == 2)
//            return 1;
//
//        int max = 1000000;
//
//        int sol_minus_1 = dp[n - 1] == 0 ? solveWithDPTopDown(n - 1) : dp[n - 1];
//
//        int sol_half = n % 2 == 0
//                ? (dp[n / 2] == 0 ? solveWithDPTopDown(n / 2) : dp[n / 2])
//                : max;
//
//        int sol_one_third = n % 3 == 0
//                ? (dp[n / 3] == 0 ? solveWithDPTopDown(n / 3) : dp[n / 3])
//                : max;
//
//        int min1 = sol_one_third > sol_half ? sol_half : sol_one_third;
//        int min2 = sol_minus_1 > min1 ? min1 : sol_minus_1;
//
//        dp[n] = 1 + min2;
//
//        return dp[n];
//    }
//}


//        /*check remainder of dividing to 3*/
//        int dpDivide2 = 0; //dp[n/2]
//        int dpCloseToTriple;
//        int dpCloseToTwice;
//
//        if (n % 2 == 0)
//            dpDivide2 = dp[n / 2] == 0 ? solveWithDP(n / 2) : dp[n / 2];
//
//
//        switch (n % 3) {
//            case 0 -> {
//                int dpDivide3 = dp[n / 3] == 0 ? solveWithDP(n / 3) : dp[n / 3]; // dp[n/3]
//                dp[n] = n % 2 == 0 ? Math.min(1 + dpDivide3, 1 + dpDivide2) : 1 + dpDivide3;
//            }
//            case 1 -> {
//                dpCloseToTriple = dp[n - 1] == 0 ? solveWithDP(n - 1) : dp[n - 1];
//                dp[n] = n % 2 == 0 ? Math.min(1 + dpDivide2, 1 + dpCloseToTriple) : 1 + dpCloseToTriple;
//            }
//            case 2 -> {
//                dpCloseToTriple = dp[n - 2] == 0 ? solveWithDP(n - 2) : dp[n - 2];
//                if (n % 2 != 0) {
//                    dpCloseToTwice = dp[n - 1] == 0 ? solveWithDP(n - 1) : dp[n - 1];
//                    dp[n] = Math.min(2 + dpCloseToTriple, 1 + dpCloseToTwice);
//                } else {
//                    dp[n] = Math.min(2 + dpCloseToTriple, 1 + dpDivide2);
//                }
//            }
//        }
//
//        return dp[n];
//    }
//}