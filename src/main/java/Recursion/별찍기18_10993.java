package Recursion;

import java.io.*;

class Solution{
    int N;
    int W, H;
    char[][] toDisplay;

    public Solution(int N){
        this.N = N;
        H = (int)Math.pow(2, N) - 1;
        W = (int)Math.pow(2, N + 1) - 3;
        toDisplay = new char[H][W];
    }
    void solve(int n, int y, int x){
        // n == 1
        if(n == 1){
            toDisplay[y][x] = '*';
            return;
        }

        int h = (int)Math.pow(2, n) - 1;
        int w = (int)Math.pow(2, n + 1) - 3;

        // select y to draw guide line
        boolean isEven = n % 2 == 0;
        int yy = isEven ? y : y + h - 1;
        for(int xx = 0; xx < w ; xx++)
            toDisplay[yy][xx] = '*';

        int dist = 1;
        int toAdd = isEven ? 1 : -1;
        while((yy = yy + toAdd) > 0 && yy < h - 1){
            toDisplay[yy][dist] = '*';
            toDisplay[yy][w - dist - 1] = '*';
            dist++;
        }
        toDisplay[yy][dist] = '*';

        if(isEven) solve(n - 1, y + 1,  x + (h + 1) / 2 + 1);
        else solve(n - 1, y - h / 2, y - h / 2 + 1);
    }

    String getAns(){
        solve(N, 0, 0);

        StringBuilder stb = new StringBuilder();
        for(int y = 0; y < H; y++){
            for(int x = 0; x < W; x++){
                stb.append(toDisplay[y][x] == 0 ? ' ' : toDisplay[y][x]);
            }
            stb.append("\n");
        }
        return stb.toString();
    }
}

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new Solution(Integer.parseInt(br.readLine())).getAns());
        bw.flush();
    }
}