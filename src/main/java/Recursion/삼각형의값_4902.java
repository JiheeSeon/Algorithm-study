package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

/*
4902 삼각형의 값
*/

class 삼각형의값_4902 {
    int L;
    int[] triangle;
    int maxDepth;
    int max; //음수일 수도 있기 때문

    Map<Triangle, Integer> dp;

    int getAns(int[] triangle){
        this.triangle = triangle;
        L = triangle[0];
        maxDepth = L;
        dp = new HashMap<>();
        max = Integer.MIN_VALUE;

        recursion(L, 1, 1);
        return max;
    }

    int recursion(int length, int level, int apexIdx){
        if(length == 1){
            max = Math.max(max, triangle[apexIdx]);
            dp.put(new Triangle(apexIdx, 1), triangle[apexIdx]);
            return triangle[apexIdx];
        }

        if(level >= L){
            return triangle[apexIdx];
        }

        int sameLevelTriangleN = 2 * level - 1;

        int[] tr = new int[4];

        Triangle tmp = new Triangle(apexIdx, length - 1);
        if(!dp.containsKey(tmp)){
            tr[0] = recursion(length - 1, level, apexIdx);
            dp.put(tmp, tr[0]);
        } else tr[0] = dp.get(tmp);

        tmp = new Triangle(apexIdx + sameLevelTriangleN, length - 1);
        if(!dp.containsKey(tmp)){
            tr[1] = recursion(length - 1, level + 1, apexIdx + sameLevelTriangleN);
            dp.put(tmp, tr[1]);
        } else tr[1] = dp.get(tmp);

        tmp = new Triangle(apexIdx + sameLevelTriangleN + 2, length - 1);
        if(!dp.containsKey(tmp)){
            tr[2] = recursion(length - 1, level + 1, apexIdx + sameLevelTriangleN + 2);
            dp.put(tmp, tr[2]);
        } else tr[2] = dp.get(tmp);

        // tr[3] 전체 합 구하기
        tr[3] = tr[0] + tr[1] + tr[2];

        if(length == 2){
            tr[3] += triangle[apexIdx + sameLevelTriangleN + 1];
        }else {

            // 한변의 길이가 2 이상일 때
            tmp = new Triangle(apexIdx, length);
            int subApexIdx = apexIdx + sameLevelTriangleN;

            if (!dp.containsKey(tmp)) {
                // add three sub triangles
                tr[3] -= dp.getOrDefault(new Triangle(subApexIdx, length - 2), recursion(length - 2, level + 1, subApexIdx));
                subApexIdx += 2;
                tr[3] -= dp.getOrDefault(new Triangle(subApexIdx, length - 2), recursion(length - 2, level + 1, subApexIdx));
                subApexIdx += 2 * (level + 1) - 1;
                tr[3] -= dp.getOrDefault(new Triangle(subApexIdx, length - 2), recursion(length - 2, level + 2, subApexIdx));

                if (length > 3) {
                    subApexIdx += (2 * (level + 2) - 1);
                    tr[3] += dp.getOrDefault(new Triangle(subApexIdx, length - 3), recursion(length - 2, level + 3, subApexIdx));
                }

                dp.put(tmp, tr[3]);
            } else tr[3] = dp.get(tmp);
        }

        dp.put(new Triangle(apexIdx, length), tr[3]);
        max = Math.max(max, Arrays.stream(tr).max().getAsInt());

        return tr[3];
    }

    private class Triangle{
        int apexIdx;
        int length;

        public Triangle(int apexIdx, int length) {
            this.apexIdx = apexIdx;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Triangle triangle = (Triangle) o;
            return apexIdx == triangle.apexIdx && length == triangle.length;
        }

        @Override
        public int hashCode() {
            return Objects.hash(apexIdx, length);
        }
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
            stb.append(++idx).append(". ").append(reusableS.getAns(triangle)).append("\n");
            triangle = strToIntArr(br.readLine());
        }
        System.out.print(stb);
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}