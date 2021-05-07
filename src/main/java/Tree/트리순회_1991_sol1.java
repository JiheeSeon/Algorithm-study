package Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

class 트리순회_1991_sol1 {
    static HashMap<Character, char[]> graph;
    static StringBuilder ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new HashMap<>();
        char[] toPut;
        char[] tmp;

        for (int i = 0; i < N; i++) {
            tmp = strToIntArray(br.readLine());

            toPut = new char[2];
            toPut[0] = tmp[1];
            toPut[1] = tmp[2];

            graph.put(tmp[0], toPut);
        }

        StringBuilder stb = new StringBuilder();

        ans = new StringBuilder();
        preorder('A');
        stb.append(ans).append("\n");

        ans = new StringBuilder();
        inorder('A');
        stb.append(ans).append("\n");

        ans = new StringBuilder();
        postorder('A');
        stb.append(ans);

        System.out.println(stb);
    }
    static char[] strToIntArray(String s){
        return s.replace(" ", "").toCharArray();
    }

    static void preorder(char c) {
        if(c == '.') return;

        ans.append(c);

        char[] children = graph.get(c);
        preorder(children[0]);
        preorder(children[1]);
    }

    static void inorder(char c) {
        if(c == '.') return;

        char[] children = graph.get(c);

        inorder(children[0]);
        ans.append(c);
        inorder(children[1]);
    }

    static void postorder(char c) {
        if(c == '.') return;

        char[] children = graph.get(c);
        postorder(children[0]);
        postorder(children[1]);
        ans.append(c);
    }
}