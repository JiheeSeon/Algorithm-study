package Implementation.Programmers.lv2;

import java.util.Stack;

/*
문제분석
prices : 초 단위로 기록한 주식가격이 담긴 배열
가격이 떨어지지 않은 기간이 몇 초인지 구할 것

-> 한번 떨어지기 전까지의 구간 길이를 구하는 게 핵심
-> 초 = 구간으로 접근할 것
*/
class 주식가격_42584_일반 {
    public static int[] solution(int[] prices) {
        int sec;
        int[] answer = new int[prices.length];

        for(int i = 0; i < prices.length - 1; i++){
            sec = 0;
            for(int j = i + 1; j < prices.length; j++){
                sec++;
                if(prices[j] < prices[i]) break;
            }
            answer[i] = sec;
        }
        return answer;
    }
}

class 주식가격_42584_스택{
    public static int[] solution(int[] prices) {
        int sec;
        int[] answer = new int[prices.length];

        Stack<Integer> stack;

        for (int i = 0; i < prices.length - 1; i++) {
            stack = new Stack<>();

            for (int j = 0; j < prices.length; j++) {
                if(prices[i] > prices[j]) stack.push(prices[j]);
                else{
                    stack.push(0);
                    break;
                }
            }
            answer[i] = stack.size();
        }
        return answer;
    }
}