package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

/*
4902 삼각형의 값

*/

class 삼각형의값_4902 {
    int L;
    int[] triangle;
    int maxDepth;
    int max; //음수일 수도 있기 때문

    int getAns(int[] triangle){
        this.triangle = triangle;
        L = triangle[0];
        maxDepth = L;
        max = Integer.MIN_VALUE;
        recursion(1, 1, 1);
        return max;
    }

    int recursion(int depth, int level, int apexIdx){
        if(depth == maxDepth){
            max = Math.max(max, triangle[apexIdx]);
            return triangle[apexIdx];
        }

        int sameDepthTriangleN = 2 * level - 1;

        int[] tr = new int[4];
        tr[0] = recursion(depth + 1, level, apexIdx);
        tr[1] = recursion(depth + 1, level + 1, apexIdx + sameDepthTriangleN);
        tr[2] = recursion(depth + 1, level + 1, apexIdx + sameDepthTriangleN + 2);

        int l = level - 1;
        int startIdx = apexIdx;
        int loopN;

        // tr[3] 전체 합 구하기
        int Y = L + 1 - depth; // 전체 길이
        for(int y = level; y < level + Y; y++){
            sameDepthTriangleN = 2 * y - 1;
            loopN = 2 * (y - depth + 1) - 1;

            for(int x = 0; x < loopN; x++){
                tr[3] += triangle[startIdx + x];
            }
            startIdx += sameDepthTriangleN;
        }

        max = Math.max(max, Arrays.stream(tr).max().getAsInt());
        return tr[3];
    }
}

class MainA4902 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] triangle = strToIntArr(br.readLine());
        StringBuilder stb = new StringBuilder();
        int idx = 0;

        삼각형의값_4902 reusableS = new 삼각형의값_4902();
        while(triangle[0] != 0){
            stb.append(++idx).append(".").append(reusableS.getAns(triangle)).append("\n");
            triangle = strToIntArr(br.readLine());
        }
        System.out.print(stb);
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}