package Graph.Archive.BDFS;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

/*
2667 단지번호붙이기
전형적인 연결요소 개수 체크하는 문제

DFS로 풀 때 연결요소마다의 개수를 세는 cnt 인자에 주목
다음으로 갈 수 있는 경로에 대해 dfs 호출하고,
진짜 갈 수 있는 곳이면 다음 호출한 것들 (하위 콜스택)의 cnt 값을 수집, 정리해서 반환
갈 수 없는 곳이면 원래 cnt 값을 그대로 반환
*/

class 단지번호붙이기_2667 {
    int yHeight, xWidth;
    int[][] graph;
    int[][] check;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 단지번호붙이기_2667(int yHeight, int xWidth, int[][] graph, int[][] check){
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.graph = graph;
        this.check = check;
    }

    public int solution_dfs(int currY, int currX, int num, int cnt){
        if(currY < 0 || currX < 0 || currY >= yHeight || currX >= xWidth
                || check[currY][currX] != 0 || graph[currY][currX] == 0) return cnt;

        check[currY][currX] = num;

        cnt++;
        for(int i = 0; i < 4; i++)
            cnt = solution_dfs(currY + dy[i], currX + dx[i], num, cnt);

        return cnt;
    }

    public int solution_bfs(int startY, int startX, int num) {
        Queue<Point> q = new LinkedList<Point>();
        q.add(new Point(startY, startX));
        check[startY][startX] = num;

        Point now;
        int nextY, nextX;

        int cnt = 1;

        while(!q.isEmpty()){
            now = q.poll();

            for(int i = 0; i < 4; i++){
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth
                        || check[nextY][nextX] != 0 || graph[nextY][nextX] == 0) continue;

                check[nextY][nextX] = num;
                q.add(new Point(nextY, nextX));
                cnt++;
            }
        }

        return cnt;
    }

    private class Point{
        int y, x;

        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}


class MainA2667 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int yHeight = Integer.parseInt(br.readLine());
        int xWidth = yHeight;

        int[][] graph = new int[yHeight][xWidth];
        int[][] check = new int[yHeight][xWidth];

        for(int y = 0; y < yHeight; y++)
            graph[y] = strToIntArr(br.readLine(), "");

        단지번호붙이기_2667 solution = new 단지번호붙이기_2667(yHeight, xWidth, graph, check);

        int num = 0;
        ArrayList<Integer> houseN = new ArrayList<Integer>();

        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                if(check[y][x] == 0 && graph[y][x] == 1)
                    houseN.add(solution.solution_dfs(y, x, ++num, 0));
                // houseN.add(solution.solution_bfs(y, x, ++num));
            }
        }

        StringBuilder stb = new StringBuilder();

        stb.append(houseN.size()).append("\n");
        houseN.stream().sorted().forEach(x -> stb.append(x).append("\n"));
        System.out.print(stb);
    }
    static int[] strToIntArr(String s, String delimiter){
        return Pattern.compile(delimiter).splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}