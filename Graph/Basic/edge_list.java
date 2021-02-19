package Basic;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class MainEdgeList {
    static final int SRC = 0;
    static final int DEST = 1;
    static final int WEIGHT = 2;

    static int numOfVertex, numOfEdge;
    static int[] input;
    static int[][] graph;
    static int[] visited;
    static int[] count;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input = getInputAndSplit(2);
        numOfVertex = input[0];
        numOfEdge = input[1];
        graph = new int[numOfEdge * 2][3];
        visited = new int[numOfVertex];
        count = new int[numOfVertex + 1];

        for (int i = 0; i < numOfEdge; i++) {
            graph[i] = getInputAndSplit(3);
            graph[i + numOfEdge] = new int[]{graph[i][DEST], graph[i][SRC], graph[i][WEIGHT]};
        }

        Arrays.sort(graph, (o1, o2) -> o1[SRC] == o2[SRC] ? Integer.compare(o1[DEST], o2[DEST]) : Integer.compare(o1[0], o2[0]));
        show2DArray(graph);

        for (int i = 0; i < numOfEdge * 2; i++) {
            count[graph[i][SRC]] += 1;
        }

        show1DArray(count);
    }

    static int[] getInputAndSplit(int limitSize) throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(limitSize).toArray();
    }

    static void show1DArray(int[] array1D) throws IOException {
        for (int i = 1; i < array1D.length; i++) {
            stb.append(array1D[i]).append(" ");
        }
        bw.write(stb.toString());
        bw.flush();

        stb = new StringBuilder(); //나갈 때 초기화시켜 여기서 사용한걸 더이상 append하지 않도록
    }

    static void show2DArray(int[][] array2D) throws IOException {
        for (int i = 0; i < array2D.length; i++) {
            for (int j = 0; j < array2D[0].length; j++)
                stb.append(array2D[i][j]).append(" ");
            stb.append("\n");
        }
        bw.write(stb.toString());
        bw.flush();

        stb = new StringBuilder();
    }

    static void dfs() {

    }

    static void bfs(int startVertex) {

    }
}