package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// TESTCASE 1
//5
//1
//3
//4
//9
//7

// TESTCASE 2
//1
//100
//50
//70
//3
//1

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
        long sum = 0;
        long result = 0;

        while(true){
            if (sum == benchmark){ result = sum; break;}
            else if (sum > benchmark) sum -= deltas[start++];
            else if (end == N) break;
            else sum += deltas[end++];

            result = Math.max(result, Math.min(sum, perimeter - sum));
        }

        System.out.println(result);
    }
}