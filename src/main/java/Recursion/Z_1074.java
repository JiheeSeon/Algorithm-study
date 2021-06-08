package Recursion;

import java.io.*;
import java.util.regex.Pattern;

class Z_1074 {
    int N, Y, X;

    public Z_1074(int[] info){
        N = info[0]; Y = info[1]; X = info[2];
    }

    int find(int n, int base, int sY, int sX, int eY, int eX){
        if(n == 1){
            if(Y == sY && X == sX) return base;
            else if(Y == sY && X == eX) return base + 1;
            else if(Y == eY && X == sX) return base + 2;
            else return base + 3;
        }
        boolean yIsInFirstHalf = (sY <= Y && Y <= (sY + eY) / 2);
        boolean xIsInFirstHalf = (sX <= X && X <= (sX + eX) / 2);

        if(yIsInFirstHalf && xIsInFirstHalf) return find(n - 1, base, sY, sX, (sY + eY) / 2, (sX + eX) / 2);
        else if(yIsInFirstHalf && !xIsInFirstHalf) return find(n - 1, base + (int)Math.pow(2, 2*(n - 1)), sY, (sX + eX) / 2 + 1, (sY + eY) / 2, eX);
        else if(!yIsInFirstHalf && xIsInFirstHalf) return find(n - 1, base + (int)Math.pow(2,  2*(n - 1)) * 2, (sY + eY) / 2 + 1, sX, eY, (sX + eX) / 2);
        else return find(n - 1, base + (int)Math.pow(2,  2*(n - 1)) * 3, (sY + eY) / 2 + 1, (sX + eX) / 2 + 1, eY, eX);
    }

    int getAns(){
        return find(N, 0, 0, 0, (int)Math.pow(2, N) - 1, (int)Math.pow(2, N) - 1);
    }
}
class MainA1074 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        System.out.println(new Z_1074(info).getAns());
    }
}