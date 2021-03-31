package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main2118{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] clockwiseCoordinate = new int[N];

        int i; long pos = 0;

        for(i = 0; i < N; i++){
            clockwiseCoordinate[i] = Integer.parseInt(br.readLine());
            pos += clockwiseCoordinate[i];
        }

        int start = 0, end = 0;
        int sum = 0;

        long benchmark = pos / 2;
        long approximate = Long.MAX_VALUE;

        while(true){
            if (sum == benchmark){
                approximate = benchmark;
                break;
            } else if (sum > benchmark){
                if (Math.abs(sum - benchmark)
                        < Math.abs(benchmark - approximate)) approximate = sum;
                sum -= clockwiseCoordinate[start++];
            } else if (end == N) break;
            else sum += clockwiseCoordinate[end++];
        }

        System.out.println(approximate);
    }
}