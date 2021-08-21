package Tree.SegmentTree;

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

    void update(int node, int start, int end, int idxToUpdate, int deltaValToUpdate) {
        if(idxToUpdate < start || idxToUpdate > end) return;

        segmentTree[node] += deltaValToUpdate;
        if(start == end) return;

        int mid = (start + end) / 2;
        update(node * 2, start, mid, idxToUpdate, deltaValToUpdate);
        update(node * 2 + 1, mid + 1, end, idxToUpdate, deltaValToUpdate);
    }

    long getSum(int node, int start, int end, int intervalStart, int intervalEnd) {
        if(start > intervalEnd || end < intervalStart) return 0;
        if(start == intervalStart && intervalEnd == end) return segmentTree[node];

        int mid = (start + end) / 2;
        long ret = 0;

        if(mid >= intervalStart)
            ret += getSum(node * 2, start, mid, intervalStart, Math.min(intervalEnd, mid));

        if(mid + 1 <= intervalEnd)
            ret += getSum(node * 2 + 1, mid + 1, end, Math.max(intervalStart, mid + 1), intervalEnd);

        return ret;
    }
}
class SegmentTreeApplication{
    public static void main(String[] args){
        SegmentTreeFrame s = new SegmentTreeFrame(11, new int[]{0, 8, 7, 3, 2, 5, 1, 8, 9, 8, 7, 3});
        s.update(1, 0, 11, 7, -5);
        //[0, 61, 25, 36, 15, 10, 18, 18, 8, 7, 5, 5, 9, 9, 15, 3, 0, 8, 0, 0, 3, 2, 0, 0, 1, 8, 0, 0, 8, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //-> [0, 56, 25, 31, 15, 10, 13, 18, 8, 7, 5, 5, 4, 9, 15, 3, 0, 8, 0, 0, 3, 2, 0, 0, 1, 3, 0, 0, 8, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

        System.out.println(s.getSum(1, 0, 11, 4, 9));
    }
}