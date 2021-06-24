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
        int low = 0; int high = N - 1;
        int mid;
        int l = -1; int h = -1;

        // 가장 최대로 target이 나온 것 - 가장 최소로 target이 나온 것

        while(low <= high){
            mid = (low + high) / 2;
            if(cards[mid] == target){
                h = mid;
                low = mid + 1; // 무조건 오른쪽으로만 탐색하게
            } else if(cards[mid] < target){
                low = mid + 1;
            } else{
                high = mid - 1;
            }
        }

        low = 0; high = N - 1;
        while(low <= high){
            mid = (low + high) / 2;
            if(cards[mid] == target){
                l = mid;
                high = mid - 1; // 무조건 왼쪽으로 탐색하게
            } else if(cards[mid] < target){
                low = mid + 1;
            } else{
                high = mid - 1;
            }
        }
        return h == -1 && l == -1 ? 0 : h - l + 1;
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
/*
10
3 3 2 10 10 10 -10 -10 7 3
8
10 9 -5 2 3 4 5 -10

3 0 0 1 3 0 0 2


10
1 1 1 2 2 2 2 2 2 3
4
1 2 3 4

output : 3 6 1 0
*/