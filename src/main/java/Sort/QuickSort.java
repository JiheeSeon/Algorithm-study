package Sort;

import java.io.*;
import java.util.regex.Pattern;

class QuickSort {
    static int[] input; //5 3 8 4 9 1 6 2 7

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

//        quickSort(input, 0, input.length - 1);

        StringBuilder stb = new StringBuilder();
        for (int i : input)
            stb.append(i).append(" ");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void quickSort() {

    }

    static void partition(int left, int right) {

    }
}
