package Recursion;

import java.io.*;

class 별찍기18_10093 {
    int N;
    int H, W;
    int Y, X;
    char[][] pic;

    public 별찍기18_10093(int N){
        this.N = N;

        H = (int)Math.pow(2, N) - 2;
        W = H * 2;

        pic = new char[H + 2][W + 2];

        Y = 0; X = 0;
    }

    void solve(int n, int y, int x, int h, int w){
        if(n == 1){
            pic[y][x] = '*';
            return;
        }

        boolean isEven = n % 2 == 0;
        // x축에 평행한 선 그리기
        int yy = isEven ? y : y + h;
        for(int xx = x; xx <= x + w; xx++){
            pic[yy][xx] = '*';
        }

        // 양 대각선 그리기
        int dy = isEven ? 1 : -1;
        int x1 = x + 1; int x2 = x + w - 1;
        yy += dy;

        int cnt = 0;
        while(cnt++ < h){
            if(x1 > x2) break;
            pic[yy][x1] = '*';
            pic[yy][x2] = '*';
            x1++; x2--;
            yy += dy;
        }

        // 다음 재귀호출 준비
        int nextW  = (w - 4) / 2;
        int nextH = nextW / 2;
        int nextY = isEven ? y + 1 : y + nextH + 1;
        int nextX = x + w / 4 + 1;

        solve(n - 1, nextY, nextX, nextH, nextW);
    }

    String getAns() {
        StringBuilder stb = new StringBuilder();
        int x, x1, x2;
        boolean isEven = N % 2 == 0;

        solve(N, 0, 0, H, W);

        // y = 0
        int w, dw;
        if (isEven) {
            for (x = 0; x <= W; x++) stb.append('*');
            w = W - 1;
            dw = -2;
        } else {
            for (x = 0; x < W / 2; x++) stb.append(' ');
            stb.append('*');
            w = 3;
            dw = 2;
        }
        stb.append("\n");

        for (int y = 1; y <= H; y++) {
            x = -1;

            // 별 나올 때까지 공백찍기
            while(x++ <= W) {
                if (pic[y][x] == '*') break;
                stb.append(' ');
            }

            for (int i = 0; i <= w; i++) {
                stb.append(pic[y][x + i] == 0 ? ' ' : '*');
            }
            stb.append("\n");
            w += dw;
        }

        return stb.toString();
    }

    void display(){
        StringBuilder stb = new StringBuilder();
        for(int y = 0; y <= H; y++){
            for(int x = 0; x <= W; x++){
                stb.append(pic[y][x]);
            }
            stb.append("\n");
        }
        System.out.println(stb);
    }
}
class MainA10093 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new 별찍기18_10093(Integer.parseInt(br.readLine())).getAns());
        bw.flush();
    }
}