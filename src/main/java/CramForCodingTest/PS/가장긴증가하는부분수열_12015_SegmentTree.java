package CramForCodingTest.PS;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class 가장긴증가하는부분수열_12015_SegmentTree {
    int N, H;
    int[] arr, tree;

    Pair12015[] pairs;

    public 가장긴증가하는부분수열_12015_SegmentTree(int n, int[] arr) {
        N = n;
        this.arr = arr;

        H = (int) (Math.ceil(Math.log10(N) / Math.log10(2)));
        tree = new int[1 << (H + 1)];

        pairs = new Pair12015[N];
        for (int i = 0; i < arr.length; i++) pairs[i] = new Pair12015(i, arr[i]);

        Arrays.sort(pairs);
    }

    int query(int a, int b) {
        return query(0, arr.length - 1, a, b, 1);
    }

    private int query(int start, int end, int a, int b, int idx){
        if(b < start || a > end) return -1;
        else if (a <= start && end <= b) return tree[idx];

        int mid = (start + end) / 2;
        return Math.max(query(start, mid, a, b, idx * 2), query(mid + 1, end, a, b, idx * 2 + 1));
    }

    void update(int idx, int val){
        update(idx, val, 0, arr.length - 1, 1);
    }

    private int update(int idxToUpdate, int valToUpdate, int start, int end, int treeIdx) {
        if(start == end) return (tree[treeIdx] = valToUpdate);
        int mid = (start + end) / 2;

        return (tree[treeIdx] = idxToUpdate <= mid
                ? Math.max(tree[treeIdx * 2 + 1], update(idxToUpdate, valToUpdate, start, mid, treeIdx * 2))
                : Math.max(tree[treeIdx * 2], update(idxToUpdate, valToUpdate, mid + 1, end, treeIdx * 2 + 1)));
    }

    int solve() {
        for (int i = 0; i < pairs.length; i++)
            update(pairs[i].idx, query(0, pairs[i].idx) + 1);

        return query(0, arr.length - 1);
    }
}

class Pair12015 implements Comparable<Pair12015>{
    int idx, val;

    public Pair12015(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Pair12015 o) {
        if(val == o.val) return Integer.compare(o.idx, idx);
        else return Integer.compare(val, o.val);
    }
}

class MainA12015_SegmentTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = InputProcessor.strToIntArr(br.readLine());
        System.out.println(new 가장긴증가하는부분수열_12015_SegmentTree(N, arr).solve());
    }
}

/*
TC
6
10 20 10 30 20 50
4

12
10 20 40 25 30 28 29 50 40 45 60 35
8

8
6 1 2 7 8 3 5 4
4
*/