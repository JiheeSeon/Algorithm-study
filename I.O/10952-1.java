import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main10952_1{
    public static void main(String[] args) throws IOException {
        /* method 1. 제일 먼저 생각나는 방법
            Input :: Scanner
            split :: split
            Output:: System.out.println
        */
        /*
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            s = sc.nextLine();
            int a = Integer.parseInt(s.split(" ")[0]);
            int b = Integer.parseInt(s.split(" ")[1]);
            if(a==0 && b==0)
                System.exit(0);
            System.out.println(a+b);
        }
        */

        /* method 2.
            method 1과 동일한 로직, Input과 split하는 방식만 변화
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int first, second;
        while(true){
            st = new StringTokenizer(br.readLine(), " ");
            first = Integer.parseInt(st.nextToken());
            second = Integer.parseInt(st.nextToken());
            if(first==0 && second ==0)
                break;
            else
                System.out.println(first+second);
        }
    }
}