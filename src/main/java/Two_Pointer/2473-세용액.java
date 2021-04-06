package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

import static java.util.Collections.reverseOrder;

class Main2473{
    static int N;
    static int[] features;
    static int minSum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        features = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(features);
        System.out.println(Arrays.toString(features) + "\n");

        System.out.println(solution());

    }
    static String solution(){
        int[] res = new int[3]; //start, mid, end
        int left = 0, right = N - 1;
        List<FeatureCombination> featureCombinations = new ArrayList<>();

        while(left < right){
            if (Math.abs(features[left]) > Math.abs(features[right])){
                while(left < right
                        && Math.abs(features[left]) >= Math.abs(features[right])) left++;
                featureCombinations.add(new FeatureCombination(
                        features[left - 1] + features[right], left - 1, right));
            } else {
                while(left < right
                        && Math.abs(features[left]) <= Math.abs(features[right])) right--;
                featureCombinations.add(new FeatureCombination(
                        features[left] + features[right + 1], left, right + 1));
            }
        }

        featureCombinations.sort(Comparator.comparing(s -> s.sum, reverseOrder()));

        for(FeatureCombination fc : featureCombinations){
            System.out.println(fc);
        }
        int fcPointer = -1;
        int featuresPointer = 0;
        int tmp; int midIdx;

        for(FeatureCombination fc : featureCombinations){
            fcPointer++;

            System.out.println(fc);

            while (!(-fc.sum < features[featuresPointer]
                    && -fc.sum < features[featuresPointer + 1])) featuresPointer++;

            System.out.println("-fc.sum = " + (-fc.sum) +
                    " features[featuresPointer] = " + features[featuresPointer] +
                    " features[featuresPointer + 1] = " + features[featuresPointer + 1]);

            if(fc.start == fcPointer || fc.end == fcPointer) continue;

            if(featuresPointer == N) {
                tmp = Math.abs(fc.sum + features[featuresPointer - 1]);
                midIdx = featuresPointer - 1;
            }
            else {
                if (Math.abs(fc.sum + features[featuresPointer]) < Math.abs(fc.sum + features[featuresPointer - 1])){
                    tmp = Math.abs(fc.sum + features[featuresPointer]);
                    midIdx = featuresPointer;
                }else {
                    tmp = Math.abs(fc.sum + features[featuresPointer - 1]);
                    midIdx = featuresPointer - 1;
                }
            }
            if(tmp < minSum){
                res[0] = features[fc.start];
                res[1] = features[fc.end];
                res[2] = features[midIdx];
                minSum = tmp;
            }
        }
        Arrays.sort(res);

        StringBuilder stb = new StringBuilder();
        return stb.append(res[0]).append(" ").append(res[1]).append(" ").append(res[2]).toString();
    }

    static private class FeatureCombination{
        int start, end, sum;

        public FeatureCombination(int sum, int startIdx, int endIdx){
            this.sum = sum;
            this.start = startIdx;
            this.end = endIdx;
        }

        @Override
        public String toString() {
            return "start=" + features[start] + ", end=" + features[end] + ", sum=" + sum;
        }
    }
}