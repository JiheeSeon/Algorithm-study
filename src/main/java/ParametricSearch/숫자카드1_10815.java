package ParametricSearch;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class 숫자카드1{
    long N, M;
    long[] cards, toCheck;

    public 숫자카드1(long N, long M, long[] cards, long[] toCheck){
        this.N = N;
        this.M = M;
        this.cards = cards;
        this.toCheck = toCheck;
    }

    int binary_search(long target){
        int low = 0;
        int high = (int)N - 1;
        int mid;

        while(low <= high){
            mid = (low + high) / 2;
            if(cards[mid] == target) return 1;

            if(cards[mid] > target) high = mid - 1;
            else low = mid + 1;
        }

        return 0;
    }

    String getAns(){
        Arrays.sort(cards);
        StringBuilder stb = new StringBuilder();
        for(long target: toCheck){
            stb.append(binary_search(target)).append(" ");
        }
        return stb.toString();
    }
}

class MainA10815 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] cards = strToLongArr(br.readLine());
        int M = Integer.parseInt(br.readLine());
        long[] toCheck = strToLongArr(br.readLine());

        System.out.println(new 숫자카드1(N, M, cards, toCheck).getAns());
    }

    static long[] strToLongArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToLong(Long::parseLong).toArray();
    }
}