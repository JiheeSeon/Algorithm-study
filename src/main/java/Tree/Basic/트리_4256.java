package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 트리_4256 {
    int V;
    int[] preorder, inorder;
    int[] inorderIndices;
    BinaryTree binaryTree;
    int preorderIdx;
    boolean[] visited;

    public 트리_4256(int v, int[] preorder, int[] inorder) {
        V = v;
        this.preorder = preorder;
        this.inorder = inorder;

        inorderIndices = new int[V + 1];
        for (int i = 1; i <= V; i++) inorderIndices[inorder[i]] = i;

        binaryTree = new BinaryTree();
        preorderIdx = 1;
        visited = new boolean[V + 1];
    }

    private class BinaryTreeNode {
        int label;
        BinaryTreeNode left = null, right = null;

        public BinaryTreeNode(int label) {
            this.label = label;
        }
    }

    private class BinaryTree{
        BinaryTreeNode rootNode = new BinaryTreeNode(-1);

        private void postorder(BinaryTreeNode node, StringBuilder stb) {
            if(node.left != null) postorder(node.left, stb);
            if(node.right != null) postorder(node.right, stb);

            stb.append(node.label).append(" ");
        }

        String postorder(){
            StringBuilder stb = new StringBuilder();
            postorder(rootNode.left, stb);
            return stb.toString();
        }
    }

    String solve() {
        connectToParentNode(binaryTree.rootNode, true);
        return binaryTree.postorder();
    }

    void connectToParentNode(BinaryTreeNode parent, boolean isLeftChild) {
        if(preorderIdx >= V + 1) return;

        BinaryTreeNode current = new BinaryTreeNode(preorder[preorderIdx]);

        if(isLeftChild) parent.left = current;
        else parent.right = current;

        int inorderLocation = inorderIndices[preorder[preorderIdx]];
        visited[inorderLocation] = true;

        if (inorderLocation >= 2 && !visited[inorderLocation - 1]) {
            preorderIdx++;
            connectToParentNode(current, true);
        }
        if (inorderLocation + 1 < visited.length && !visited[inorderLocation + 1]) {
            preorderIdx++;
            connectToParentNode(current, false);
        }
    }
}


class MainA4256{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int V;
        int[] preorder, inorder;
        StringBuilder stb = new StringBuilder();

        while (--T >= 0) {
            V = Integer.parseInt(br.readLine());
            preorder = InputProcessor.strToIntArr("0 " + br.readLine());
            inorder = InputProcessor.strToIntArr("0 " + br.readLine());

            stb.append(new 트리_4256(V, preorder, inorder).solve()).append("\n");
        }

        System.out.print(stb);
    }
}