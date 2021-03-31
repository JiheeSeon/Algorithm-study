package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main6159{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int N = input[0]; int K = input[1];
        int[] cows = new int[N];

        for(int i = 0; i < N; i++) cows[i] = Integer.parseInt(br.readLine());
        Arrays.sort(cows);

        int sum = cows[0] + cows[1];
        int result = 0;

        int start = 0; int end = N - 1;

        for(;start < N; start++){
            if(cows[start] >= K / 2) break;
            while(end > start){
                if(cows[start] + cows[end] <= K){
                    result += (end - start);
                    break;
                }
                end--;
            }
        }

        System.out.println(result);
    }
}