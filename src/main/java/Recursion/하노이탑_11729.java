package Recursion;

import java.io.*;

class 하노이탐_11729 {
    int N;
    StringBuilder stb = new StringBuilder();

    public 하노이탐_11729(int N) {
        this.N = N;
    }

    int hanoi(int N, int start, int via, int end){
        if (N == 1){
            stb.append(start).append(" ").append(end).append("\n");
            return 1;
        }

        int sum = 0;
        sum += hanoi(N - 1, start, end, via);
        stb.append(start).append(" ").append(end).append("\n");
        sum += 1;
        sum += hanoi(N - 1, via, start, end);

        return sum;
    }

    String getAns(){
        return stb.insert(0, "\n").insert(0, hanoi(N, 1, 2, 3)).toString();
    }
}

class MainA11729 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N > 20) return;
        System.out.print(new 하노이탐_11729(N).getAns());
    }
}