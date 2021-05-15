package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

class 녹색옷입은애가젤다지_4485 {
    int vertexN;
    int[][] graph;
    int[][] mCost;

    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public 녹색옷입은애가젤다지_4485(int vertexN, int[][] graph) {
        this.vertexN = vertexN;
        this.graph = graph;
        mCost = new int[vertexN][vertexN];
    }

    int solution() {
        PriorityQueue<Edge4485> pq = new PriorityQueue<>();
        pq.offer(new Edge4485(0, 0, graph[0][0]));
        for(int[] c : mCost) Arrays.fill(c, Integer.MAX_VALUE);
        mCost[0][0] = graph[0][0];

        Edge4485 now;
        int nextY, nextX;
        while (!pq.isEmpty()) {
            now = pq.poll();

            for (int i = 0; i < 4; i++) {
                nextY = now.y + dy[i];
                nextX = now.x + dx[i];

                if(nextX < 0 || nextY < 0 || nextY >= vertexN || nextX >= vertexN) continue;

                if (mCost[nextY][nextX] > mCost[now.y][now.x] + graph[nextY][nextX]) {
                    mCost[nextY][nextX] = mCost[now.y][now.x] + graph[nextY][nextX];
                    pq.add(new Edge4485(nextY, nextX, mCost[nextY][nextX]));
                }
            }
        }

        return mCost[vertexN - 1][vertexN - 1];
    }
}
class MainA4485{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        int N; int idx = 1;

        int[][] graph;
        while((N = Integer.parseInt(br.readLine())) != 0){
            graph = new int[N][N];
            for (int i = 0; i < N; i++) {
                graph[i] = splitIntoIntArray(br.readLine());
            }
            stb.append("Problem ").append(idx++).append(": ").append(new 녹색옷입은애가젤다지_4485(N, graph).solution()).append("\n");
        }

        System.out.print(stb);
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
class Edge4485 implements Comparable<Edge4485>{
    int y, x, weight;

    public Edge4485(int y, int x, int weight) {
        this.y = y;
        this.x = x;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge4485 o) {
        return weight == o.weight ? Integer.compare(y, o.y) : Integer.compare(weight, o.weight);
    }
}