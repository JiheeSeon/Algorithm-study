package Two_Pointer;

import java.io.*;
import java.util.regex.Pattern;
import java.util.Arrays;

class Main1940 {
    static int N, M;
    static int[] materials;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        materials = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(materials);

        solution();
        System.out.println(result);
    }

    static void solution() {
        if (N == 1) return;

        int base, current, sum;
        int benchmarkIdx = -1;

        for (int i = 0; i < N - 1; i++){
            current = materials[i] + materials[i + 1];

            if (current >= M){
                benchmarkIdx = i + 1;
                break;
            }
        }
        if (benchmarkIdx == -1) return;

        for(int start = 0; start < benchmarkIdx; start++){
            base = materials[start];

            for (int end = benchmarkIdx; end < N; end++){
                sum = base + materials[end];

                if (sum >= M){
                    if (sum == M) result++;
                    break;
                }
            }
        }
    }
}