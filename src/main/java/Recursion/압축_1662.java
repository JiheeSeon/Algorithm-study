package Recursion;

import java.io.*;

class Solution{
//    int solve(String s){
//        System.out.println(s);
//        if(s.length() == 1) return 1;
//
//        int nowIdx = -1;
//        int openIdx = -1;
//        int closeIdx = 0;
//        int beforeCloseIdx = 0;
//
//        int openParenthesisN = 0;
//        int closeParenthesisN = 0;
//        boolean firstOpenFlag = true;
//
//        char nowC;
//        int ret = 0;
//
//        while (++nowIdx < s.length()) {
//            nowC = s.charAt(nowIdx);
//
//            if (nowC == '('){
//                openParenthesisN++;
//
//                if(firstOpenFlag){
//                    firstOpenFlag = false;
//                    openIdx = nowIdx;
//                    ret += (openIdx - 1);
//                }
//            } else if (nowC == ')'){
//                closeParenthesisN++;
//                closeIdx = nowIdx;
//
//                if(openParenthesisN == closeParenthesisN){
//                    ret += ((s.charAt(openIdx - 1) - '0') * solve(s.substring(openIdx + 1, closeIdx)));
//                }
//            }
//        }
//        ret += (s.length() - closeIdx - 1);
//
//        return ret;
//    }

    int getAns(String s){
        int nowIdx = -1;
        int openIdx = -1;
        int closeIdx = 0;
        int beforeCloseIdx = 0;

        int openParenthesisN = 0;
        int closeParenthesisN = 0;
        boolean firstOpenFlag = true;

        char nowC;
        int ret = 0;

        while (++nowIdx < s.length()) {
            nowC = s.charAt(nowIdx);

            if (nowC == '('){
                openParenthesisN++;
                openIdx = nowIdx;

                if(firstOpenFlag){
                    firstOpenFlag = false;
                    ret += (openIdx - 1);
                }
            } else if (nowC == ')'){
                closeParenthesisN++;

                if(openParenthesisN == closeParenthesisN){
                    ret += ((s.charAt(openIdx - closeIdx - 1) - '0') * getAns(s.substring(openIdx + 1, closeIdx)));
                }
                closeIdx = nowIdx;
            }
        }

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
*/