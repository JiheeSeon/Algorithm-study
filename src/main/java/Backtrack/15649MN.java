package Backtrack;

import java.io.*;
import java.util.Stack;
import java.util.regex.Pattern;

class Main15649{
    static StringBuilder stb = new StringBuilder();
    static StringBuilder stbRec = new StringBuilder();
    static int maxN, count;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        maxN = input[0]; count = input[1];

        recursive(1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void recursive(int digit){
        if (digit > count){
            stb.append(stbRec.toString()).append("\n");
            return;
        }

        for(int i = 1; i <= maxN; i++){
            if (!stack.isEmpty() && stack.contains(i)) continue;

            stbRec.append(i).append(" ");
            stack.push(i);
            recursive(digit + 1);
            stack.pop();

            stbRec.setLength((digit - 1) * 2);
        }
    }
}