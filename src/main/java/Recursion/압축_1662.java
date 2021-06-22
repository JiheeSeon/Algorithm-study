package Recursion;

import java.io.*;

class Solution{
    int getAns(String s){
        int nowIdx = -1;
        int openIdx = -1;
        int closeIdx = -1;

        int openParenthesisN = 0;
        int closeParenthesisN = 0;
        boolean isMatched = true;

        char nowC;
        int ret = 0;

        while (++nowIdx < s.length()) {
            nowC = s.charAt(nowIdx);

            if (nowC == '('){
                openParenthesisN++;

                if(isMatched){
                    openIdx = nowIdx;
                    ret += (openIdx - closeIdx - 2);
                    isMatched = false;
                }
            } else if (nowC == ')'){
                closeParenthesisN++;

                closeIdx = nowIdx;
                if(openParenthesisN == closeParenthesisN){
                    ret += ((s.charAt(openIdx - 1) - '0') * getAns(s.substring(openIdx + 1, closeIdx)));
                    isMatched = true;
                }
            }
        }
        ret += (s.length() - closeIdx - 1);

        if(openIdx == -1 && closeIdx == -1) return s.length();
        return ret;
    }
}

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(new Solution().getAns(br.readLine()));
    }
}
/*
3(3(3(2(2)2(2))))
108

6(22)122
15

33(562(71(9)))
19
*/