package CramForCodingTest.SampleCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/*
2957 이진탐색트리

*/
class BST {
    BinaryNode rootNode;
    int cnt = 0;

    public BST(int number) {
        rootNode = new BinaryNode(number);
    }

    void insert(int number) {
        insert(number, rootNode);
    }

    private void insert(int number, BinaryNode node) {
        cnt++;

        if(node.label > number){
            if(node.left == null) node.left = new BinaryNode(number);
            else insert(number, node.left);
        }
        else{
            if(node.right == null) node.right = new BinaryNode(number);
            else insert(number, node.right);
        }
    }

    boolean find(int val) {
        return find(val, rootNode);
    }

    private boolean find(int val, BinaryNode node){
        if(node == null) return false;
        if(val == node.label) return true;

        return (node.label > val) ? find(val, node.left) : find(val, node.right);
    }

//    boolean delete(int val) {
//
//    }
}

class BinaryNode {
    int label;
    BinaryNode left = null;
    BinaryNode right = null;

    public BinaryNode(int label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "label=" + label +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryNode that = (BinaryNode) o;
        return label == that.label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}

class BSTApp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();

        BST bst = new BST(Integer.parseInt(br.readLine()));
        stb.append("0\n");

        for (int i = 1; i < N; i++) {
            bst.insert(Integer.parseInt(br.readLine()));
            stb.append(bst.cnt).append("\n");
        }

        System.out.print(stb);
    }
}