package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class Main1806{
    static int N; static long S;
    static int[] sequence;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();
        N = (int)input[0]; S = input[1];
        sequence = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        solution();
        System.out.println(result == Integer.MAX_VALUE ? 0 : result);
    }
    static void solution(){
        int start = 0, end = 0;
        long sum = 0;

        while(true){
            if (sum >= S) { result = Math.min(result, end - start); sum -= sequence[start++];}
            else if (end == N) break;
            else sum += sequence[end++];
        }
    }
}