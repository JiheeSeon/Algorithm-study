package Resolve;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class Main2667Resolve {
    static int mapSize;
    static int aptNum = 0;

    static int[][] visited;
    static int[][] isInQueue;
    static int[][] graph;
    static int[] res;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        mapSize = Integer.parseInt(br.readLine());
        visited = new int[mapSize][mapSize];
        graph = new int[mapSize][mapSize];

        int i, j;

        for (i = 0; i < mapSize; i++)
            graph[i] = processInput(mapSize);

        for (i = 0; i < mapSize; i++) {
            for (j = 0; j < mapSize; j++) {
                /*마킹된 단지거나(이미 방문), 유효하지 않은 노드의 경우*/
                if (visited[i][j] == 0 && graph[i][j] != 0)
                    dfs(i, j, ++aptNum);
            }
        }

        res = new int[aptNum];

        for (i = 0; i < mapSize; i++) {
            for (j = 0; j < mapSize; j++) {
                if (visited[i][j] != 0)
                    res[visited[i][j] - 1]++;
            }
        }
        Arrays.sort(res);

        stb.append(aptNum).append("\n");

        for (i = 0; i < aptNum; i++)
            if (res[i] != 0)
                stb.append(res[i]).append("\n");

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static int[] processInput(int limitSize) throws IOException {
        return Pattern.compile("").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(limitSize).toArray();
    }

    static void dfs(int x, int y, int apt) {
        /* 밑에서 재귀로 현재 위치에서 갈 수 있는 건 다 호출 > 앞에서 막아줘야 함 */
        if (x < 0 || y < 0 || x >= mapSize || y >= mapSize) return;
        if (visited[x][y] != 0) return;
        if (graph[x][y] == 0) return;

        visited[x][y] = apt;

        /* 이후 방문 가능한 노드에 대해 dfs 실행 */
        for (int i = 0; i < 4; i++)
            dfs(x + dx[i], y + dy[i], apt);
    }

    static void bfs(){
        Queue<Point> willVisit = new LinkedList<>();
        willVisit.add(new Point(0, 0));
        isInQueue[0][0] = graph[0][0];

        Point visiting;
        int nextX, nextY;

        while(!willVisit.isEmpty()){
            visiting = willVisit.poll();
        }
    }

    static private class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}