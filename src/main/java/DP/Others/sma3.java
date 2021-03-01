package DP.Others;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class sma3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int [] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int numOfTotalPeanuts = input[0]; int numOfPeanutToEat = input[1]; int initialPos = input[2];

        int [] peanuts = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(x -> Integer.parseInt(x) - initialPos).toArray();

        int [] left = new int[numOfTotalPeanuts];
        int [] right = new int[numOfTotalPeanuts];
        int leftIdx = 0, rightIdx = 0;
        int i;

        for (i = 0; i < peanuts.length; i++){
            if (peanuts[i] < 0) left[leftIdx++] = -peanuts[i];
            else right[rightIdx++] = peanuts[i];
        }

        Arrays.sort(left, 0, leftIdx);
        Arrays.sort(right, 0, rightIdx);

        while(leftIdx < left.length){ left[leftIdx++] = -1; }
        while(rightIdx < right.length){  right[rightIdx++] = -1; }

        int candidate, l, r;
        int min = Integer.MAX_VALUE;

        for (int lefEat = 0; lefEat <= numOfPeanutToEat; lefEat++){
            l = (lefEat == 0) ? 0 : left[lefEat - 1];
            r = (lefEat == numOfPeanutToEat) ? 0 : right[numOfPeanutToEat - lefEat - 1];

            if (l == -1 || r == -1) continue;

            candidate = l + r;
            if (candidate < min) min = candidate;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(min));
        bw.flush();
        bw.close();
    }
}