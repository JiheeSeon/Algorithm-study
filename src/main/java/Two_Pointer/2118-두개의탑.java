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

        long allSum = pos;
        long benchmark = pos / 2;
        long approximate = Long.MAX_VALUE;
        int startIdx = -1, endIdx = -1;

        long previousSum = -1;
        long won;

        while(true){
            if (sum == benchmark){
                approximate = benchmark;
                startIdx = start; endIdx = end;
                break;
            } else if (sum > benchmark){
                won = (Math.abs(sum - benchmark) > Math.abs(previousSum - benchmark)) ? previousSum : sum;

                if (Math.abs(won - benchmark)
                        < Math.abs(approximate - benchmark)){
                    startIdx = start; endIdx = end;
                    approximate = sum;
                }

                sum -= clockwiseCoordinate[start++];
                previousSum = sum;
            } else if (end == N) break;
            else{
                sum += clockwiseCoordinate[end++];
                previousSum = sum;
            }
        }
        System.out.println("startIdx = " + startIdx);
        System.out.println("endIdx = " + endIdx);
        System.out.println("approximate = " + approximate);
        System.out.println("benchmark = " + benchmark);

        System.out.println(Math.min(approximate, Math.abs(allSum - approximate)));
    }
}