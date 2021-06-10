package Recursion;

import java.io.*;
import java.util.Stack;

class 괄호의값_2504 {
    String s;
    private int nextPtr;
    private Stack<Character> stack;

    public 괄호의값_2504(String s){
        this.s = s;
        nextPtr = 0;
        stack = new Stack<Character>();
    }

    /*
    함수의 소멸시기
    > 현재 stack size가 기존에 받은 depth보다 작아졌을 때
       == 닫힌 괄호가 나왔을 때

    함수의 반환값
    - 미래에서 콜스택으로 종합해 계산한 값 > sum

    함수의 역할
    - nextPtr 값을 올리기
    - 열린 괄호가 나오면 바로 재귀호출해서 sum값에 반영
      -> 자기 하위에서 모아놓을 수 있는 depth 애들은 다 더해놓음
    - 닫힌 괄호가 나오면 자기 아래 모아놓은 것들에 * 2 or 3 해서 처리

    한마디로 열린 괄호에서 호출이 시작된 특정 depth 값은 닫힌 괄호까지 마무리지어줌
    */
    int getParenthesisValue(int depth){
        int tmp;
        int sum = 0; // 값을 누적하는 곳
        char now;

        while(nextPtr < s.length()){ // 사실상 depth == 0인 경우를 위함
            now = s.charAt(nextPtr++);

            switch(now){
                case '(':
                case '[':
                    stack.push(now);
                    tmp = getParenthesisValue(depth + 1);
                    if(tmp == 0) return 0;
                    else sum += tmp; // (5) XY = x + Y
                    break;
                case ')':
                    if(stack.isEmpty()) return 0; // 아무것도 없을 때 pop하면 exception 터지므로 방지
                    // sum 값이 0이라는 건 위에서 열린괄호로 호출했는데, 다음 열린괄호 없이 바로 닫힌 괄호가 나왔다는 것
                    // -> 즉 자기가 제일 안쪽 괄호라는 것 -> 재귀의 마지막, 2 반환하도록 (1)
                    // 자기가 제일 안쪽 괄호가 아니면 앞에서 모아놓은 애들을 감쌌다는 의미 (3)
                    return (stack.pop() == '(') ? (sum == 0 ? 2 : 2 * sum) : 0; // 괄호 안 맞으면 0
                case ']':
                    if(stack.isEmpty()) return 0;
                    return (stack.pop() == '[') ? (sum == 0 ? 3 : 3 * sum) : 0;
            }
        }

        // 매칭되지 않은 열린 괄호가 남은 경우
        if(stack.size() != 0) return 0;

        // depth == 0인 경우 다 sum에 저장해서 모아 반환
        // depth != 0이면 닫힌 괄호일 때 다 return 해버림
        return sum;
    }
    int getAns(){
        return (s.charAt(0) == ')' || s.charAt(0) == ']')
                ? 0 : getParenthesisValue(0);
    }
}
class MainA2504 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(new 괄호의값_2504(br.readLine()).getAns());
    }
}