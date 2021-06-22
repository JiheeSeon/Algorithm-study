package Recursion;

import java.io.*;

/*
생각하지 못한 TC
- 병렬적으로 K(Q)L(Q)P(Q) 처럼 이어져있는 경우

3(3(3(2(2)2(2))))
108

6(22)122
15

33(562(71(9)))
19

재귀 함수의 ROLE

1. 열린 괄호가 나오면
   (매칭된 상태로 세팅되었을 때 - 첫 열린 괄호인 경우) 해당 index 저장
   그 전전까지 나왔던 괄호를 미포함한 문자열에 대한 개수 더함
2. 닫힌 괄호가 나오면
   열린괄호부터 해당 닫힌 괄호까지에 대해 괄호만 제거하고 하위 콜스택 호출
   -> 하위 콜스택에서도 이와 같은 역할을 수행

EndPoint
열린 괄호, 닫힌 괄호 모두 안 나오는 경우 -> 해당 스트링의 길이 반환


문제 풀이 특징 요약

- 스택 안 쓰고 열린괄호와 닫힌괄호의 개수 비교해서 구현
- 괄호가 매칭되면 바로 return 하지 않고 동일 계층에 대해 ret에 쌓아나가면서 책임을 다함.
> 이렇게 구현한 이유는 코드의 불필요한 중복을 막기 위함.
*/

class 압축_1662 {
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

class MainA1662 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(new 압축_1662().getAns(br.readLine()));
    }
}