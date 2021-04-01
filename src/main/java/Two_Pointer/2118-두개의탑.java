package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// TESTCASE
//5
//1
//3
//4
//9
//7

class Main2118{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] deltas = new int[N];

        int i; long pos = 0;

        for(i = 0; i < N; i++){
            deltas[i] = Integer.parseInt(br.readLine());
            pos += deltas[i];
        }

        long perimeter = pos; long benchmark = perimeter / 2;
        int start = 0, end = 0;
        long sum = 0; long error = Long.MAX_VALUE;
        long result = 0;

        long currentError;
        while(true){
            if (sum == benchmark){
                error = 0;
                result = sum;
                break;
            } else if (sum > benchmark){
                currentError = Math.abs(sum - benchmark) + Math.abs((perimeter - sum) - benchmark);

                if (currentError < error){
                    error = currentError;
                    result = Math.min(sum, perimeter - sum);
                }
                sum -= deltas[start++];
            } else if (end == N) break;
            else sum += deltas[end++];
        }

        System.out.println(result);
    }
}