package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class BST표현3_배열 {
    static char[] tree;
    static int nodeN;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int level = Integer.parseInt(br.readLine());
        nodeN = (int) Math.pow(2, level) - 1;
        tree = ".".concat(br.readLine()).replace(" ", "").toCharArray(); //

        StringBuilder stb = new StringBuilder();
        stb.append(preorder(1, new StringBuilder())).append("\n");
        stb.append(inorder(1, new StringBuilder())).append("\n");
        stb.append(postorder(1, new StringBuilder())).append("\n");

        System.out.println(stb);
    }
    static StringBuilder preorder(int idx, StringBuilder stb) {
        if (idx < 1 || idx > nodeN || tree[idx] == '.') return stb;

        stb.append(tree[idx]);
        preorder(idx * 2, stb);
        preorder(idx * 2 + 1, stb);

        return stb;
    }

    static StringBuilder inorder(int idx, StringBuilder stb) {
        if (idx < 1 || idx > nodeN || tree[idx] == '.') return stb;

        inorder(idx * 2, stb);
        stb.append(tree[idx]);
        inorder(idx * 2 + 1, stb);

        return stb;
    }

    static StringBuilder postorder(int idx, StringBuilder stb) {
        if (idx < 1 || idx > nodeN || tree[idx] == '.') return stb;

        postorder(idx * 2, stb);
        postorder(idx * 2 + 1, stb);
        stb.append(tree[idx]);

        return stb;
    }
}

// Example Test case
// 4
//A B C D . E F . . . . . . . G