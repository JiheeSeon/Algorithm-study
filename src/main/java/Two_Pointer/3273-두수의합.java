package Two_Pointer;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main3273{
    static int N;
    static int[] sequence;
    static int x;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sequence = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        x = Integer.parseInt(br.readLine());
        Arrays.sort(sequence);

        solution();
        System.out.println(result);
    }

    static void solution() {
        if (N == 1) return;

        int base, current, sum;
        int benchmarkIdx = -1;

        for (int i = 0; i < N - 1; i++){
            current = sequence[i] + sequence[i + 1];

            if (current >= x){
                benchmarkIdx = i + 1;
                break;
            }
        }
        if (benchmarkIdx == -1) return;

        for(int start = 0; start < benchmarkIdx; start++){
            base = sequence[start];

            for (int end = benchmarkIdx; end < N; end++){
                sum = base + sequence[end];

                if (sum >= x){
                    if (sum == x) result++;
                    break;
                }
            }
        }
    }
}