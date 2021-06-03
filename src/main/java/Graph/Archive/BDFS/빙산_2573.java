package Graph.Archive.BDFS;

import java.io.*;
import java.util.regex.Pattern;

/*
2573 빙산

#1. 한번에 생각나는 솔루션
주변에 갈 수 없는 길이 있을 때 빼야 함.
그룹의 개수를 세는 건 dfs를 사용하든 bfs를 사용하든 상관 없음.
값을 바꾸는걸 각각 진행하고 dfs로 연결요소 개수 카운트하면 될듯

전부 다 녹을 때까지 2 덩어리 이상으로 분리되지 않은 경우 -> 0 출력
=> 모두 다 녹았는데 덩어리로 쪼개지지 않은 경우

#2. 다른 솔루션
BFS 혹은 DFS에서 다른 솔루션으로 접근할 수는 없을까?
찾는 solution과 함께 통합하면 안될까?
-> 현재로서는 통합하기 어렵다고 생각.
무조건 그룹개수는 변화가 다 이루어진 이후에 진행되어야 함.
dfs로 탐색해서 빙하 변화를 반영할 수는 있지만 cnt 는 별도로 이루어짐
*/

class 빙산_2573_1 {
    int yHeight, xWidth;
    int[][] oldGraph;
    int[][] newGraph;
    boolean[][] check;
    boolean zeroFlag;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 빙산_2573_1(int yHeight, int xWidth, int[][] oldGraph){
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

class 빙산_2573_2 {
    int yHeight, xWidth;
    int[][] oldGraph;
    int[][] newGraph;
    boolean[][] check;
    boolean zeroFlag;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 빙산_2573_2(int yHeight, int xWidth, int[][] oldGraph){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.oldGraph = oldGraph;
        newGraph = new int[yHeight][xWidth];
        copyGraph(oldGraph, newGraph);
    }

    void copyGraph(int[][] A, int[][] B) {
        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                B[y][x] = A[y][x];
                if(B[y][x] != 0) zeroFlag = false;
            }
        }
    }

    int getAns() {
        int t = 1;
        int groupN;

        while(true){
            check = new boolean[yHeight][xWidth];
            zeroFlag = true;
            groupN = 0;

            for(int y = 0; y < yHeight; y++){
                for(int x = 0; x < xWidth; x++){
                    if(!check[y][x] && oldGraph[y][x] != 0) {
                        // 여기를 newGraph[y][x]로 했으면
                        change(y, x, -100, -100);
                    }
                }
            }

            check = new boolean[yHeight][xWidth];
            for (int y = 0; y < yHeight; y++) {
                for (int x = 0; x < xWidth; x++) {
                    if(!check[y][x] && newGraph[y][x] != 0){
                        dfs(y, x);
                        groupN++;
                    }
                }
            }

            if(groupN >= 2) return t;

            copyGraph(newGraph, oldGraph);
            if(zeroFlag) return 0;
            t++;
        }
    }

    void change(int nowY, int nowX, int prevY, int prevX) {
        if(nowY < 0 || nowX < 0 || nowY >= yHeight || nowX >= xWidth || check[nowY][nowX]) return;
        if(oldGraph[nowY][nowX] == 0){
            newGraph[prevY][prevX] = Math.max(0, newGraph[prevY][prevX] - 1);
            return;
        }

        check[nowY][nowX] = true;

        for(int i = 0; i < 4; i++){
            change(nowY + dy[i], nowX + dx[i], nowY, nowX);
        }
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
        StringBuilder stb = new StringBuilder("\n");
        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                stb.append(newGraph[y][x]).append(" ");
            }
            stb.append("\n");
        }
        System.out.println(stb);
    }
}

class MainA2573 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int yHeight = tmp[0]; int xWidth = tmp[1];

        int[][] graph = new int[yHeight][xWidth];

        for(int y = 0; y < yHeight; y++)
            graph[y] = strToIntArr(br.readLine());

        System.out.println(new 빙산_2573_2(yHeight, xWidth, graph).getAns());

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