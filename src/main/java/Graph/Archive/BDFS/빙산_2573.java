package Graph.Archive.BDFS;

import java.io.*;
import java.util.regex.Pattern;

/*
2573 빙산
주변에 갈 수 없는 길이 있을 때 빼야 함.
그룹의 개수를 세는 건 dfs를 사용하든 bfs를 사용하든 상관 없음.
값을 바꾸는걸 각각 진행하고 dfs로 연결요소 개수 카운트하면 될듯

전부 다 녹을 때까지 2 덩어리 이상으로 분리되지 않은 경우 -> 0 출력
=> 모두 다 녹았는데 덩어리로 쪼개지지 않은 경우
*/

class Solution{
    int yHeight, xWidth;
    int[][] oldGraph;
    int[][] newGraph;
    boolean[][] check;
    boolean zeroFlag;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public Solution(int yHeight, int xWidth, int[][] oldGraph){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.oldGraph = oldGraph;
        newGraph = new int[yHeight][xWidth];
    }

    int getAns(){
        int t = 1;
        int groupN;

        while(true) {
            zeroFlag = true;

            setNewGraph();
            if(getGroupCnt() >= 2) return t;

            backupNewGraphToOldGraph();
            if(zeroFlag) return 0;

            t++;
        }
    }

    void backupNewGraphToOldGraph() {
        int toSubtract;

        for (int y = 0; y < yHeight; y++) {
            for(int x = 0; x < xWidth; x++){
                oldGraph[y][x] = newGraph[y][x];
                if(oldGraph[y][x] != 0) zeroFlag = false;
            }
        }
    }

    void setNewGraph() {
        int toSubtract;
        int nextY, nextX;

        for (int y = 0; y < yHeight; y++) {
            for (int x = 0; x < xWidth; x++) {
                toSubtract = 0;

                for(int i = 0; i < 4; i++){
                    nextY = y + dy[i];
                    nextX = x + dx[i];

                    if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth) continue;
                    if(oldGraph[y + dy[i]][x+dx[i]] == 0) toSubtract++;
                }

                newGraph[y][x] = Math.max(oldGraph[y][x] - toSubtract, 0);
            }
        }
    }

    int getGroupCnt(){
        int groupN = 0;

        check = new boolean[yHeight][xWidth];
        for (int y = 0; y < yHeight; y++) {
            for (int x = 0; x < xWidth; x++) {
                if(!check[y][x] && newGraph[y][x] != 0){
                    dfs(y, x);
                    groupN++;
                }
            }
        }
        return groupN;
    }

    void dfs(int y, int x){
        if(y < 0 || x < 0 || y >= yHeight || x >= xWidth || check[y][x] || newGraph[y][x] == 0)
            return;

        check[y][x] = true;

        for(int i = 0; i < 4; i++){
            dfs(y + dy[i], x + dx[i]);
        }
    }

    void display(){
        StringBuilder stringBuilder = new StringBuilder("\n");
        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                stringBuilder.append(newGraph[y][x]).append(" ");
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