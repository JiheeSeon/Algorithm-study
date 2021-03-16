package Implementation.Programmers.lv1;

import java.util.Stack;
class Main17682 {
    public static void main(String[] args) {
        System.out.println(solution("1S2D*3T"));
        System.out.println();
        System.out.println(solution("1T2D3D#"));
        System.out.println();
        System.out.println(solution("1D2S#10S"));
    }

    public static int solution(String dartResult) {
        Stack<Integer> stack = new Stack<>();

        int top, beforeTop;
        char beforeChar = dartResult.charAt(0);
        char currentChar;

        for(int i = 0; i < dartResult.length(); i++){
            currentChar = dartResult.charAt(i);

            if('0' <= currentChar && currentChar <= '9'){
                if(i >= 1 && '0' <= beforeChar && beforeChar <= '9') {
                    int toPush = Integer.parseInt((stack.pop() + Character.toString(currentChar)));
                    stack.push(toPush);
                }
                else
                    stack.push(dartResult.charAt(i) - '0');
            }
            else if(currentChar== 'S')
                stack.push(calculateSeed(stack.pop(), 1));
            else if(currentChar== 'D')
                stack.push(calculateSeed(stack.pop(), 2));
            else if(currentChar== 'T')
                stack.push(calculateSeed(stack.pop(), 3));
            else if(currentChar == '#')
                stack.push(stack.pop() * -1);
            else{
                if (stack.size() == 1)
                    stack.push(2 * stack.pop());
                else if(stack.size() >= 2){
                    top = stack.pop();
                    beforeTop = stack.pop();
                    stack.push(beforeTop * 2);
                    stack.push(top * 2);
                }
            }
            beforeChar = currentChar;
        }

        int result = 0;
        while(!stack.isEmpty()){
            result += stack.pop();
        }
        return result;
    }
    static int calculateSeed(int n, int digit){
        if(digit == 1) return n;
        return (int)Math.pow(n, digit);
    }
}