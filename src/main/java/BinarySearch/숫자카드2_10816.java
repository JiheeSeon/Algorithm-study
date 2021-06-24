package BinarySearch;

import java.io.*;
import java.util.regex.Pattern;
import java.util.Arrays;

class 숫자카드2_10816{
    int N, M;
    long[] cards;
    long[] toCheck;

    public 숫자카드2_10816(int N, int M, long[] cards, long[] toCheck){
        this.N = N;
        this.M = M;
        this.cards = cards;
        this.toCheck = toCheck;
    }

    int binary_search(long target){
        Arrays.sort(cards);
        int low = 0; int high = N - 1;
        int mid;
        int ans = 0;
        boolean goLeft = true;

        while(low <= high){
            mid = (low + high) / 2;
            if(cards[mid] == target){
                ans++;
                if(goLeft) high = mid - 1;
                else low = mid + 1;
            } else if(cards[mid] < target){
                low = mid + 1;
                goLeft = false;
            } else{
                high = mid - 1;
                goLeft = true;
            }
        }
        return ans;
    }

    String getAns(){
        StringBuilder stb = new StringBuilder();
        for(long target: toCheck){
            stb.append(binary_search(target)).append(" ");
        }
        return stb.toString();
    }
}

class MainA10816 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] cards = strToLongArr(br.readLine());
        int M = Integer.parseInt(br.readLine());
        long[] toCheck = strToLongArr(br.readLine());
        System.out.println(new 숫자카드2_10816(N, M, cards, toCheck).getAns());

    }
    static long[] strToLongArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToLong(Long::parseLong).toArray();
    }
}