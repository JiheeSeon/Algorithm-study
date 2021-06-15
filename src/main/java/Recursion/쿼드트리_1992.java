package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class 쿼드트리_1992{
    int N;
    int[][] graph;

    public 쿼드트리_1992(int N, int[][] graph) {
        this.N = N;
        this.graph = graph;
    }

    String solve(int n, int y, int x) {
        if(n == 1){
            return Integer.toString(graph[y][x]);
        }

        String a1 = solve(n / 2, y, x);
        String a2 = solve(n / 2, y, x + n / 2);
        String a3 = solve(n / 2, y + n / 2, x);
        String a4 = solve(n / 2, y + n / 2, x + n / 2);

        if((a1.equals("0") || a1.equals("1")) && a1.equals(a2) && a2.equals(a3) && a3.equals(a4)){
            return a1;
        } else{
            StringBuilder stb = new StringBuilder();
            stb.append("(").append(a1).append(a2).append(a3).append(a4).append(")");
            return stb.toString();
        }
    }

    String getAns(){
        return solve(N, 0, 0);
    }
}
class MainA1992{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        for(int y = 0; y < N; y++){
            graph[y] = strToIntArr(br.readLine());
        }

        System.out.println(new 쿼드트리_1992(N, graph).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile("").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}