package CramForCodingTest.PS;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 최솟값_10868 {
    int N, H;
    int[] arr, tree;

    public 최솟값_10868(int n, int[] arr) {
        N = n;
        this.arr = arr;

        H = (int) Math.ceil(Math.log10(N) / Math.log10(2));
        tree = new int[1 << (H + 1)];
        init(0, N - 1, 1);
    }

    int init(int start, int end, int idx) {
        if(start == end) return (tree[idx] = arr[start]); // 주의 포인트 1

        int mid = (start + end) / 2;
        return (tree[idx] = Math.min(init(start, mid, idx * 2), init(mid + 1, end, idx * 2 + 1)));
    }

    int solve(int a, int b) {
        return solve(0, N - 1, a, b, 1);
    }

    private int solve(int start, int end, int a, int b, int idx) {
        if(a > end || b < start) return -1; // 주의 포인트 2
        else if(a <= start && end <= b) return tree[idx]; // 주의 포인트 3

        int mid = (start + end) / 2;
        int left = solve(start, mid, a, b, idx * 2);
        int right = solve(mid + 1, end, a, b, idx * 2 + 1);

        // 주의 포인트 4
        if(left == -1 && right == -1) return -1;
        else if(left != -1 && right == -1) return left;
        else if(right != -1 && left == -1) return right;
        else return Math.min(left, right);
    }
}
class MainA10868{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int N = tmp[0]; int M = tmp[1];

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        최솟값_10868 solution = new 최솟값_10868(N, arr);

        StringBuilder stb = new StringBuilder();
        while (M-- > 0) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            stb.append(solution.solve(tmp[0] - 1, tmp[1] - 1)).append("\n");
        }
        System.out.print(stb);
    }
}