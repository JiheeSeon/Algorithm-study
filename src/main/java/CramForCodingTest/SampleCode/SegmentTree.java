package CramForCodingTest.SampleCode;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/*
구간합구하기 4, 1 문제 참고
*/

class SegmentTree {
    int N, H;
    BigInteger[] input;
    BigInteger[] tree;

    public SegmentTree(int N, BigInteger[] input) {
        this.N = N;
        this.input = input;

        // set basic information of tree
        H = (int) (Math.ceil(Math.log10(N) / Math.log10(2)));
        tree = new BigInteger[1 << (H + 1)]; // 2^(H + 1)

        init(0, input.length - 1, 1);
    }

    BigInteger init(int start, int end, int idx) {
        if(start == end){
            tree[idx] = input[start];
            return tree[idx];
        }

        int mid = (start + end) / 2;
        return (tree[idx] = init(start, mid, idx * 2).add(init(mid + 1, end, idx * 2 + 1)));
    }

    BigInteger sum(int a, int b){
        return sum(0, N - 1, a, b, 1);
    }

    private BigInteger sum(int start, int end, int a, int b, int idx) {
        if(end < a || start > b) return BigInteger.ZERO;
        else if(a <= start && end <= b) return tree[idx]; // 사실상 여기서 결정
        int mid = (start + end) / 2;
        return sum(start, mid, a, b, idx * 2).add(sum(mid + 1, end, a, b, idx * 2 + 1)); // 두 트리 모두 사용
    }

    void update(long idxToUpdate, long valToUpdate){
        update((int)idxToUpdate, valToUpdate, 0, N - 1, 1);
    }

    private BigInteger update(int idxToUpdate, long valToUpdate, int start, int end, int treeIdx){
        if(start == end) return (tree[treeIdx] = BigInteger.valueOf(valToUpdate));

        int mid = (start + end) / 2;
        if(idxToUpdate <= mid) return (tree[treeIdx] = update(idxToUpdate, valToUpdate, start, mid, treeIdx * 2).add(tree[treeIdx * 2 + 1]));
        else return (tree[treeIdx] = update(idxToUpdate, valToUpdate, mid + 1, end, treeIdx * 2 + 1).add(tree[treeIdx * 2]));
    }
}

class SegmentTreeApp{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] intTmp = InputProcessor.strToIntArr(br.readLine());
        long[] longTmp;
        int N = intTmp[0]; int loopN = intTmp[1] + intTmp[2];

        BigInteger[] input = new BigInteger[N];
        for(int i = 0; i < N; i++) input[i] = BigInteger.valueOf(Long.parseLong(br.readLine()));

        StringBuilder stb = new StringBuilder();
        SegmentTree segmentTree = new SegmentTree(N, input);


        while (loopN-- > 0) {
            longTmp = InputProcessor.strToLongArr(br.readLine());
            if(longTmp[0] == 1) segmentTree.update(longTmp[1] - 1, longTmp[2]);
            else stb.append(segmentTree.sum((int)longTmp[1] - 1, (int)longTmp[2] - 1)).append("\n");
        }

        System.out.print(stb);
    }
}