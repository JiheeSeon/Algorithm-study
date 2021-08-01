package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class 트리의순회_2263 {
    int V;
    int[] inorder, postorder;
    int[] inorderIdxMapper;

    int rightCheck;
    BinaryTree binaryTree;

    public 트리의순회_2263(int v, int[] inorder, int[] postorder) {
        V = v;
        this.inorder = inorder;
        this.postorder = postorder;

        inorderIdxMapper = new int[V + 1];
        for (int idx = 1; idx <= V; idx++) {
            inorderIdxMapper[inorder[idx]] = idx;
        }

        binaryTree = new BinaryTree(postorder[V]);
        rightCheck = V;
    }

    private class BinaryTreeNode{
        int label;
        BinaryTreeNode left, right;

        public BinaryTreeNode(int label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return "(" +
                    "label=" + label +
                    ", left=" + left +
                    ", right=" + right +
                    ')';
        }
    }

    private class BinaryTree{
        BinaryTreeNode rootNode;

        public BinaryTree(int label) {
            rootNode = new BinaryTreeNode(label);
        }

        String preorder() {
            return preorder(rootNode, new StringBuilder()).toString();
        }

        StringBuilder preorder(BinaryTreeNode pNode, StringBuilder stb) {
            stb.append(pNode.label).append(" ");

            if(pNode.left != null){
                preorder(pNode.left, stb);
                if(pNode.right != null) preorder(pNode.right, stb);
            }

            return stb;
        }
    }

    String solve() {
        if(inorderIdxMapper[binaryTree.rootNode.label] != postorder[V])
            connectNode(binaryTree.rootNode, inorderIdxMapper[binaryTree.rootNode.label] + 1, V);
        connectNode(binaryTree.rootNode, 1, inorderIdxMapper[binaryTree.rootNode.label] - 1);

        return binaryTree.preorder();
    }

    void connectNode(BinaryTreeNode pNode, int leftIdx, int rightIdx) {
        if(leftIdx > rightIdx) return;

        int inorderLocationOfpNode = inorderIdxMapper[pNode.label];

        // right first
        if(inorderLocationOfpNode + 1 >= rightCheck){
            pNode.left = new BinaryTreeNode(postorder[--rightCheck]);
            return;
        }

        pNode.right = new BinaryTreeNode(postorder[--rightCheck]);
        System.out.println(pNode);

        if(inorderLocationOfpNode + 1 != rightIdx){
            connectNode(pNode.right, inorderLocationOfpNode + 1, rightIdx);
        }

        System.out.println(pNode);

        // left
        pNode.left = new BinaryTreeNode(postorder[--rightCheck]);
        if(inorderLocationOfpNode - 1 == leftIdx)
            connectNode(pNode.left, leftIdx, inorderLocationOfpNode - 1);

        System.out.println(pNode);
    }
}

class MainA2263{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int[] inorder = InputProcessor.strToIntArr("0 " + br.readLine());
        int[] postorder = InputProcessor.strToIntArr("0 " + br.readLine());

        System.out.println(new 트리의순회_2263(V, inorder, postorder).solve());
    }
}
/*
TC 1
19
13 8 14 4 15 9 16 2 10 5 1 6 3 17 11 18 7 19 12
13 14 8 15 16 9 4 10 5 2 6 17 18 11 19 12 7 3 1

1 2 4 8 13 14 9 15 16 5 10 3 6 7 11 18 12 19
*/