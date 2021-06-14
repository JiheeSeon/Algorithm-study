package Recursion;

import java.io.*;

class 칸토어집합_4779 {
    int N;
    StringBuilder s;
    String ans = "";

    public 칸토어집합_4779(int N){
        this.N = N;
        s = new StringBuilder("-".repeat((int)Math.pow(3, N)));
    }

    void solve(int start, int end){
        int length = end - start;
        if(length < 3) return;
        if(length == 3){
            s.replace((start + end) / 2, (start + end) / 2 + 1, " ");
            return;
        }

        int s1 = start + length / 3;
        int s2 = end - length / 3;

        s.replace(s1, s2, " ".repeat(s2 - s1));

        solve(start, s1);
        solve(s2, end);
    }

    String getAns() {
        solve(0, (int)Math.pow(3, N));
        return s.toString();
    }
}

class MainA4779 {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s; int N;
        StringBuilder stb = new StringBuilder();
        while((s = br.readLine()) != null){
            N = Integer.parseInt(s);
            stb.append(N == 0 ? "-" : new 칸토어집합_4779(N).getAns()).append("\n");
        }
        System.out.print(stb);
    }
}