package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

// TestCase 1
// 19
//-250 -240 -190 -150 -140 -100 -60 -30 -10 45 70 80 90 110 160 170 180 200 300

// TestCase 2
//8
//-250 -240 -190 -150 140 154 160 180

// Testcase 3
//10
//-100 0 10 20 30 40 50 56 57 58

//Testcase 4
//9
//-14 -13 -12 0 50 60 70 80 90

class Main2467 {
    static int N;
    static int[] features;
    static int leftRes, rightRes;
    static int minSum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        features = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution());
    }

    static String solution() {
        StringBuilder stb = new StringBuilder();

        int leftPointer = 0, rightPointer= features.length - 1;
        int mainPointer; int counterPointer;
        int toAdd; int tmpSum;
        boolean leftIsChosen;

        while (leftPointer < rightPointer) {

            if (Math.abs(features[leftPointer]) < Math.abs(features[rightPointer])) {
                leftIsChosen = false;
                mainPointer = rightPointer;
                counterPointer = leftPointer;
                toAdd = -1;
            } else {
                leftIsChosen = true;
                mainPointer = leftPointer;
                counterPointer = rightPointer;
                toAdd = 1;
            }

            while (((leftIsChosen && mainPointer < counterPointer)
                    || (!leftIsChosen && counterPointer < mainPointer))
                    && Math.abs(features[mainPointer]) >= Math.abs(features[counterPointer])) {
                mainPointer += toAdd;
            }

            if (leftIsChosen) leftPointer = mainPointer;
            else rightPointer = mainPointer;

            tmpSum = features[mainPointer - toAdd] + features[counterPointer];

//            System.out.print("features[mainPointer] = " + features[mainPointer - toAdd]);
//            System.out.println(" features[counterPointer] = " + features[counterPointer]);
//            System.out.println("tmpSum = " + tmpSum);
//            System.out.println("leftPointer = " + leftPointer);
//            System.out.println("rightPointer = " + rightPointer);

            if (Math.abs(tmpSum) < minSum) {
                minSum = Math.abs(tmpSum);

                if (leftIsChosen) {
                    leftRes = features[mainPointer - toAdd];
                    rightRes = features[counterPointer];
                } else {
                    leftRes = features[counterPointer];
                    rightRes = features[mainPointer - toAdd];
                }

                if (tmpSum == 0) {
                    stb.append(leftRes).append(" ").append(rightRes);
                    return stb.toString();
                }
            }
        }
        stb.append(leftRes).append(" ").append(rightRes);
        return stb.toString();
    }
}