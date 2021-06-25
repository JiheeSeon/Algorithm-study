package BinarySearch;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class 랜선자르기_1654 {
    int K, N;
    int[] lanCables;

    public 랜선자르기_1654(int K, int N, int[] lanCables){
        this.K = K;
        this.N = N;
        this.lanCables = lanCables;
    }

    long getAns(){
        Arrays.sort(lanCables);
        return parametric_search();
    }

    long parametric_search(){
        long low = 1;
        long high = lanCables[K - 1];
        long mid;
        long cnt;

        while(low <= high){
            mid = (low + high) / 2;

            cnt = 0;
            for(int lc : lanCables) cnt += ((long)lc / mid);

            if(cnt < N) high = mid - 1;
            else low = mid + 1;
        }
        return high;
    }
}

class MainA1654 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int K = tmp[0]; int N = tmp[1];
        int[] lanCables = new int[K];

        for(int k = 0; k < K; k++){
            lanCables[k] = Integer.parseInt(br.readLine());
        }

        System.out.println(new 랜선자르기_1654(K, N, lanCables).getAns());
    }
}