package Roadmap.Lv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

class 약수구하기_2501 {
    int N, K;

    public 약수구하기_2501(int n, int k) {
        N = n;
        K = k;
    }

    int solve() {
        ArrayList<Integer> divisors = new ArrayList<>();

        for (int i = 1; i * i <= N; i++) {
            if(N % i == 0){
                divisors.add(i);
                if(i != N / i) divisors.add(N / i);
            }
        }
        if(divisors.size() < K) return 0;

        Collections.sort(divisors);
        return divisors.get(K - 1);
    }
}

class MainA2501{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        System.out.println(new 약수구하기_2501(input[0], input[1]).solve());
    }
}
