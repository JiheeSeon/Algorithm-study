package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class BST표현1_객체 {
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        char[] nodeInfo;
        for(int i = 0; i < N; i++){
            nodeInfo = br.readLine().replace(" ", "").toCharArray();
            nodes[nodeInfo[0] - 'A'] = new Node(nodeInfo);
        }

        StringBuilder stb = new StringBuilder();
        stb.append(preorder('A', new StringBuilder())).append("\n");
        stb.append(inorder('A', new StringBuilder())).append("\n");
        stb.append(postorder('A', new StringBuilder())).append("\n");
        System.out.print(stb);
    }

    static StringBuilder preorder(char current, StringBuilder stb) {
        if(current == '.') return stb;

        int currIdx = current - 'A';
        Node currNode = nodes[currIdx];

        stb.append(current);

        preorder(currNode.leftChild, stb);
        preorder(currNode.rightChild, stb);

        return stb;
    }

    static StringBuilder inorder(char current, StringBuilder stb) {
        if(current == '.') return stb;

        int currIdx = current - 'A';
        Node currNode = nodes[currIdx];

        inorder(currNode.leftChild, stb);

        stb.append(current);

        inorder(currNode.rightChild, stb);

        return stb;
    }

    static StringBuilder postorder(char current, StringBuilder stb) {
        if(current == '.') return stb;

        int currIdx = current - 'A';
        Node currNode = nodes[currIdx];

        postorder(currNode.leftChild, stb);
        postorder(currNode.rightChild, stb);

        stb.append(current);

        return stb;
    }

    static private class Node{
        char label;
        char leftChild, rightChild;

        public Node(char[] nodeInfo) {
            label = nodeInfo[0];
            leftChild = nodeInfo[1];
            rightChild = nodeInfo[2];
        }
    }
}
