package CramForCodingTest.PS;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 최솟값과최댓값_2357 {
    int N;
    int H;
    long[] arr;
    long[] minTree, maxTree;

    final static int MIN = 0;
    final static int MAX = 1;

    public 최솟값과최댓값_2357(int n, long[] arr) {
        N = n;
        this.arr = arr;
        H = (int)(Math.ceil(Math.log10(N) / Math.log10(2)));
        minTree = new long[1 << (H + 1)];
        maxTree = new long[1 << (H + 1)];

        init(0, N - 1, 1);
    }

    long[] init(int start, int end, int idx) {
        if(start == end){
            minTree[idx] = arr[start];
            maxTree[idx] = arr[start];
            return new long[]{arr[start], arr[start]};
        }

        int mid = (start + end) / 2;
        long[] leftTreeRes = init(start, mid, idx * 2);
        long[] rightTreeRes = init(mid + 1, end, idx * 2 + 1);

        minTree[idx] = Math.min(leftTreeRes[MIN], rightTreeRes[MIN]);
        maxTree[idx] = Math.max(leftTreeRes[MAX], rightTreeRes[MAX]);

        return new long[]{minTree[idx], maxTree[idx]};
    }

    String solve(int a, int b) {
        long[] res = solve(0, N - 1, a, b, 1);
        return res[0] + " " + res[1];
    }

    private long[] solve(int start, int end, int a, int b, int idx) {
        if(b < start || a > end) return null;
        else if(a <= start && end <= b) return new long[]{minTree[idx], maxTree[idx]};

        int mid = (start + end) / 2;
        long[] left = solve(start, mid, a, b, idx * 2);
        long[] right = solve(mid + 1, end, a, b, idx * 2 + 1);

        if(left == null && right == null) return null;
        else if(left != null && right == null) return left;
        else if(right != null && left == null) return right;
        return new long[]{Math.min(left[MIN], right[MIN]), Math.max(left[MAX], right[MAX])};
    }
}

class MainA2357{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int N = tmp[0]; int M = tmp[1];
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(br.readLine());

        최솟값과최댓값_2357 solution = new 최솟값과최댓값_2357(N, arr);
        StringBuilder stb = new StringBuilder();
        while(M-- > 0) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            stb.append(solution.solve(tmp[0] - 1, tmp[1] - 1)).append("\n");
        }
        System.out.print(stb);
    }
}