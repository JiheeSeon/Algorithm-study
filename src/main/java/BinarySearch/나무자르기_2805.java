package BinarySearch;

import java.io.*;
import java.util.regex.Pattern;
import java.util.Arrays;

class 나무자르기_2805{
    long N, M;
    long[] trees;
    long ans = -1;

    public 나무자르기_2805(long N, long M, long[] trees){
        this.N = N;
        this.M = M;
        this.trees = trees;
    }

    long parametric_search(){
        long low = 0;
        long high = trees[(int)N - 1] - 1;
        long mid;
        long sum;

        while(low <= high){
            mid = (low + high) / 2;

            sum = 0;
            for(long i : trees){
                if(i - mid <= 0) continue;
                sum += (i - mid);
            }

            if(sum >= M) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    long getAns(){
        Arrays.sort(trees);
        return parametric_search();
    }
}

class MainA2805{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N, M;
        long[] tmp = strToLongArr(br.readLine());
        N = tmp[0]; M = tmp[1];
        long[] trees = strToLongArr(br.readLine());
        System.out.println(new 나무자르기_2805(N, M, trees).getAns());
    }

    static long[] strToLongArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToLong(Long::parseLong).toArray();
    }
}