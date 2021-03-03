package Sort;

import java.io.*;
import java.util.regex.Pattern;

class SelectionSort {
    static int[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        selectionSort();

        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < input.length; i++)
            stb.append(input[i]).append(" ");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void selectionSort(){
        int minIdx, temp;

        for(int startPoint = 0; startPoint < input.length - 1; startPoint++){
            minIdx = startPoint;
            for(int i = startPoint + 1; i < input.length; i++){
                if (input[minIdx] > input[i]) minIdx = i;
            }
            //swap
            temp = input[minIdx];
            input[minIdx] = input[startPoint];
            input[startPoint] = temp;
        }
    }
}
