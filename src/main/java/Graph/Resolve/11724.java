package Graph.Resolve;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

class Main11724Resolve {
    static int numOfVertex;
    static int numOfEdge;

    static int[] visited;
    static ArrayList<Integer>[] graph;

    static int[] temp;
    static int result;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        temp = getInputAndSplit();
        numOfVertex = temp[0];
        numOfEdge = temp[1];
        graph = (ArrayList<Integer>[]) new ArrayList[numOfVertex];
        visited = new int[numOfVertex];

        int u, v;

        for (int i = 0; i < numOfVertex; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < numOfEdge; i++) {
            temp = getInputAndSplit();
            u = temp[0] - 1;  v = temp[1] - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        for (ArrayList<Integer> i : graph)
            Collections.sort(i);

        for (int i = 0; i < numOfVertex; i++) {
            if (visited[i] == 0) {
                dfs(i);
                result++;
            }
        }

        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }

    static void dfs(int srcVertex) {
        visited[srcVertex] = 1;

        for (int i : graph[srcVertex]) {
            if (visited[i] == 0)
                dfs(i);
        }
    }

    static int[] getInputAndSplit() throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }
}