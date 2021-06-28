package ParametricSearch;

import java.io.*;
import java.util.regex.Pattern;
import java.util.Arrays;

/*
Bound Algorithm
lowerBound : 찾고자 하는 값 이상이 처음 나타나는 위치 리턴
upperBound : target 값 보다 큰 값이 처음 나오는 위치 리턴

[0 - N-1]이 아니라 [0 - N] 구간으로
while loop도 low < high를 조건으로
*/
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

    // 찾고자 하는 값 이상이 처음 나타나는 위치
    int lowerBound(long target){
//        System.out.println("\nstart lower bound call");

        int low = 0; int high = N;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;
//            System.out.println("low = " + low + " high = " + high + " mid = " + mid);

            if(cards[mid] < target) low = mid + 1;
            else high = mid;
        }

//        System.out.println("end lower bound call with low = " + low + " high = " + high + "\n");
        return high;
    }

    // 특정 target값보다 큰 값이 처음 나오는 위치를 리턴
    int upperBound(long target) {
//        System.out.println("start upper bound call\n");

        int low = 0; int high = N;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

//            System.out.println("low = " + low + " high = " + high + " mid = " + mid);

            if(cards[mid] <= target) low = mid + 1;
            else high = mid;
        }

//        System.out.println("end upper bound call with low = " + low + " high = " + high);
        return high;
    }

    int binary_search_bound_algorithm(long target) {
        int lb = lowerBound(target);
        int ub = upperBound(target);

//        System.out.println("lb = " + lb + " ub = " + ub);
        return ub - lb;
    }

    int binary_search_iteration(long target){
        int low = 0; int high = N - 1;
        int mid;
        int l = -1; int h = -1;

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

    int binary_search_recursion_timeout(long target, int low, int high) {
        int mid;

        while(low <= high){
            mid = (low + high) / 2;
            if(cards[mid] == target){
                return 1 + binary_search_recursion_timeout(target, low, mid - 1) + binary_search_recursion_timeout(target, mid + 1, high);
            } else if(cards[mid] < target){
                low = mid + 1;
            } else{
                high = mid - 1;
            }
        }

        return 0;
    }

    String getAns(){
        Arrays.sort(cards);

        StringBuilder stb = new StringBuilder();
        for(long target: toCheck){
            stb.append(binary_search_bound_algorithm(target)).append(" ");
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

output : 3 0 0 1 3 0 0 2


10
1 1 1 2 2 2 2 2 2 3
4
1 2 3 4

output : 3 6 1 0
*/