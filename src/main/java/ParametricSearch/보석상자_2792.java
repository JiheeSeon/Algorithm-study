package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class 보석상자_2792 {
    int childrenN, colorN;
    long[] jewelry;

    public 보석상자_2792(int childrenN, int colorN, long[] jewelry) {
        this.childrenN = childrenN;
        this.colorN = colorN;
        this.jewelry = jewelry;
    }

    long parametricSearch() {
        long max = -1;
        for(long l : jewelry){
            if(l > max) max = l;
        }

        long low = 1;
        long high = max;
        long mid;
        long childrenSum;

        while(low <= high){
            mid = (low + high) / 2;
            childrenSum = 0;

            for (long colorOfJewelry : jewelry) {
                childrenSum += ((colorOfJewelry / mid) + (colorOfJewelry % mid == 0 ? 0 : 1));
            }

            if(childrenSum <= childrenN) high = mid - 1;
            else low = mid + 1;
        }
        return high + 1;
    }
}

class MainA2792{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int childrenN = tmp[0]; int colorN = tmp[1];

        long[] jewelry = new long[colorN];
        for(int c = 0; c < colorN; c++)
            jewelry[c] = Long.parseLong(br.readLine());

        System.out.println(new 보석상자_2792(childrenN, colorN, jewelry).parametricSearch());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}