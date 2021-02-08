import java.io.*;
import java.util.regex.Pattern;

class Main11053{
    static long[] memo;
    static long[] amount;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        memo = new long[N + 1];
        amount = new long[N + 1];

        amount = Pattern.compile(" ").splitAsStream("0 "+br.readLine()).limit(N + 1).mapToLong(Long::parseLong).toArray();

        for(int i = 1 ; i <= N ; i++) {
            memo[i] = 1;
            solveWithDP(i);
        }

        bw.write(Long.toString(memo[N]));
        bw.flush();
        bw.close();
    }

    static void solveWithDP(int n){
        for(int j = 1; j <= n ; j++){
            if(memo[n] < memo[j] + 1 && amount[j] < amount[n])
                memo[n] = memo[j] + 1;
        }
    }
}