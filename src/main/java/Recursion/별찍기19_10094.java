package Recursion;

import java.io.*;

class 별찍기19_10094 {
    int N;
    int L;
    char[][] pic;

    public 별찍기19_10094(int N){
        this.N = N;
        L = 4 * (N - 1);
        pic = new char[L + 2][L + 2];
    }

    void solve(int l, int y, int x){
        if(l == 0){
            pic[y][x] = '*';
            return;
        }
        for(int xx = x; xx <= x + l; xx++){
            pic[y][xx] = '*';
            pic[y + l][xx] = '*';
        }

        for(int yy = y; yy <= y + l; yy++){
            pic[yy][x] = '*';
            pic[yy][x + l] = '*';
        }

        solve(l - 4, y + 2, x + 2);
    }

    String getAns(){
        solve(L, 0, 0);

        StringBuilder stb = new StringBuilder();
        for(int y = 0; y <= L; y++){
            for(int x = 0; x <= L; x++){
                stb.append(pic[y][x] == '*' ? pic[y][x] : ' ');
            }
            stb.append("\n");
        }
        return stb.toString();
    }
}

class MainA10994 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new 별찍기19_10094(Integer.parseInt(br.readLine())).getAns());
        bw.flush();
    }
}
