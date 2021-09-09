package CramForCodingTest.SampleCode;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SegmentTree {
    int N, H;
    int[] input;
    int[] tree;

    public SegmentTree(int N, int[] input) {
        this.N = N;
        this.input = input;

        // set basic information of tree
        H = (int) (Math.ceil(Math.log10(N) / Math.log10(2)));
        tree = new int[1 << (H + 1)];

        init(0, input.length - 1, 1);
    }

    int init(int start, int end, int idx) {
        if(start == end){
            tree[idx] = input[start];
            return tree[idx];
        }

        int mid = (start + end) / 2;
        return (tree[idx] = init(start, mid, idx * 2) + init(mid + 1, end, idx * 2 + 1));
    }

    int sum(int a, int b){
        return sum(0, N - 1, a - 1, b - 1, 1);
    }

    private int sum(int start, int end, int a, int b, int idx) {
        if(end < a || start > b) return 0;

        int mid = (start + end) / 2;

        if(a == start && b == end) return tree[idx];
        else if(b <= mid) return sum(start, mid, a, b, idx * 2); // 왼쪽 트리
        else if(mid + 1 <= a) return sum(mid + 1, end, a, b, idx * 2 + 1); // 오른쪽 트리
        else return sum(start, mid, a, mid, idx * 2) + sum(mid + 1, end, mid + 1, b, idx * 2 + 1); // 두 트리 모두 사용
    }
}

class SegmentTreeApp{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int N = tmp[0]; int M = tmp[1];
        int[] input = InputProcessor.strToIntArr(br.readLine());
        StringBuilder stb = new StringBuilder();
        SegmentTree segmentTree = new SegmentTree(N, input);

        while (M-- > 0) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            stb.append(segmentTree.sum(tmp[0], tmp[1])).append("\n");
        }

        System.out.print(stb);
    }
}