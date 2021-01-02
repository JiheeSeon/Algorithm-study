// scanner가 입출력이 늦어서, 여러번 부르는게 시간초과날 때
// import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder(); --> 출력을 한번에 할 때 사용
        String[] input = bf.readLine().split(" ");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        System.out.println(a+b);
    }
}