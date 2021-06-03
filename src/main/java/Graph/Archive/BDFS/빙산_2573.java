package Graph.Archive.BDFS;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/*
2573 빙산
전부 다 녹을 때까지 2 덩어리 이상으로 분리되지 않은 경우 -> 0 출력
*/
class Solution{
    int yHeight, xWidth;
    int[][] graph;
    boolean[][] check;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public Solution(int yHeight, int xWidth, int[][] graph){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.graph = graph;
    }

    int getAns(){
        int t = 0;
        int groupN;
        boolean stopFlag;

        while(true){
            groupN = 0;
            check = new boolean[yHeight][xWidth];
            stopFlag = true;
            t++;

            for(int y = 0; y < yHeight; y++){
                for(int x = 0; x < xWidth; x++){
                    if(graph[y][x] != 0) stopFlag = false;

                    if(!check[y][x] && graph[y][x] != 0){
                        dfs(y, x);
                        groupN++;
                    }
                }
            }

            System.out.println(t + " :: " + groupN);
            display();

            if(stopFlag) return 0;
            if(groupN >= 2) return t;
        }
    }

    void dfs(int y, int x){
        if(y < 0 || x < 0 || y >= yHeight || x >= xWidth || check[y][x]
                || (graph[y][x] = graph[y][x] >= 1 ? graph[y][x] - 1 : 0) == 0) return;

        check[y][x] = true;

        for(int i = 0; i < 4; i++)
            dfs(y + dy[i], x + dx[i]);
    }

    void display(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                stringBuilder.append(graph[y][x]).append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
}

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int yHeight = tmp[0]; int xWidth = tmp[1];

        int[][] graph = new int[yHeight][xWidth];

        for(int y = 0; y < yHeight; y++)
            graph[y] = strToIntArr(br.readLine());

        System.out.println(new Solution(yHeight, xWidth, graph).getAns());

    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}

/*
5 5
10 9 9 8 0
0 10 10 9 10
10 13 3 10 10
15 14 13 12 11
9 8 9 14 0

19
*/