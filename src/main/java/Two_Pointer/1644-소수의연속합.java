package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

class Main1644{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));
    }
    static int solution(int N){
        int result = 0;
        int start = 0, end = 0, sum = 0;
        int[] primeNumbers = getPrimeNumbers(N);

        while(true){
            if (sum >= N) {sum -= primeNumbers[start++];}
            else if (end == primeNumbers.length) break;
            else sum += primeNumbers[end++];

            if (sum == N) result++;
        }

        return result;
    }
    static int[] getPrimeNumbers(int N){
        int[] localPrime = new int[N + 1];
        for (int i = 2; i <= N; i++) localPrime[i] = i;
        for (int i = 2; i <= N; i++) {
            if(localPrime[i] == 0) continue;

            for (int j = 2 * i; j <= N; j += i){
                localPrime[j] = 0;
            }
        }
        return Arrays.stream(localPrime).filter(x -> x != 0).toArray();
    }
}