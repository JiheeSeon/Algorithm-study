package Tree;

import java.io.*;
import java.util.*;

class Main1991_2{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        BTreeNode[] btree = new BTreeNode[N];
        for(int c = 0; c < N; c++){
            btree[c] = new BTreeNode((char)(c + 'A'));
        }
        String[] input;
        int currIdx;
        for(int i = 0; i < N; i++){
            input = br.readLine().split(" ");
            currIdx = input[0].charAt(0) - 'A';

            btree[currIdx].left = input[1].charAt(0);
            btree[currIdx].right = input[2].charAt(0);
        }
        System.out.print(new 트리순회_1991_sol2().solution(btree));
    }
}
class BTreeNode{
    char curr;
    char left; char right;

    public BTreeNode(char curr){
        this.curr = curr;
    }
}
class 트리순회_1991_sol2 {
    public String solution(BTreeNode[] btree) {
        long answer = 0;
        StringBuilder stb = new StringBuilder();
        stb.append(preorder('A', new StringBuilder(), btree)).append("\n");
        stb.append(inorder('A', new StringBuilder(), btree)).append("\n");
        stb.append(postorder('A', new StringBuilder(), btree)).append("\n");
        return stb.toString();
    }

    String preorder(char nowNode, StringBuilder stb, BTreeNode[] btree){
        if(nowNode == '.') return stb.toString();

        stb.append(nowNode);
        preorder(btree[nowNode - 'A'].left, stb, btree);
        preorder(btree[nowNode - 'A'].right, stb, btree);

        return stb.toString();
    }

    String inorder(char nowNode, StringBuilder stb, BTreeNode[] btree){
        if(nowNode == '.') return stb.toString();

        inorder(btree[nowNode - 'A'].left, stb, btree);
        stb.append(nowNode);
        inorder(btree[nowNode - 'A'].right, stb, btree);

        return stb.toString();
    }

    String postorder(char nowNode, StringBuilder stb, BTreeNode[] btree){
        if(nowNode == '.') return stb.toString();

        postorder(btree[nowNode - 'A'].left, stb, btree);
        postorder(btree[nowNode - 'A'].right, stb, btree);
        stb.append(nowNode);

        return stb.toString();
    }
}