package CramForCodingTest.SampleCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

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
        } else{
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

    boolean delete(int val) {
        return delete(val, rootNode, null);
    }

    private boolean delete(int val, BinaryNode node, BinaryNode parentNode) {
        if(node == null) return false;
        if (val == node.label){
            // 1. 단말노드일 경우
            if (node.left == null && node.right == null) {
                if(parentNode.label > node.label) parentNode.left = null;
                else parentNode.right = null;
            } // 2. 하나의 서브 트리만 있는 경우
            else if(node.left == null && node.right != null){
                parentNode.right = node.right;
            }
            else if(node.left != null && node.right == null){
                parentNode.left = node.left;
            }
            // 3. 두 개의 서브트리가 있는 경우
            else {
                // 먼저 관계 정리부터
                BinaryNode tmp = node.right;
                while(tmp.left != null) tmp = tmp.left;
                tmp.left = node.left;

                if(parentNode == null){ // root 일 경우
                    rootNode = node.right;
                }
                else if(parentNode.label > node.label){ // 내가 부모의 왼쪽 자식이었을 경우
                    parentNode.left = node.right;
                } else { // 내가 부모의 오른쪽 자식이었을 경우
                    parentNode.right = node.right;
                }
            }
        } else return node.label > val ? delete(val, node.left, node) : delete(val, node.right, node);
        return true;
    }

    @Override
    public String toString() {
        return "[" +rootNode + ']';
    }
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

        BST bst = new BST(Integer.parseInt(br.readLine()));

        for (int i = 1; i < N; i++) {
            bst.insert(Integer.parseInt(br.readLine()));
        }

        System.out.println(bst);
        System.out.println();
        System.out.println(bst.delete(99));
        System.out.println(bst);
        System.out.println();

        System.out.println(bst.delete(30));
        System.out.println(bst);
        System.out.println();

        System.out.println(bst.delete(23)); // false
        System.out.println(bst);
        System.out.println();

        System.out.println(bst.delete(18));
        System.out.println(bst);
        System.out.println();

        System.out.println(bst.delete(35));
        System.out.println(bst);
        System.out.println();
    }
}
/*
TC

INPUT
14
35
18
68
99
81
130
7
3
12
26
150
30
22
140

OUTPUT
[BinaryNode{label=35, left=BinaryNode{label=18, left=BinaryNode{label=7, left=BinaryNode{label=3, left=null, right=null}, right=BinaryNode{label=12, left=null, right=null}}, right=BinaryNode{label=26, left=BinaryNode{label=22, left=null, right=null}, right=BinaryNode{label=30, left=null, right=null}}}, right=BinaryNode{label=68, left=null, right=BinaryNode{label=99, left=BinaryNode{label=81, left=null, right=null}, right=BinaryNode{label=130, left=null, right=BinaryNode{label=150, left=BinaryNode{label=140, left=null, right=null}, right=null}}}}}]

true
[BinaryNode{label=35, left=BinaryNode{label=18, left=BinaryNode{label=7, left=BinaryNode{label=3, left=null, right=null}, right=BinaryNode{label=12, left=null, right=null}}, right=BinaryNode{label=26, left=BinaryNode{label=22, left=null, right=null}, right=BinaryNode{label=30, left=null, right=null}}}, right=BinaryNode{label=68, left=null, right=BinaryNode{label=130, left=BinaryNode{label=81, left=null, right=null}, right=BinaryNode{label=150, left=BinaryNode{label=140, left=null, right=null}, right=null}}}}]

true
[BinaryNode{label=35, left=BinaryNode{label=18, left=BinaryNode{label=7, left=BinaryNode{label=3, left=null, right=null}, right=BinaryNode{label=12, left=null, right=null}}, right=BinaryNode{label=26, left=BinaryNode{label=22, left=null, right=null}, right=null}}, right=BinaryNode{label=68, left=null, right=BinaryNode{label=130, left=BinaryNode{label=81, left=null, right=null}, right=BinaryNode{label=150, left=BinaryNode{label=140, left=null, right=null}, right=null}}}}]

false
[BinaryNode{label=35, left=BinaryNode{label=18, left=BinaryNode{label=7, left=BinaryNode{label=3, left=null, right=null}, right=BinaryNode{label=12, left=null, right=null}}, right=BinaryNode{label=26, left=BinaryNode{label=22, left=null, right=null}, right=null}}, right=BinaryNode{label=68, left=null, right=BinaryNode{label=130, left=BinaryNode{label=81, left=null, right=null}, right=BinaryNode{label=150, left=BinaryNode{label=140, left=null, right=null}, right=null}}}}]

true
[BinaryNode{label=35, left=BinaryNode{label=26, left=BinaryNode{label=22, left=BinaryNode{label=7, left=BinaryNode{label=3, left=null, right=null}, right=BinaryNode{label=12, left=null, right=null}}, right=null}, right=null}, right=BinaryNode{label=68, left=null, right=BinaryNode{label=130, left=BinaryNode{label=81, left=null, right=null}, right=BinaryNode{label=150, left=BinaryNode{label=140, left=null, right=null}, right=null}}}}]

true
[BinaryNode{label=68, left=BinaryNode{label=26, left=BinaryNode{label=22, left=BinaryNode{label=7, left=BinaryNode{label=3, left=null, right=null}, right=BinaryNode{label=12, left=null, right=null}}, right=null}, right=null}, right=BinaryNode{label=130, left=BinaryNode{label=81, left=null, right=null}, right=BinaryNode{label=150, left=BinaryNode{label=140, left=null, right=null}, right=null}}}]
*/