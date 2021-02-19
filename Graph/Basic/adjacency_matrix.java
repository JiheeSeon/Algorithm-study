package Basic;

import java.io.*;
import java.util.regex.Pattern;

class MainAdjacencyMatrix {
    static int numOfVertex;
    static int numOfEdge;

    static int[] input;

    static int[] check;
    static int[][] graph;
    static int[][] weight; // 간선의 가중치에 0이 포함되는 경우 구분을 위해 가중치 배열 별도 생성

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        /* case 1.
        5
        1 0 1 0 1
        0 0 1 1 1
        1 1 0 1 0
        0 1 1 0 1
        1 1 0 1 0
         */

        numOfVertex = Integer.parseInt(br.readLine());
        graph = new int[numOfVertex][numOfVertex];
        check = new int[numOfVertex];

        for (int i = 0; i < numOfVertex; i++)
            graph[i] = getInputAndSplit();

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
        graph = new int[numOfVertex][numOfVertex];
        check = new int[numOfVertex];

        int u, v, w;

        for (int i = 0; i < numOfEdge; i++) {
            input = getInputAndSplit();
            u = input[0] - 1;
            v = input[1] - 1;

            //bi-directional case
            graph[u][v] = graph[v][u] = 1;

            // if there's weight on last
            w = input[2];

            // if weight cannot be 0
            graph[u][v] = graph[v][u] = w;

            /*if weight can be 0*/
            weight[u][v] = weight[v][u] = w;
        }


    }

    static int[] getInputAndSplit() throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }

    static void dfs(int n) {

    }
}