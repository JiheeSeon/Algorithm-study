package Sort;
import java.io.*;
import java.util.regex.Pattern;

class InsertionSort {
    static int[] input;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        insertionSort();
        StringBuilder stb = new StringBuilder();
        for(int element : input){
            stb.append(element).append(" ");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void insertionSort(){
        int currentValue, i;

        for (int currentIdx = 1; currentIdx < input.length; currentIdx++){
            currentValue = input[currentIdx];

            for (i = currentIdx - 1; i >= 0; i--){ // 자기 앞의 애들 기준으로 돌기
                if (input[i] > currentValue) input[i + 1] = input[i];
                else break; // 자리 찾음
            }
            input[i + 1] = currentValue;
        }
    }
}
