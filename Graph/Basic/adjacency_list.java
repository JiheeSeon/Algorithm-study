package Basic;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

class MainAdjacencyList {
    static int numOfVertex;
    static int numOfEdge;

    static int[] input;
    static boolean[] visited;
    static ArrayList<Point>[] graph; // i번째 노드와 연결된 정점을 링크드리스트 형태로 가짐

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stb = new StringBuilder();

    private static class Point {
        int dst, weight;

        public Point(int dst, int weight) {
            this.dst = dst;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        /* weight 있는 경우
         * 5 7
         * 1 2 1
         * 1 5 5
         * 2 3 9
         * 2 4 4
         * 3 1 2
         * 3 4 3
         * 4 5 7
         * */

        input = getInputAndSplit();
        numOfVertex = input[0];
        numOfEdge = input[1];
        visited = new boolean[numOfVertex];
        graph = (ArrayList<Point>[]) new ArrayList[numOfVertex];

        int u, v, w;
        for (int i = 0; i < numOfEdge; i++) {
            input = getInputAndSplit();
            u = input[0] - 1; v = input[1] - 1; w = input[2];
            graph[u].add(new Point(v, w));
        }

        bw.write(stb.toString());
    }

    static int[] getInputAndSplit() throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }

    static void dfs(int srcVertex) { // x를 방문했고, log처럼 함수 콜스택에 순차적으로 쌓임
        visited[srcVertex] = true; // 해당 정점과 연결된 정점에 대해 방문 여부 확인하고자
        stb.append(srcVertex + 1).append(" "); //방문 기록 출력

        for (Point dstVertex : graph[srcVertex]){
            if (!visited[dstVertex.dst])
                dfs(dstVertex.dst);
        }
    }

    static void bfs(int firstVertex){

    }

}