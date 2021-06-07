package Graph.Archive.BDFS;

import java.io.*;
import java.util.regex.Pattern;

/*
치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정
*/
class 치즈_2638 {
    int yHeight, xWidth;
    int[][] graph;
    int[][] nextGraph;
    boolean[][] check;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 치즈_2638(int yHeight, int xWidth, int[][] graph){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        check = new boolean[yHeight][xWidth];

        nextGraph = new int[yHeight][xWidth];
        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                nextGraph[y][x] = graph[y][x];
            }
        }
        this.graph = new int[yHeight][xWidth];
    }

    boolean dfsForInsideCheeseSpace(int y, int x, boolean[][] ck){
        if(y < 0 || x < 0 || y >= yHeight || x >= xWidth) return false;
        if(ck[y][x]) return graph[y][x] == 2;
        if(nextGraph[y][x] == 1) return true;

        ck[y][x] = true;

        boolean flag = true;
        for(int i = 0; i < 4; i++){
            if(!dfsForInsideCheeseSpace(y + dy[i], x + dx[i], ck)) flag = false;
            if(!flag) break;
        }

        if(flag) nextGraph[y][x] = 2;

        return flag;
    }


    boolean dfsForMeltingCheese(int y, int x){
        // 굳이 cnt 변수를 안 둔건 아래의 콜스택 결과들을 다 합쳐서 해야 할 것이 아니기 때문
        if(y < 0 || y >= yHeight || x < 0 || x >= xWidth || check[y][x]) return false;
        if(graph[y][x] == 0) return true;

        check[y][x] = true;
        int cnt = 0;
        for(int i = 0; i < 4; i++){
            if(dfsForMeltingCheese(y + dy[i], x + dx[i])) cnt++;
        }

        if(cnt >= 2) nextGraph[y][x] = 0;

        return false;
    }

    int getAns(){
        boolean stopFlag;
        int t = 0;

        while(true){
            if(backupGraph()) return t;

            check = new boolean[yHeight][xWidth];
            for(int y = 0; y < yHeight; y++){
                for(int x = 0; x < xWidth; x++){
                    if(graph[y][x] == 1){
                        dfsForMeltingCheese(y, x);
                    }
                }
            }
            t++;
        }
    }

    boolean backupGraph(){
        boolean stopFlag = true;

        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                if(nextGraph[y][x] == 1) stopFlag = false;
                else if(nextGraph[y][x] == 0)
                    dfsForInsideCheeseSpace(y, x, new boolean[yHeight][xWidth]);

                graph[y][x] = nextGraph[y][x];
            }
        }

        return stopFlag;
    }

    void display(int[][] graph){
        for(int y= 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++)
                System.out.print(graph[y][x]+ " ");
            System.out.println();
        }
    }

}

class MainA2638 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int yHeight = tmp[0]; int xWidth = tmp[1];

        int[][] graph = new int[yHeight][xWidth];
        for(int y = 0; y < yHeight; y++){
            graph[y] = strToIntArr(br.readLine());
        }

        System.out.println(new 치즈_2638(yHeight, xWidth, graph).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
/*
8 9
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 1 0 0 0 1 1 0
0 1 0 1 1 1 0 1 0
0 1 0 0 1 0 0 1 0
0 1 0 1 1 1 0 1 0
0 1 1 0 0 0 1 1 0
0 0 0 0 0 0 0 0 0
2

8 9
0 0 0 0 0 0 0 0 0
0 0 0 1 1 0 0 0 0
0 0 0 1 1 0 1 1 0
0 0 1 1 1 1 1 1 0
0 0 1 1 1 1 1 0 0
0 0 1 1 0 1 1 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
4
*/