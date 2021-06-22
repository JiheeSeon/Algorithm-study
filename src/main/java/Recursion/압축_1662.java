package Recursion;

import java.io.*;

class Solution{
    int getAns(String s){
        int openIdx = -1;
        int closeIdx = -1;
        int nowIdx = -1;
        boolean unmatched = false;
        boolean firstFlag = true;

        int ret = 0;

        while(++nowIdx < s.length()){
            if(!unmatched) openIdx = closeIdx;

            if(s.charAt(nowIdx) == '('){
                openIdx = nowIdx;
                unmatched = true;

                if(firstFlag) {
                    ret += openIdx - 1;
                    firstFlag = false;
                } else{
                    ret += openIdx - closeIdx - 2;
                }
            } else if(s.charAt(nowIdx) == ')'){
                closeIdx = nowIdx;
                ret += (s.charAt(openIdx - 1) - '0') * getAns(s.substring(openIdx + 1, closeIdx));
                unmatched = false;
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
*/