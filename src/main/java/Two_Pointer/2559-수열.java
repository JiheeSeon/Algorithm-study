package Two_Pointer;

import java.io.*;
import java.util.regex.Pattern;

class Main2558{

    static int[] temperatures;
    static int N, K;
    static int result;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0]; K = input[1];
        temperatures = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        solution();
        System.out.println(result);
    }
    static void solution(){
        int sum = 0;

        for(int i = 0; i < K; i++){sum += temperatures[i];}
        result = sum;

        int start = 0;
        int end = K;

        while (end < N){
            sum -= temperatures[start++];
            sum += temperatures[end++];
            result = Math.max(sum, result);
        }
    }
}
