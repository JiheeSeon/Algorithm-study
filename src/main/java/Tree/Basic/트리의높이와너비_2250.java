package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 트리의높이와너비_2250 {
    int V;
    int x = 0;
    int rootIdx;
    BinaryTreeNode_2250[] tree;
    ArrayList<LevelInfo> levelInfos = new ArrayList();

    public 트리의높이와너비_2250(int V, int rootIdx, BinaryTreeNode_2250[] tree) {
        this.V = V;
        this.rootIdx = rootIdx;
        this.tree = tree;
        levelInfos.add(new LevelInfo());
    }

    void inorder(BinaryTreeNode_2250 node, int level) {
        if (levelInfos.size() == level) {
            levelInfos.add(new LevelInfo());
        }

        if(node.left != null) inorder(node.left, level + 1);

        LevelInfo l;
        x++;

        l = levelInfos.get(level);
        if(l.min == Integer.MAX_VALUE) l.min = x;
        l.max = x;

        if(node.right != null) inorder(node.right, level + 1);
    }

    private class LevelInfo {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
    }

    String solve() {
        inorder(tree[rootIdx], 1);

        int width;
        int ansLevel = -1;
        int ansWidth = -1;

        LevelInfo l;
        for (int i = 1; i < levelInfos.size(); i++) {
            l = levelInfos.get(i);

            width = l.max - l.min + 1;
            if(ansWidth < width){
                ansWidth = width;
                ansLevel = i;
            }
        }

        return ansLevel + " " + ansWidth;
    }
}

class BinaryTreeNode_2250{
    int label;
    BinaryTreeNode_2250 left, right;

    public BinaryTreeNode_2250(int label) {
        this.label = label;
    }
}

class MainA2250 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] tmp;
        BinaryTreeNode_2250[] binaryTreeNodes = new BinaryTreeNode_2250[N + 1];
        for (int i = 1; i <= N; i++)
            binaryTreeNodes[i] = new BinaryTreeNode_2250(i);

        ArrayList<Integer> rootCandidates = IntStream.rangeClosed(1, N).boxed().collect(Collectors.toCollection(ArrayList::new));

        for (int i = 1; i <= N; i++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            if(tmp[1] != -1) {
                binaryTreeNodes[tmp[0]].left = binaryTreeNodes[tmp[1]];
                rootCandidates.remove((Object)tmp[1]);
            }
            if(tmp[2] != -1){
                binaryTreeNodes[tmp[0]].right = binaryTreeNodes[tmp[2]];
                rootCandidates.remove((Object)tmp[2]);
            }
        }
        System.out.println(new 트리의높이와너비_2250(N, rootCandidates.get(0), binaryTreeNodes).solve());
    }
}