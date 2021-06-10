package Recursion;

import java.io.*;
import java.util.Stack;

class Solution{
    String s;
    private int nextPtr;
    private Stack<Character> stack;

    public Solution(String s){
        this.s = s;
        nextPtr = 0;
        stack = new Stack<Character>();
    }

    int getPairValue(int depth){
        int tmp;
        int sum = 0;
        char now;

        while(nextPtr < s.length()){
            now = s.charAt(nextPtr++);

            switch(now){
                case '(':
                case '[':
                    stack.push(now);
                    tmp = getPairValue(depth + 1);
                    if(tmp == 0) return 0;
                    else sum += tmp;
                    break;
                case ')':
                    if(stack.isEmpty()) return 0;
                    return (stack.pop() == '(') ? (sum == 0 ? 2 : 2 * sum) : 0;
                case ']':
                    if(stack.isEmpty()) return 0;
                    return (stack.pop() == '[') ? (sum == 0 ? 3 : 3 * sum) : 0;
            }
        }
        if(stack.size() != 0) return 0;
        return sum;
    }
    int getAns(){
        return (s.charAt(0) == ')' || s.charAt(0) == ']')
                ? 0 : getPairValue(0);
    }
}
class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(new Solution(br.readLine()).getAns());
    }
}