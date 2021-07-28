package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class 완전이진트리_9934 {
    int[] inorder;
    int height;
    String info;
    ArrayList<Integer>[] binaryTree;

    public 완전이진트리_9934(int k, String info) {
        height = k;
        this.info = info;

        binaryTree = new ArrayList[height + 1];
        for(int h = 1; h <= height; h++){
            binaryTree[h] = new ArrayList<>();
        }
    }

    String solveForPerfectTree() {
        inorder = InputProcessor.strToIntArr(info);

        dfsForPerfectTree(1, 0, inorder.length);
        return makeTreeInfoIntoString(new StringBuilder());
    }

    void dfsForPerfectTree(int level, int leftIdx, int rightIdx) {
        if(level > height || leftIdx > rightIdx) return;

        int midIdx = (leftIdx + rightIdx) / 2;
        binaryTree[level].add(inorder[midIdx]);
        dfsForPerfectTree(level + 1, leftIdx, midIdx - 1);
        dfsForPerfectTree(level + 1, midIdx + 1, rightIdx);
    }

    String makeTreeInfoIntoString(StringBuilder stb) {
        for(int h = 1; h <= height; h++){
            for (int element : binaryTree[h]) {
                stb.append(element).append(" ");
            }
            stb.append("\n");
        }
        return stb.toString();
    }

    String solveForCompleteTree() {
        inorder = InputProcessor.strToIntArr("0 " + info);
        dfsForCompleteTree(1, height, 0, inorder.length - 1);
        return makeTreeInfoIntoString(new StringBuilder());
    }

    void dfsForCompleteTree(int rootDepth, int heightOfSubTree, int startPadding, int allNodeCount) {
        if(rootDepth > height || heightOfSubTree < 0 || allNodeCount <= 0) return;

        int leafNodeCnt = allNodeCount - getAllNodeCountOfPerfectBinaryTree(heightOfSubTree - 1); // 20 - 15
        int leafNodeCntForPBT = getLevelNodeCountOfPerfectBinaryTree(heightOfSubTree); // 16

        int leftSubTreeNodeCnt, rightSubTreeNodeCnt;
        if (leafNodeCnt < leafNodeCntForPBT / 2) { // 왼쪽이 길고 오른쪽은 한 레벨이 통채로 나감
            leftSubTreeNodeCnt = getAllNodeCountOfPerfectBinaryTree(heightOfSubTree - 2) + leafNodeCnt;
            rightSubTreeNodeCnt = getAllNodeCountOfPerfectBinaryTree(heightOfSubTree - 2);

            binaryTree[rootDepth].add(inorder[startPadding + leftSubTreeNodeCnt + 1]);

            dfsForCompleteTree(rootDepth + 1, heightOfSubTree - 1, startPadding, leftSubTreeNodeCnt);
            dfsForCompleteTree(rootDepth + 1, heightOfSubTree - 2, startPadding + leftSubTreeNodeCnt + 1, rightSubTreeNodeCnt);
        } else { // 왼쪽은 풀 채워지고 오른쪽은 덜 채워짐
            leftSubTreeNodeCnt = getAllNodeCountOfPerfectBinaryTree(heightOfSubTree - 1);
            rightSubTreeNodeCnt = getAllNodeCountOfPerfectBinaryTree(heightOfSubTree - 2) + (leafNodeCnt - leafNodeCntForPBT / 2);

            binaryTree[rootDepth].add(inorder[startPadding + leftSubTreeNodeCnt + 1]);

            dfsForCompleteTree(rootDepth + 1, heightOfSubTree - 1, startPadding, leftSubTreeNodeCnt);
            dfsForCompleteTree(rootDepth + 1, heightOfSubTree - 1, startPadding + leftSubTreeNodeCnt + 1, rightSubTreeNodeCnt);
        }
    }

    int getAllNodeCountOfPerfectBinaryTree(int heightOfTree) {
        return (int)Math.pow(2, heightOfTree) - 1;
    }

    int getLevelNodeCountOfPerfectBinaryTree(int level) {
        return (int)Math.pow(2, level - 1);
    }
}

class MainA9934{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        System.out.print(new 완전이진트리_9934(K, br.readLine()).solveForPerfectTree());
    }
}

/*
Complete BTree
5
23 5 25 7 27 8 29 6 31 11 4 14 3 13 9 20 2 17 10 19

3
6 2
7 4 9 10
5 8 11 14 13 20 17 19
23 25 27 29 31


5
23 5 25 7 27 8 29 6 31 11 35 4 50 14 53 3 13 9 20 2 17 10 19

3
6 2
7 4 9 10
5 8 11 14 13 20 17 19
23 25 27 29 31 35 50 53


Perfect BTree
4
5 7 8 6 11 4 14 3 13 9 20 2 17 10 19

3
6 2
7 4 9 10
5 8 11 14 13 20 17 19
*/