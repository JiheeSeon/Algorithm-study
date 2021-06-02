package Graph.Archive.BDFS;

import java.io.*;
import java.util.regex.Pattern;

/*
문제분석

해당 문제가 혼란스러웠던건, 접근법이 바로 안 보였다는 것.
처음에는 최단경로, 최장경로 이런거인줄 알고 BFS로 접근했다가,
끝까지 가야한다는 점에서 깊이 우선 탐색으로 접근해야 한다고 선회하다가,
막상 풀면서 한 경로 경로를 따져야 하기 때문에 가보고 빠꾸하면서 모든 경로를 확인하는 백트래킹으로 귀결했다는 것.

1. BFS가 안되는 이유
A가 한번 나오면 다른 A가 나올 때를 체크할 수 없다는 것.
한칸 한칸 더 가는 게 결국 depth에 대응되는 개념이라 볼 수 있음.

2. DFS가 안되었던 이유
DFS로 보통 int를 반환하는 접근은 모든 점을 다 확인한다는 것에 의의를 두는 것
dy, dx 순서에 따라 달라지므로 연결된 그룹 개수 및 연결요소들의 개수를 셀 때
한번 찍어서 확인할 수 있는 탐색기법. (ex. 영역의 넓이 기록)

!!!!결과적으로 백트래킹이었던 이유!!!!
탐색이 중요한게 아니라, 하나 하나의 경로를 다 확인해야 했기 때문

Resolve하면서 다르게 풀었던 부분
-> 알파벳마다 check 배열 두어서 체킹, 따로 set 등의 자료구조 사용하지 않음.
*/

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
        return backtrack(0, 0, 0);
    }

    int backtrack(int y, int x, int cnt){
        if(y < 0 || x < 0 || y >= yHeight || x >= xWidth || check[board[y][x] - 'A']) return cnt;

        int max = cnt;
        int nextY, nextX;

        check[board[y][x] - 'A'] = true;

        for(int i = 0; i < 4; i++)
            max = Math.max(max, backtrack(y + dy[i], x + dx[i], cnt + 1));

        check[board[y][x] - 'A'] = false;
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