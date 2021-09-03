package Roadmap.Lv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class 괄호의값_2504 {
    String input;
    int ptr = -1;
    Stack<Character> stack;
    Map<Character, Character> parenthesisMap;
    Map<Character, Integer> parenthesisValueMap;

    public 괄호의값_2504(String input) {
        this.input = input;

        stack = new Stack<>();
        parenthesisMap = new HashMap<>();
        parenthesisValueMap = new HashMap<>();

        parenthesisMap.put(')', '(');
        parenthesisMap.put(']', '[');
        parenthesisValueMap.put(')', 2);
        parenthesisValueMap.put(']', 3);
    }

    int solve() {
        int sum = 0;
        int tmp;
        while (ptr + 1 < input.length()) {
            tmp = getGroupValue(-1);
            if(tmp == 0) return 0; // 검증 작업

            sum += tmp;
        }
        return stack.isEmpty() ? sum : 0;
    }

    private int getGroupValue(int level) {
        int res = 0; // 사실상 사이에 쌓이는 값, 하위 호출로 벌어들인 값 저장
        int tmp;

        while (true) {
            if(ptr + 1 >= input.length()) return res;

            char c = input.charAt(++ptr);

            switch(c){
                case '(', '[' ->{
                    stack.push(c); // push open parenthesis to stack
                    tmp = getGroupValue(level + 1); // get value of the pair(at the level)

                    if(tmp == 0) return 0;
                    else res += tmp;
                }
                case ')', ']' ->{
                    if(stack.isEmpty() || stack.pop() != parenthesisMap.get(c)) return 0;

                    // 종료 조건
                    if(stack.size() == level){
                        // 사이에 아무 값도 없었으면 그냥 2 or 3을 반환
                        return res == 0 ? parenthesisValueMap.get(c) : res * parenthesisValueMap.get(c);
                    }
                }
                default -> { return 0; } // 틀렸습니다 -> 맞았습니다!!
            }
        }
    }
}

class MainA2504{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(new 괄호의값_2504(input).solve());
    }
}