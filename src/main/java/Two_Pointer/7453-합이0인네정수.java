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
    static long abMinSum;
    static long abMaxSum;

    static Map<Long, Integer> cdMap = new HashMap<>();
    static Map<Long, Integer> abMap = new HashMap<>();
    static int result = 0;

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
        abMinSum = ABCD[0][0] + ABCD[1][0]; abMaxSum = ABCD[0][N - 1] + ABCD[1][N - 1];
        cdProcessedSum = Arrays.stream(cdProcessedSum).sorted().filter(s -> s >= abMinSum && s <= abMaxSum).toArray();

        for(long l : cdProcessedSum){ //O(N)
            cdMap.put(l, cdMap.getOrDefault(l, 0) + 1);
        }

        for(int i = 0; i < N; i++){ //O(N^2)
            for (int j = 0; j < N; j++){
                abMap.put(ABCD[0][i] + ABCD[1][j], abMap.getOrDefault(ABCD[0][i] + ABCD[1][j], 0) + 1);
            }
        }

        abMap.keySet().retainAll(cdMap.keySet());
        for(long l : abMap.keySet()){ //O(N^2)
            result += abMap.get(l) * cdMap.get(l);
        }

        System.out.println(result);
    }

    static void display(long[] ar){
        for(int i = 0; i < ar.length; i++)
            System.out.print(ar[i] + " ");
        System.out.println();
    }
}