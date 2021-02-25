package Greedy;

import java.io.*;
import java.util.regex.Pattern;

class Main12931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        int[] arr = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(size).toArray();

        int i, j;
        boolean flag;
        int res = 0;

        while (true) {
            flag = true;

            for (i = 0; i < size; i++) {
                if (arr[i] % 2 == 1) {
                    arr[i] = arr[i] - 1;
                    res++;
                }
            }

            for (j = 0; j < size; j++) {
                if (arr[j] != 0) {
                    if(flag) flag = false;
                    arr[j] /= 2;
                }
            }

            if (flag) break;

            res++;
        }
        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }
}