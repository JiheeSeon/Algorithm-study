package Graph.Archive.BDFS;

import java.io.*;
import java.util.regex.Pattern;

class 알파벳_1987 {
    int yHeight, xWidth;
    char[][] board;
    boolean[] check;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 알파벳_1987(int yHeight, int xWidth, char[][] board){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.board = board;
        check = new boolean[26];
    }

    int getAns(){
        check[board[0][0] - 'A'] = true;
        return backtrack(0, 0, 1);
    }

    int backtrack(int y, int x, int cnt){
        int max = cnt;
        int nextY, nextX;

        for(int i = 0; i < 4; i++){
            nextY = y + dy[i];
            nextX = x + dx[i];

            if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth || check[board[nextY][nextX] - 'A'])
                continue;

            check[board[nextY][nextX] - 'A'] = true;
            max = Math.max(max, backtrack(nextY, nextX, cnt + 1));
            check[board[nextY][nextX] - 'A'] = false;
        }

        return max;
    }
}

class MainA1987 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine(), " ");
        int yHeight = tmp[0]; int xWidth = tmp[1];

        char[][] board = new char[yHeight][xWidth];
        for(int y = 0; y < yHeight; y++){
            board[y] = br.readLine().toCharArray();
        }

        System.out.println(new 알파벳_1987(yHeight, xWidth, board).getAns());
    }
    static int[] strToIntArr(String s, String delimiter){
        return Pattern.compile(delimiter).splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}