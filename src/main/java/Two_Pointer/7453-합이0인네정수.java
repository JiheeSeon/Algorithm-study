package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
/*
* 성공까지 시간을 끌었던 부분, 헷갈린 지점 또는 패착의 원인
* index가 마지막 인덱스가 되었을 때의 처리 -> 길이와 함께 조건 체크
* 같은 value가 연속으로 나왔을 때의 처리 -> tmpPointer
* result 의 타입 long 캐스팅
* */
class Main7453{
    static int N;
    static long[][] ABCD;

    static long[] cdSumWithSignChange;
    static long[] abSum;
    static long abMinSum, abMaxSum;
    static long cdMinSum, cdMaxSum;

    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ABCD = new long[4][N];
        cdSumWithSignChange = new long[N*N];
        abSum = new long[N*N];

        int [] temp;
        for(int i = 0; i < N; i++){
            temp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < ABCD.length; j++)  ABCD[j][i] = temp[j];
        }
        for (int i = 0; i < 4; i++) Arrays.sort(ABCD[i]);

        for(int i = 0; i < N; i++){ //O(N^2)
            for (int j = 0; j < N; j++){
                cdSumWithSignChange[N * i + j] = - ABCD[2][i] - ABCD[3][j];
            }
        }
        for(int i = 0; i < N; i++){ //O(N^2)
            for (int j = 0; j < N; j++){
                abSum[N * i + j] = ABCD[0][i] + ABCD[1][j];
            }
        }

        abMinSum = ABCD[0][0] + ABCD[1][0]; abMaxSum = ABCD[0][N - 1] + ABCD[1][N - 1];
        cdSumWithSignChange = Arrays.stream(cdSumWithSignChange).sorted().filter(s -> s >= abMinSum && s <= abMaxSum).toArray();

        cdMinSum = cdSumWithSignChange[0]; cdMaxSum = cdSumWithSignChange[cdSumWithSignChange.length - 1];
        abSum = Arrays.stream(abSum).sorted().filter(s -> s >= cdMinSum && s <= cdMaxSum).toArray();

        int cdPointer = 0, abPointer = 0;
        while(true){
            if(cdPointer >= cdSumWithSignChange.length || abPointer >= abSum.length) break;

            int tmpCdPointer, tmpAbPointer;

            if (cdSumWithSignChange[cdPointer] == abSum[abPointer]){
                tmpCdPointer = cdPointer++; tmpAbPointer = abPointer++;

                while(cdPointer < cdSumWithSignChange.length && cdSumWithSignChange[cdPointer] == abSum[tmpAbPointer]) cdPointer++;
                while(abPointer < abSum.length && cdSumWithSignChange[tmpCdPointer] == abSum[abPointer]) abPointer++;

                result += (long)(abPointer - tmpAbPointer) * (cdPointer - tmpCdPointer);
            }
            else if (cdSumWithSignChange[cdPointer] > abSum[abPointer]) abPointer++;
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