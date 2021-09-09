package CramForCodingTest.PS;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class 구간곱구하기_11505 {
    int N, H;
    int[] arr;
    int[] tree;

    public 구간곱구하기_11505(int n, int[] arr) {
        N = n;
        this.arr = arr;
        H = (int) (Math.ceil(Math.log10(N) / Math.log10(2)));
        tree = new int[1 << (H + 1)];

        init(0, N - 1, 1);
    }

    int init(int start, int end, int idx) {
        if(start == end) return (tree[idx] = arr[start] % 1000000007);

        int mid = (start + end) / 2;
        return (tree[idx] = (init(start, mid, idx * 2) % 1000000007) * (init(mid + 1, end, idx * 2 + 1) % 1000000007) % 1000000007);
    }

    void update(int idxToUpdate, int valToupdate) {
        update(idxToUpdate, valToupdate, 0, N - 1, 1);
    }

    private int update(int idxToUpdate, int valToUpdate, int start, int end, int treeIdx) {
        if(start == end) return (tree[treeIdx] = valToUpdate % 1000000007);

        int mid = (start + end) / 2;
        return (tree[treeIdx]
                = (idxToUpdate <= mid)
                ? (tree[treeIdx * 2 + 1] % 1000000007) * (update(idxToUpdate, valToUpdate, start, mid, treeIdx * 2) % 1000000007) % 1000000007
                : (tree[treeIdx * 2] % 1000000007) * (update(idxToUpdate, valToUpdate, mid + 1, end, treeIdx * 2 + 1) % 1000000007) % 1000000007);

    }

    int multiply(int a, int b) {
        return multiply(a, b, 0, N - 1, 1);
    }

    private int multiply(int a, int b, int start, int end, int idx) {
        if(b < start || a > end) return -1;
        else if(a <= start && end <= b) return tree[idx];

        int mid = (start + end) / 2;
        int left = multiply(a, b, start, mid, idx * 2);
        int right = multiply(a, b, mid + 1, end, idx * 2 + 1);

        if(left == -1 && right == -1) return -1;
        else if(left != -1 && right == -1) return left % 1000000007;
        else if(right != -1 && left == -1) return right % 1000000007;
        else return (left % 1000000007) * (right % 1000000007) % 1000000007;
    }
}

class MainA11505{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int N = tmp[0]; int loopN = tmp[1] + tmp[2];
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        구간곱구하기_11505 solution = new 구간곱구하기_11505(N, arr);
        StringBuilder stb = new StringBuilder();

        while (loopN-- > 0) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            if(tmp[0] == 1){
                solution.update(tmp[1] - 1, tmp[2]);
            } else{
                stb.append(solution.multiply(tmp[1] - 1, tmp[2] - 1)).append("\n");
            }
        }
        System.out.print(stb);
    }
}