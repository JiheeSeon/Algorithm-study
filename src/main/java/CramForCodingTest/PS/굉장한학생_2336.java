package CramForCodingTest.PS;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

class 굉장한학생_2336 {
    int N, H;
    int[][] ranks;
    int[] tree;

    private final int INF = Integer.MAX_VALUE;

    public 굉장한학생_2336(int n, int[][] ranks) {
        N = n;
        this.ranks = ranks;

        H = (int) (Math.ceil(Math.log10(N) / Math.log10(2)));
        tree = new int[1 << (H + 1)];
        Arrays.fill(tree, INF);
    }

    int query(int a, int b) {
        return query(a, b, 0, N - 1, 1);
    }

    private int query(int a, int b, int start, int end, int idx) {
        if(end < a || start > b) return INF;
        else if(a <= start && end <= b) return tree[idx];

        int mid = (start + end) / 2;
        int left = query(a, b, start, mid,idx * 2);
        int right = query(a, b, mid + 1, end,idx * 2 + 1);

        if(left == INF && right == INF) return INF;
        else if(left != INF && right == INF) return left;
        else if(left == INF && right != INF) return right;
        else return Math.min(left, right);
    }

    void update(int idx, int value) {
        update(idx, value, 0, N - 1, 1);
    }

    private int update(int idxToUpdate, int valToUpdate, int start, int end, int treeIdx) {
        if(start == end) return (tree[treeIdx] = valToUpdate);

        int mid = (start + end) / 2;
        return (tree[treeIdx] = (idxToUpdate <= mid)
                ? Math.min(tree[treeIdx * 2 + 1], update(idxToUpdate, valToUpdate, start, mid, treeIdx * 2))
                : Math.min(tree[treeIdx * 2], update(idxToUpdate, valToUpdate, mid + 1, end, treeIdx * 2 + 1))
        );
    }

    int solve() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            if (query(0, ranks[1][i]) > ranks[2][i]) res++;
            update(ranks[1][i], ranks[2][i]);
        }
        return res;
    }
}

class MainA2336{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] ranks = new int[3][N + 1];
        for (int i = 0; i < 3; i++)
            ranks[i] = InputProcessor.strToIntArr(br.readLine());
        Arrays.sort(ranks, Comparator.comparing(s -> s[0]));

        System.out.println(new 굉장한학생_2336(N, ranks).solve());
    }
}