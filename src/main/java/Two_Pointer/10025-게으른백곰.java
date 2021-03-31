package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

class Main10025{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int N = input[0]; int K = input[1];

        int[][] iceInput = new int [N][2];
        for(int i = 0; i < N; i++){
            iceInput[i] = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        }
        Arrays.sort(iceInput, Comparator.comparingInt(x->x[1]));

        final int AMOUNT = 0;
        final int POSITION = 1;
        int [] ice = new int[iceInput[N - 1][1] + 1];

        for (int[] i : iceInput){
            ice[i[POSITION]] = i[AMOUNT];
        }

        int sum = 0; int result;

        if (ice.length <= 2 * K){
            for (int j : ice) sum += j;
            System.out.println(sum);
        }else {
            for (int i = 0; i <= 2 * K; i++) sum += ice[i];
            result = sum;

            int start = 0, end = 2 * K + 1;

            while (end < ice.length) {
                sum -= ice[start++];
                sum += ice[end++];

                if (sum > result) {
                    result = sum;
                }
            }

            System.out.println(result);
        }
    }
}