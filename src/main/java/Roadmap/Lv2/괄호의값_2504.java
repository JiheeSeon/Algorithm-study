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
    Map<Character, Character> parenthesisPair;
    Map<Character, Integer> parenthesisValuePair;

    public 괄호의값_2504(String input) {
        this.input = input;
        stack = new Stack<>();

        parenthesisPair = new HashMap<>();
        parenthesisPair.put('(', ')');
        parenthesisPair.put('[', ']');
        parenthesisPair.put(')', '(');
        parenthesisPair.put(']', '[');

        parenthesisValuePair = new HashMap<>();
        parenthesisValuePair.put('(', 2);
        parenthesisValuePair.put('[', 3);
        parenthesisValuePair.put(')', 2);
        parenthesisValuePair.put(']', 3);
    }

    int solve() {
        return solve(0);
    }

    private int solve( int level) {
        int res = 0;
        int tmp;

        while(true) {
            System.out.println(stack);
            System.out.println("level = " + level + " ptr = " + (ptr + 1) + " res = " + res);

            if(level == 0 && ptr >= input.length()) {
                return stack.isEmpty() ? res : 0;
            }

            char c = input.charAt(++ptr);
            switch (c) {
                case '(', '[' -> {
                    if (input.charAt(ptr + 1) == parenthesisPair.get(c)){
                        if(level != 0) return parenthesisValuePair.get(c);

                        res += parenthesisValuePair.get(c); ptr++;
                    } else {
                        stack.push(c);
                        tmp = solve(level + 1);
                        System.out.println("open tmp = " + tmp);
                        if (tmp == 0) return 0;
                        else res += tmp;
                    }
                }

                case ')', ']' -> {
                    if (stack.isEmpty() || stack.pop() != parenthesisPair.get(c)){
                        System.out.println("close = " + ptr + " " + input.charAt(ptr) + " -> return to 0");
                        return 0;
                    }

                    tmp = res * parenthesisValuePair.get(c);
                    if(level != 0) return tmp;
                    else res += tmp;
                }
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