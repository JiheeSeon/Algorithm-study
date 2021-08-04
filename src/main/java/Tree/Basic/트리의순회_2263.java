package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 트리의순회_2263 {
    int V;
    int[] inorder, postorder;
    boolean[] visited;
    int visitPtrOfPostOrder;
    int[] inorderIdxMapper;

    BinaryTree binaryTree;

    public 트리의순회_2263(int v, int[] inorder, int[] postorder) {
        V = v;
        this.inorder = inorder;
        this.postorder = postorder;
        visited = new boolean[V + 1];

        inorderIdxMapper = new int[V + 1];
        for (int idx = 1; idx <= V; idx++) {
            inorderIdxMapper[inorder[idx]] = idx;
        }

        binaryTree = new BinaryTree();
        visitPtrOfPostOrder = V;
    }

    static class BinaryTreeNode{
        final static BinaryTreeNode DEFAULT_NODE = new BinaryTreeNode(-1);
        int label;
        BinaryTreeNode left = DEFAULT_NODE, right = DEFAULT_NODE;

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
        BinaryTreeNode rootNode = new BinaryTreeNode(-2);

        public BinaryTree() {
            rootNode.right = null;
        }

        String preorder() {
            return preorder(rootNode.left, new StringBuilder()).toString();
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
        connectNode(binaryTree.rootNode, true);
        return binaryTree.preorder();
    }

    void connectNode(BinaryTreeNode pNode, boolean isLeftChild) {
        BinaryTreeNode cNode = new BinaryTreeNode(postorder[visitPtrOfPostOrder]);

        if(isLeftChild) pNode.left = cNode;
        else pNode.right = cNode;

        int currentIdx = inorderIdxMapper[postorder[visitPtrOfPostOrder]];
        visited[currentIdx] = true;

        if (currentIdx + 1 >= visited.length || visited[currentIdx + 1] || visitPtrOfPostOrder == 1) {
            cNode.right = null;
        } else{
            visitPtrOfPostOrder--;
            connectNode(cNode, false);
        }

        if(currentIdx < 1 || visited[currentIdx - 1] || visitPtrOfPostOrder == 1){
            cNode.left = null;
        } else{
            visitPtrOfPostOrder--;
            connectNode(cNode, true);
        }
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

TC 2
15
10 7 4 8 2 1 5 3 13 11 15 14 9 12 6
10 7 8 4 2 5 13 15 14 11 12 9 6 3 1

1 2 4 7 10 8 3 5 6 9 11 13 14 15 12
*/