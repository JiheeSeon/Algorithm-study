package ParametricSearch;

import java.io.*;
import java.util.regex.Pattern;

/*
오답률이 높았던 이유
1. prevWinRate가 100퍼센트뿐만 아니라 99 퍼센트인 경우에도 -1을 return 했어야 함.

2. high의 초기값 설정
   최대 mid 값은 high라는 점을 염두, newWinRate 계산 과정에서 overflow 나지 않게

3. return 할 값이 low
   3가지 버전의 답이 맞음
   1. 값이 바뀌었을 때만 mid를 ans에 저장해서 Return 하는 경우
   2. low를 Return 하는 경우
   3. high + 1을 Return 하는 경우

   틀린 답 처리 : mid 를 return 하는 경우

   * 상황 및 이유 분석 *
   2와 3은 사실상 동일한 효과
   b/c 현재 While 절은
   while(true){
        if(low == high + 1) break;
        ...
   }
   와 같음.

   1은 이전 루프에서 승률이 달라질 때만 다시 갱신해서 저장하는 방식
   1. 이전 루프에서 승률이 안 바뀌고 루프가 종료되면
      루프 종료시점의 mid 는 현재 low - 1 값
      => low == high + 1 == mid + 1
   2. 이전 루프에서 승률이 바뀌고 루프가 종료되면
      루프 종료시점의 mid 는 high + 1 값
      => low == high + 1 == mid

   따라서 mid 값은 루프종료 이전 상황에 따라 정확한 값을 가리키지 않는다.
   오히려 low 값에서 가능한 최소값을 가리키게 됨.
*/

class 게임_1072 {
    long gameN, wonN;
    int prevWinRate;

    public 게임_1072(long gameN, long wonN){
        this.gameN = gameN;
        this.wonN = wonN;
        prevWinRate = (int)(100 * wonN / gameN);
    }

    long getAns(){
        return (prevWinRate == 100 || prevWinRate == 99) ? -1 : parametricSearch();
    }

    long parametricSearch(){
        long low = 1;
        long high = Long.MAX_VALUE / 100 - wonN;
        long mid;
        long newWinRate;

        while(low <= high){
            mid = (low + high) / 2;
            newWinRate = (int)(100 * (wonN + mid) / (gameN + mid));

            if(newWinRate <= prevWinRate){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}

class MainA1072{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();
        long gameN = tmp[0]; long wonN = tmp[1];
        System.out.println(new 게임_1072(gameN, wonN).getAns());
    }
}