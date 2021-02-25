package Stack;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main10828 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        StackWithArrayList stackWithArrayList = new StackWithArrayList();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int idx = 0;

        while (idx++ < T) {
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()){
                case "push"-> stackWithArrayList.push(Integer.parseInt(st.nextToken()));
                case "pop"-> stb.append(stackWithArrayList.pop()).append("\n");
                case "top"-> stb.append(stackWithArrayList.top()).append("\n");
                case "size"-> stb.append(stackWithArrayList.size()).append("\n");
                case "empty" -> stb.append(stackWithArrayList.empty()).append("\n");
            }
        }
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static class StackWithArrayList {
        private ArrayList<Integer> stack;

        public StackWithArrayList() {
            stack = new ArrayList<Integer>();
        }

        void push(int input) {
            stack.add(input);
        }

        int pop() {
            return empty()==1?-1:stack.remove(stack.size() - 1);
        }

        int empty() {
            return stack.size() == 0?1:0;
        }

        int top() {
            return empty()==1?-1:stack.get(stack.size() - 1);
        }

        int size() {
            return stack.size();
        }
    }
}