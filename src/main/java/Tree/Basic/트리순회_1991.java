package Tree.Basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class BinaryTreeNode {
    char left, right;

    public BinaryTreeNode(char left, char right) {
        this.left = left;
        this.right = right;
    }
}

class 트리순회_1991 {
    int N;
    BinaryTreeNode[] binaryTree;

    public 트리순회_1991(int n, BinaryTreeNode[] binaryTree) {
        N = n;
        this.binaryTree = binaryTree;
    }

    String solve() {
        StringBuilder retStb = new StringBuilder();

        char rootNode = 'A';

        preorder(rootNode, retStb);
        retStb.append("\n");

        inorder(rootNode, retStb);
        retStb.append("\n");

        postorder(rootNode, retStb);
        retStb.append("\n");

        return retStb.toString();
    }

    void preorder(char subTreeRootNode, StringBuilder stb) {
        if(subTreeRootNode == '.') return;

        stb.append(subTreeRootNode); // visit current node
        preorder(binaryTree[subTreeRootNode - 'A'].left, stb); // call left sub-tree
        preorder(binaryTree[subTreeRootNode - 'A'].right, stb); // call right sub-tree
    }

    void inorder(char subTreeRootNode, StringBuilder stb) {
        if(subTreeRootNode == '.') return;

        inorder(binaryTree[subTreeRootNode - 'A'].left, stb); // call left sub-tree
        stb.append(subTreeRootNode); // visit current node
        inorder(binaryTree[subTreeRootNode - 'A'].right, stb); // call right sub-tree
    }

    void postorder(char subTreeRootNode, StringBuilder stb) {
        if(subTreeRootNode == '.') return;

        postorder(binaryTree[subTreeRootNode - 'A'].left, stb); // call left sub-tree
        postorder(binaryTree[subTreeRootNode - 'A'].right, stb); // call right sub-tree
        stb.append(subTreeRootNode); // visit current node
    }
}

class MainA1991{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        BinaryTreeNode[] binaryTree = new BinaryTreeNode[N];
        char[] tmp;
        for (int i = 0; i < N; i++) {
            tmp = br.readLine().replace(" ", "").toCharArray();
            binaryTree[tmp[0] - 'A'] = new BinaryTreeNode(tmp[1], tmp[2]);
        }
        System.out.print(new 트리순회_1991(N, binaryTree).solve());
    }
}
