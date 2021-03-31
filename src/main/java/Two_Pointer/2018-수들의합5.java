package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main2018{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int result = 1;
        int start = 0, end;
        int sum;

        for(start = 1; start <= N / 2; start++){
            sum = start;

            for(end = start + 1; end <= N; end++){
                sum += end;
                if (sum == N) result++;
                if (sum >= N) break;
            }
        }

        System.out.println(result);
    }
}