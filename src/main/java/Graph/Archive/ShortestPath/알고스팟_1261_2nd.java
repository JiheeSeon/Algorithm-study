package Graph.Archive.ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

/*
문제분석
앞에서는 노드들의 연결상태 및 가중치를 주었다면
여기서는 x, y 좌표 및 그래프의 상태로, 가중치를 알아서 반영
*/

class 알고스팟_1261_2nd {
    int yHeight, xWidth;
    int[][] graph;
    int[][] distance;
    boolean[][] check;

    final static int INF = Integer.MAX_VALUE;
    final static int[] dy = {-1, 1, 0, 0};
    final static int[] dx = {0, 0, -1, 1};

    public 알고스팟_1261_2nd(int yHeight, int xWidth, int[][] graph) {
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.graph = graph;
        distance = new int[yHeight][xWidth];
        check = new boolean[yHeight][xWidth];

        for (int y = 0; y < yHeight; y++)
            Arrays.fill(distance[y], INF);
    }

    void dijkstra() {
        PriorityQueue<Edge_1261_2nd> pq = new PriorityQueue<>();
        distance[0][0] = 0;
        pq.add(new Edge_1261_2nd(0, 0, distance[0][0]));

        Edge_1261_2nd now;
        int nextY, nextX;

        while (!pq.isEmpty()) {
            now = pq.poll();
            if(check[now.dstY][now.dstX]) continue;
            check[now.dstY][now.dstX] = true;

            for(int i = 0; i < 4; i++){
                nextY = now.dstY + dy[i];
                nextX = now.dstX + dx[i];

                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth) continue;

                if(distance[nextY][nextX] > distance[now.dstY][now.dstX] + graph[nextY][nextX]){
                    distance[nextY][nextX] = distance[now.dstY][now.dstX] + graph[nextY][nextX];
                    pq.add(new Edge_1261_2nd(nextY, nextX, distance[nextY][nextX]));
                }
            }
        }
    }

    int getAns() {
        dijkstra();
        return distance[yHeight - 1][xWidth - 1];
    }
}

class Edge_1261_2nd implements Comparable<Edge_1261_2nd>{
    int dstY, dstX;
    int cost;

    public Edge_1261_2nd(int dstY, int dstX, int cost) {
        this.dstY = dstY;
        this.dstX = dstX;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge_1261_2nd o) {
        return Integer.compare(cost, o.cost);
    }
}

class MainA1261_2nd {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine(), " ");
        int xWidth = tmp[0]; int yHeight = tmp[1];

        int[][] graph = new int[yHeight][xWidth];
        for(int y = 0; y < yHeight; y++)
            graph[y] = strToIntArr(br.readLine(), "");

        System.out.println(new 알고스팟_1261_2nd(yHeight, xWidth, graph).getAns());
    }

    static int[] strToIntArr(String s, String delim) {
        return Pattern.compile(delim).splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
