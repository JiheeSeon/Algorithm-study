package Sort;

import java.io.*;
import java.util.regex.Pattern;

class BubbleSort{
    static int[] input;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        bubbleSort();

        StringBuilder stb = new StringBuilder();
        for (int j : input)
            stb.append(j).append(" ");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
    static void bubbleSort(){
        int temp;
        for (int endPoint = input.length - 1; endPoint > 0; endPoint--){ // end point 만 조정해주면 됨
            for (int i = 0; i < endPoint; i++){
                if (input[i] > input[i+1]){ //swap
                    temp = input[i];
                    input[i] = input[i+1];
                    input[i+1] = temp;
                }
            }
        }
    }
}