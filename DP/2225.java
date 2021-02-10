import java.io.*;
import java.util.regex.Pattern;

class Main2225 {
    static int[] input;
    static long[][] memo;
    static final int MAX = 201;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        memo = new long[input[0]][input[1]];

        for(int i = 0 ; i <= input[0] ; i++)
            for(int j = 0 ; j <= input[1] ; j++)
                decomposeSum(j, j);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(memo[input[0] + 1][input[1] + 1] % 1000000000));
        bw.flush();
        bw.close();
    }

    static void decomposeSum(int n, int k){
        if(n == 1)
            memo[n][k] = k;
        else{

        }
    }
}