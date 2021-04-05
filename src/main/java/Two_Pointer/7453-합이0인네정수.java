package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class Main7453{
    static int N;
    static long[][] ABCD;

    static long[] cdProcessedSum;
    static long[] abSum;
    static long abMinSum, abMaxSum;
    static long cdMinSum, cdMaxSum;

    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ABCD = new long[4][N];
        cdProcessedSum = new long[N*N];
        abSum = new long[N*N];

        int [] temp;
        for(int i = 0; i < N; i++){
            temp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < ABCD.length; j++)  ABCD[j][i] = temp[j];
        }
        for (int i = 0; i < 4; i++) Arrays.sort(ABCD[i]);

        for(int i = 0; i < N; i++){ //O(N^2)
            for (int j = 0; j < N; j++){
                cdProcessedSum[N * i + j] = - ABCD[2][i] - ABCD[3][j];
            }
        }
        for(int i = 0; i < N; i++){ //O(N^2)
            for (int j = 0; j < N; j++){
                abSum[N * i + j] = ABCD[0][i] + ABCD[1][j];
            }
        }

        abMinSum = ABCD[0][0] + ABCD[1][0]; abMaxSum = ABCD[0][N - 1] + ABCD[1][N - 1];
        cdProcessedSum = Arrays.stream(cdProcessedSum).sorted().filter(s -> s >= abMinSum && s <= abMaxSum).toArray();

        cdMinSum = cdProcessedSum[0]; cdMaxSum = cdProcessedSum[cdProcessedSum.length - 1];
        abSum = Arrays.stream(abSum).sorted().filter(s -> s >= cdMinSum && s <= cdMaxSum).toArray();

//        cdProcessedSum = new long[]{-72, -71, -67, -56, -52, -40, -40, -25, -8, 3, 5, 6, 6, 8, 10, 10, 10, 11, 13};
//        abSum = new long[]{-80, -73, -72, -69, -56, -51, -43, -41, -40, -40, 5, 6, 6, 8, 10, 10, 11, 12};
//         -72, -56, -40(4), 5, 6(4), 8, 10 (6) -> 19

        int cdPointer = 0, abPointer = 0;
        while(true){
            if(cdPointer >= cdProcessedSum.length || abPointer >= abSum.length) break;

            int tmpCdPointer, tmpAbPointer;

            if (cdProcessedSum[cdPointer] == abSum[abPointer]){
                tmpCdPointer = cdPointer++; tmpAbPointer = abPointer++;

                while(cdPointer < cdProcessedSum.length && cdProcessedSum[cdPointer] == abSum[tmpAbPointer]) cdPointer++;
                while(abPointer < abSum.length && cdProcessedSum[tmpCdPointer] == abSum[abPointer]) abPointer++;

                result += (abPointer - tmpAbPointer) * (cdPointer - tmpCdPointer);

//                System.out.println("SAME value = " + abSum[tmpAbPointer]);
//                System.out.println("result = " + result + " added = "+ (abPointer - tmpAbPointer) * (cdPointer - tmpCdPointer));
            }
            else if (cdProcessedSum[cdPointer] > abSum[abPointer]) abPointer++;
            else cdPointer++;
        }

        System.out.println(result);
    }

    static void display(long[] ar){
        for(int i = 0; i < ar.length; i++)
            System.out.print(ar[i] + " ");
        System.out.println();
    }
}