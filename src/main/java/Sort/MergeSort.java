package Sort;

import java.io.*;
import java.util.regex.Pattern;

class MergeSort {
    static int[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        mergeSort();
        StringBuilder stb = new StringBuilder();
        for(int i : input)
            stb.append(i).append(" ");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
    static void mergeSort(){

    }
}
