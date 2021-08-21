package Tree.SegmentTree;

import java.util.Arrays;

class SegmentTreeFrame {
    int V;
    int[] weights;
    int[] segmentTree;

    public SegmentTreeFrame(int v, int[] weights) {
        V = v;
        this.weights = weights;
        segmentTree = new int[V * 4];
        makeSegmentTree(1, 0, 11);
    }

    int makeSegmentTree(int node, int start, int end) {
        if(start == end) return (segmentTree[node] = weights[start]);

        int mid = (start + end) / 2;
        return segmentTree[node] = makeSegmentTree(node * 2, start, mid) + makeSegmentTree(node * 2 + 1, mid + 1, end);
    }

    void setValueTree(int node, BinaryTree treeNode) {
        int nextLeft = node * 2;
        int nextRight = nextLeft + 1;

        if(nextLeft >= weights.length) return;
        treeNode.left = new BinaryTree(nextLeft, weights[nextLeft]);

        if(nextRight >= weights.length) return;
        treeNode.right = new BinaryTree(nextRight, weights[nextLeft]);

        setValueTree(node * 2, treeNode.left);
        setValueTree(node * 2 + 1, treeNode.right);
    }


    private class BinaryTree{
        int label;
        int weight;
        BinaryTree left = null;
        BinaryTree right = null;

        public BinaryTree(int label, int weight) {
            this.label = label;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "{" +
                    "label=" + label +
                    ", weight=" + weight +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
class SegmentTreeApplication{
    public static void main(String[] args){
        new SegmentTreeFrame(11, new int[]{0, 8, 7, 3, 2, 5, 1, 8, 9, 8, 7, 3});
    }
}