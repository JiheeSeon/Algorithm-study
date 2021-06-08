package Recursion;

import java.io.*;
import java.math.BigInteger;

/* int  : 21억   (21 * 10^8) -> 4 byte (2^32)
   long : 922 경 (9 * 10^18) -> 8 byte (2^64)
   Biginteger : int, long으로 표현 불가한 범위

   N이 최대 100까지이므로 long으로도 커버 불가(2^100 - 1)
   -> BigInteger (java.math) 사용해야 함.

   하노이탑 재귀호출
   1. 맨 밑바닥에 있는 가장 큰 원반을 가장 오른쪽으로 옮겨야함
       > 맨 밑에 있던 가장 큰 원반이 움직이려면 1번 봉을 매개하면 안됨
         B/c 가장 큰 원반이 이동해야하는데 그걸 막는 꼴이 되어버림
   2. 가장 오른쪽으로 가장 큰 원반을 옮긴다.
   3. 2번 봉에 쌓여있는 나머지 원반들을 3번으로 옮긴다.
      > 1번 봉을 매개해서 3번으로 옮겨야

  인사이트
  - 옮길 때 보조봉과 목표봉을 잡는 거
*/
class 하노이탑_1914 {
    int N;
    StringBuilder stb = new StringBuilder();

    public 하노이탑_1914(int N){
        this.N = N;
    }

    int hanoi(int N, int start, int via, int end){
        // 결국 원반은 하나 단위로 옮기게 됨.
        // 원반이 하나면 바로(경유 없이) 목표한 end봉으로 옮기기.
        if(N == 1){
            stb.append(start).append(" ").append(end).append("\n");
            return 1;
        }

        // sum은 hanoi 함수의 인자가 아니라 지역변수로 하단 콜스택 결과 collect하는 역할
        int sum = 0;
        // 1. 맨 하단에 있는 가장 큰 원반 위에 있는 N - 1개의 원반들을 중간 봉에 몰아넣기
        // (via 봉으로 N번째로 큰 원반 위에 있던 N-1개의 원반들을 모으는 것)
        sum += hanoi(N - 1, start, end, via);

        // 2. N번째로 큰 원반을 end 봉에 가장 하단부에 옮김
        sum += 1;
        stb.append(start).append(" ").append(end).append("\n");

        // via 봉에(중간에 거치려 원반들을 쌓아놓은 봉) 모아놓은 원반들을 end 봉으로 모으는 것
        sum += hanoi(N - 1, via, start, end);

        return sum;
    }

    String getAns(){
        if(N <= 20)
            return stb.insert(0, "\n").insert(0, hanoi(N, 1, 2, 3)).toString();
        else{
            BigInteger two = new BigInteger("2");
            BigInteger res = two.pow(N).subtract(BigInteger.ONE);
            return res.toString();
        }
    }
}
class MainA1914 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.print(new 하노이탑_1914(N).getAns());
    }
}