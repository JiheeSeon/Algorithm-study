package CramForCodingTest.PS;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 직사각형_6549 {
    int N, H;
    long[] height;
    int[] tree; // index를 저장하는 minimum segment tree
    long ans = 0;

    public 직사각형_6549(int n, long[] height) {
        N = n;
        this.height = new long[N];
        for (int i = 0; i < N; i++) { this.height[i] = height[i + 1];}

        H = (int) Math.ceil((Math.log10(N) / Math.log10(2)));
        tree = new int[1 << (H + 1)];

        init(0, N - 1, 1);
    }

    int init(int start, int end, int idx) {
        if(start == end) return (tree[idx] = start);

        int mid = (start + end) / 2;
        int leftVal = init(start, mid,idx * 2);
        int rightVal = init(mid + 1, end,idx * 2 + 1);

        return (tree[idx] = (height[leftVal] < height[rightVal]) ? leftVal : rightVal);
    }

    int getMinIdx(int a, int b) {
        return getMinIdx(0, N - 1, a, b, 1);
    }

    private int getMinIdx(int start, int end, int a, int b, int idx) {
        if(b < start || a > end) return -1;
        if(a <= start && end <= b) return tree[idx];

        int mid = (start + end) / 2;

        int leftMinIdx = getMinIdx(start, mid, a, b, idx * 2);
        int rightMinIdx = getMinIdx(mid + 1, end, a, b, idx * 2 + 1);

        // idx 간의 min 값을 구하고자 하는 것이 아님.
        if(leftMinIdx == -1 && rightMinIdx == -1) return -1;
        else if(leftMinIdx == -1 && rightMinIdx != -1) return rightMinIdx;
        else if(rightMinIdx == -1 && leftMinIdx != -1) return leftMinIdx;
        else return (height[leftMinIdx] < height[rightMinIdx]) ? leftMinIdx : rightMinIdx;
    }

    long solve() {
        solve(0, N - 1);
        return ans;
    }

    private void solve(int a, int b) {
        if(a > b) return;

        int minIdx = getMinIdx(a, b);
        ans = Math.max(ans, height[minIdx] * (b - a + 1));

        // 분할 정복 (Core idea)
        solve(a, minIdx - 1);
        solve(minIdx + 1, b);
    }
}

class MainA6549{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] input = InputProcessor.strToLongArr(br.readLine());

        StringBuilder stb = new StringBuilder();

        while (!(input.length == 1 && input[0] == 0)) {
            stb.append(new 직사각형_6549((int)input[0], input).solve()).append("\n");
            input = InputProcessor.strToLongArr(br.readLine());
        }
        System.out.print(stb);
    }
}

/*
반례
7 0 5 7 5 5 3 1
0

Answer = 20
Output = 30

4 1 4 3 3
0

Answer = 9
Output = 12

5 1 3 2 4 6
0

Answer = 8
Output = 12
*/