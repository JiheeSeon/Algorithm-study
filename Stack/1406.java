import java.io.*;
import java.util.Stack;

class Main1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int length = input.length();
        int T = Integer.parseInt(br.readLine());
        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();
        StringBuilder stb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < length; i++)
            leftStack.push(input.charAt(i));

        for (int i = 0; i < T; i++) {
            String[] command = br.readLine().split(" ");
            switch (command[0]) {
                case "L" -> {
                    if (!leftStack.isEmpty()) rightStack.push(leftStack.pop());
                }
                case "D" -> {
                    if (!rightStack.isEmpty()) leftStack.push(rightStack.pop());
                }
                case "B" -> {
                    if (!leftStack.isEmpty()) leftStack.pop();
                }
                case "P" -> leftStack.push(command[1].charAt(0));
            }
        }

        while(!leftStack.isEmpty())
            rightStack.push(leftStack.pop());

        while(!rightStack.isEmpty())
            stb.append(rightStack.pop());

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}