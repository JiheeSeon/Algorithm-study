package Recursion;

import java.io.*;

/* 문제 접근 기록
처음에는 정직하게 프랙탈 삼각형 3개로 나누어서 각각을 재귀로 풀려 함.

-> 재귀함수의 역할을 처음에 출력(StringBuilder에 append)으로 잡음
   각 stage에서 StringBuilder로 append 하려 했으나
   3번째 삼각형과 2번째 삼각형의 층계가 같기 때문에 append로 처리하기 어려웠음
   층마다 계산하면 사실상 재귀를 사용하는 의미가 없어짐.

-> 출력을 재귀함수의 role로 잡을 것이 아니라 배열의 원소 세팅으로 잡았어야.
   또한 char 배열을 두어서 모든 자리에 대한 값을 세팅하는 문제로 넘겨보았어야 함.
   배열로 보는 순간 사각형으로 구역 분할이 가능해짐.
   너무 string으로 생각했다..!

   참고 풀이
   https://ssu-gongdoli.tistory.com/79
   수학적 접근이 인상깊었음
*/

class 별찍기11_2448 {
    int N;
    char[][] stars = { "  *  ".toCharArray(),
            " * * ".toCharArray(),
            "*****".toCharArray()};
    char[][] toPrint;

    public 별찍기11_2448(int N){
        this.N = N;
        toPrint = new char[N][N * 2];
    }

    // y, x 는 사각형의 시작점
    void solve(int n, int y, int x){
        if(n == 1){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 5; j++){
                    toPrint[y + i][x + j] = stars[i][j];
                }
            }
            return;
        }

        solve(n / 2, y, x + n * 3 / 2);
        solve(n / 2, y + n * 3 / 2, x);
        solve(n / 2, y + n * 3 / 2, x + n * 3);
    }

    String getAns(){
        solve(N /3, 0, 0);
        StringBuilder stb = new StringBuilder();
        for(int y = 0; y < N; y++){
            for(int x = 0; x < 2 * N; x++)
                stb.append(toPrint[y][x] == 0 ? ' ' : toPrint[y][x]);
            stb.append("\n");
        }
        return stb.toString();
    }
}

class MainA2448 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new 별찍기11_2448(N).getAns());
        bw.flush();
    }
}