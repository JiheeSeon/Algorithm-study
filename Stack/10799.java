import java.io.*;
import java.util.Stack;

class Main10799{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Integer> parenthesisStack = new Stack<>();
        String s = br.readLine();
        char token;
        int idx = 0;
        int length = s.length();
        int result = 0;

        while(idx < length){
            token = s.charAt(idx);
            if(token == '(')
                parenthesisStack.push(idx);
            else if(token == ')'
                    && parenthesisStack.pop() == idx - 1) //레이저
                result += parenthesisStack.size();
            else
                result += 1;

            idx++;
        }

        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
}