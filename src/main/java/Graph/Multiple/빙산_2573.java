package Graph.Multiple;

import java.io.*;
import java.util.regex.Pattern;

/*문제요약
빙산의 칸 높이는 일년마다 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다.
단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다.
이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램
-> 몇개의 군집이 있는지를 dfs/bfs로 체크

어디서 초기화를 해줘야 할지, 재사용하는 변수 단위를 잘 체크해야 함
*/
class 빙산_2573 {
    static int yHeight, xWidth;
    static int[][] graph;
    static boolean[][] check;
    static boolean[][] isIceberg;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmp = strToIntArray(br.readLine());
        yHeight = tmp[0]; xWidth = tmp[1];

        graph = new int[yHeight][xWidth]; // 각 스테이지의 초기 정보
        isIceberg = new boolean[yHeight][xWidth]; // graph의 불린배열 :: 0 -> false 1~ -> true
        boolean[][] visited; // 방문 true == 원래는 0이 아니었다.

        int maxIteration = 0;
        for(int y = 0; y < yHeight; y++) {
            graph[y] = strToIntArray(br.readLine());

            for(int x = 0; x < xWidth; x++){
                if(graph[y][x] != 0)
                    isIceberg[y][x] = true;
            }
        }

        int minus;
        int nextY, nextX;
        boolean allZero;
        int it = 0;

        while(true) {
            int area = 0;
            allZero = true;
            check = new boolean[yHeight][xWidth];

            for(int y = 0; y < yHeight; y++) {
                for (int x = 0; x < xWidth; x++) {
                    if(graph[y][x] != 0)
                        allZero = false;

                    if(isIceberg[y][x] && !check[y][x]) {
                        dfs(new Point(y, x));
                        area++;
                    }
                }
            }
            if(area >= 2){
                System.out.println(it);
                return;
            } else if(allZero){
                System.out.println("0");
                return;
            }

            visited = new boolean[yHeight][xWidth];

            for(int y = 0; y < yHeight; y++){
                for (int x = 0; x < xWidth; x++) {
                    // 이미 방문되지 않았고, iceberg인 곳
                    if(!visited[y][x] && isIceberg[y][x]) {
                        minus = 0;
                        for (int i = 0; i < 4; i++) {
                            nextY = y + dy[i];
                            nextX = x + dx[i];
                            if (!(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth)
                                    && !isIceberg[nextY][nextX] && !visited[nextY][nextX]){
                                minus++;
                            }
                        }

                        if(graph[y][x] <= minus) {
                            graph[y][x] = 0;
                            isIceberg[y][x] = false;
                        } else
                            graph[y][x] = graph[y][x] - minus;

                        visited[y][x] = true;
                    }
                }
            }
            it++;
        }
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static void dfs(Point now) {
        if(now.y < 0 || now. x < 0 || now.y >= yHeight || now.x >= xWidth
                || check[now.y][now.x] || graph[now.y][now.x] == 0)
            return;

        check[now.y][now.x] = true;

        for(int i = 0; i < 4; i++)
            dfs(new Point(now.y + dy[i], now.x + dx[i]));
    }

    static void display(int[][] arr) {
        System.out.println("display called");
        for (int[] i : arr) {
            for (int x : i) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
        System.out.println("display finish");
    }

    private static class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
/* Testcase
5 5
10 9 9 8 0
0 10 10 9 10
10 13 3 10 10
15 14 13 12 11
9 8 9 14 0
19
*/