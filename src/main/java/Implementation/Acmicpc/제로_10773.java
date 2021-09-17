package Implementation.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class 제로_10773 {
    Stack<Integer> stack = new Stack<>();

    void pushOrPop(int n) {
        if(n == 0){
            if(!stack.isEmpty()) stack.pop();
        } else{
            stack.push(n);
        }
    }

    int getSum() {
        return stack.stream().mapToInt(x -> x).sum();
    }
}

class MainA10773{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        제로_10773 solution = new 제로_10773();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            solution.pushOrPop(Integer.parseInt(br.readLine()));
        }
        System.out.println(solution.getSum());
    }
}