package Resolve;

import java.io.*;
import java.util.regex.Pattern;

class Main10451Resolve {
    static int numOfVertex, numOfEdge;

    static StringBuilder stb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] visited;
    static int[] graph;

    static int result;

    public static void main(String[] args) throws IOException {
        int numOfTestCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < numOfTestCase; i++) {
            result = 0;

            numOfVertex = numOfEdge = Integer.parseInt(br.readLine());
            graph = getInputAndSplit(numOfEdge);
            visited = new int[numOfVertex];

            for (int j = 0; j < numOfVertex; j++) {
                if (visited[j] == 0) {
                    result++;
                    dfs(j, result);
                }
            }

            stb.append(result).append("\n");
        }

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int srcVertex, int group) {
        visited[srcVertex] = group;

        if (visited[graph[srcVertex]]==0)
            dfs(graph[srcVertex], group);
    }

    static int[] getInputAndSplit(int numOfLimit) throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(v -> Integer.parseInt(v) - 1).limit(numOfLimit).toArray();
    }
}