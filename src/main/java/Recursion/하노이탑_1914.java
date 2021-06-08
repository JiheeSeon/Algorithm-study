package Recursion;

import java.io.*;
import java.math.BigInteger;

/* int  : 21억   (21 * 10^8)
   long : 922 경 (9 * 10^18)
   Biginteger : int, long으로 표현 불가한 범위
*/
class Solution{
    int N;
    StringBuilder stb = new StringBuilder();

    public Solution(int N){
        this.N = N;
    }

    int hanoi(int N, int start, int via, int end){
        int sum = 0;
        if(N == 1){
            stb.append(start).append(" ").append(end).append("\n");
            return 1;
        }

        sum += hanoi(N - 1, start, end, via);
        sum += 1;
        stb.append(start).append(" ").append(end).append("\n");
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
class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.print(new Solution(N).getAns());
    }
}