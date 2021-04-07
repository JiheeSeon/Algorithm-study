package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main2230{
    static int N;
    static long minOfDifference;
    static long[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();
        N = (int)input[0]; minOfDifference = input[1];
        A = new long[N];

        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(A);
        System.out.println(solution());
    }

    static long solution() {
        long result = Long.MAX_VALUE;

        int start = 0, end = N - 1;
        while(true){
            if(start == N - 1) break;
            else if(A[end] - A[start] == minOfDifference) return minOfDifference;
            else if(A[end] - A[start] < minOfDifference){
                end = N - 1; start++;
            } else{
                if(result > A[end] - A[start])
                    result = A[end] - A[start];
                end--;
            }
        }

        return result;
    }
}
//6 3
//1
//10
//9
//8
//7
//5