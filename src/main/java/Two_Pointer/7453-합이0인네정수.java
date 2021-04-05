package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main7453{
    static int N;
    static long[][] ABCD;
    static long[][] pointerValueRange = new long[4][2]; // A->B->C / min -> max
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ABCD = new long[4][N];

        int [] temp;
        for(int i = 0; i < N; i++){
            temp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < ABCD.length; j++)  ABCD[j][i] = temp[j];
        }
        for (int i = 0; i < 4; i++) Arrays.sort(ABCD[i]);

        int idx = 3;
        int tmpMin = 0; int tmpMax = 0;

        while(idx > 0){
            tmpMin += ABCD[idx][N - 1];
            tmpMax += ABCD[idx--][0];
            pointerValueRange[idx][0] = -tmpMin;
            pointerValueRange[idx][1] = -tmpMax;
        }
        pointerValueRange[3][0] = (long) -Math.pow(2, 28);
        pointerValueRange[3][1] = (long) Math.pow(2, 28);

        solution();

        System.out.println(result);
    }
    static void solution(){
        pick(0, 0);
    }
    static void pick(int depth, long accumulated){
        if(depth == 3){
            for (int i = 0; i < N; i++){
                if (-accumulated == ABCD[depth][i]){
                    result++;
                }
            }
            return;
        }
        for (int i = 0; i < N; i++){
            if (accumulated + ABCD[depth][i] < pointerValueRange[depth][0]) continue;
            if (accumulated + ABCD[depth][i] > pointerValueRange[depth][1]) break;

            pick(depth + 1, accumulated + ABCD[depth][i]);
        }
    }
    static void display(long[][] array){
        System.out.println();

        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}