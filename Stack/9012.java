import java.io.*;
import java.util.Stack;

class Main9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int idx = 0;
        int T = Integer.parseInt(br.readLine());
        while (idx < T) {
            String line = br.readLine();
            if ((stackHandler(line) == 1)) {
                stb.append("NO\n");
            } else {
                stb.append("YES\n");
            }
            idx++;
        }
        stb.setLength(stb.length() - 1);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static int stackHandler(String line) {
        Stack<Character> stack = new Stack<Character>();
        int idx = 0;
        char token;
        while (idx < line.length()) {
            token = line.charAt(idx++);
            if (token == '(') {
                stack.push('(');
            } else if (token == ')' && !stack.isEmpty()) {
                stack.pop();
            } else {
                return 1;
            }
        }
        return stack.isEmpty() ? 0 : 1;
    }
}