package Graph.Basic;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class MainAdjacencyMatrix {
    static int numOfVertex;
    static int numOfEdge;
    static int startVertex;

    static int[] input;

    static boolean[] visited;
    static boolean[] isInQueue;

    static int[][] graph;
    static int[][] weight; // 간선의 가중치에 0이 포함되는 경우 구분을 위해 가중치 배열 별도 생성

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        /* case 1.
        5
        1 0 1 0 1
        0 0 1 1 1
        1 1 0 1 0
        0 1 1 0 1
        1 1 0 1 0
         */

//        numOfVertex = Integer.parseInt(br.readLine());
//        graph = new int[numOfVertex][numOfVertex];
//        visited = new boolean[numOfVertex];
//
//        for (int i = 0; i < numOfVertex; i++)
//            graph[i] = getInputAndSplit();

        /* case 2.
         *
         * 5 7
         * 1 2
         * 1 5
         * 2 3
         * 2 4
         * 3 1
         * 3 4
         * 4 5
         * */

        input = getInputAndSplit();
        numOfVertex = input[0];
        numOfEdge = input[1];
        startVertex = input[2] - 1;
        graph = new int[numOfVertex][numOfVertex];
        visited = new boolean[numOfVertex];
        isInQueue = new boolean[numOfVertex];

        int u, v, w;

        for (int i = 0; i < numOfEdge; i++) {
            input = getInputAndSplit();
            u = input[0] - 1;
            v = input[1] - 1;

            //bi-directional case
            graph[u][v] = graph[v][u] = 1;

//            // if there's weight on last
//            w = input[2];
//
//            // if weight cannot be 0
//            graph[u][v] = graph[v][u] = w;
//
//            /*if weight can be 0*/
//            weight[u][v] = weight[v][u] = w;
        }

        dfs(startVertex);
        stb.append("\n");
        bfs();

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static int[] getInputAndSplit() throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }

    static void dfs(int srcVertex) {
        visited[srcVertex] = true;
        stb.append(srcVertex + 1).append(" ");

        for (int i = 0; i < numOfVertex; i++) {
            if (graph[srcVertex][i] == 1 && !visited[i])
                dfs(i);
        }
    }

    static void bfs() {
        Queue<Integer> willVisit = new LinkedList<>();

        willVisit.add(startVertex);
        isInQueue[startVertex] = true;

        int visiting;

        while (!willVisit.isEmpty()) {
            visiting = willVisit.poll();
            stb.append(visiting + 1).append(" ");

            for (int i = 0; i < numOfVertex; i++) {
                if (graph[visiting][i] == 1 && !isInQueue[i]) { // 연결된 거
                    willVisit.add(i); // 큐에 넣어
                    isInQueue[i] = true; // 큐에 들어간다 라고 표시
                }
            }
        }
    }
}