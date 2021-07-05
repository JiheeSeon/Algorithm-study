package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

class 먹을것인가먹힐것인가_7795 {
    int sizeA, sizeB;
    int[] A, B;

    public 먹을것인가먹힐것인가_7795(int sizeA, int sizeB, int[] a, int[] b) {
        this.sizeA = sizeA;
        this.sizeB = sizeB;
        A = a;
        B = b;
    }

    int solution() {
        Arrays.sort(A);
        Arrays.sort(B);

        int sum = 0;
        for(int i : A){
            sum += parametricSearch(i);
        }

        return sum;
    }

    int parametricSearch(int a) {
        int low = 0;
        int high = sizeB - 1;
        int mid;

        while(low <= high){
            mid = (low + high) / 2;

            if(B[mid] >= a) high = mid - 1;
            else low = mid + 1;
        }

        return high + 1;
    }
}

class MainA7795{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int sizeA, sizeB;
        int[] A, B;
        int[] tmp;

        StringBuilder stb = new StringBuilder();
        while(--T >= 0){
            tmp = strToIntArr(br.readLine());
            sizeA = tmp[0]; sizeB = tmp[1];
            A = strToIntArr(br.readLine());
            B = strToIntArr(br.readLine());
            stb.append(new 먹을것인가먹힐것인가_7795(sizeA, sizeB, A, B).solution()).append("\n");
        }
        System.out.print(stb);
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}