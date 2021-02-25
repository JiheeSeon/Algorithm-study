package Example;

import java.util.Arrays;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;

class MainLambdaSum{
    public static void main(String[] args) throws IOException{
        // 배열의 합 구하기
        List<String> input = Arrays.asList(new BufferedReader(new InputStreamReader(System.in)).readLine().split(","));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(input.stream().map(Integer::parseInt).reduce((accu, curr) -> accu+curr)));
        
        input.stream().map(Integer::parseInt).reduce((accu, curr) -> accu+curr);
        // int sum = input.stream().mapToInt(Integer::parseInt).sum();
    }
}