package Resolve;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

class Main1707Resolve {
    static int numOfVertex;
    static int numOfEdge;

    static int[] visited;
    static ArrayList<Integer>[] graph;

    static int[] temp;
    static String result;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int numOfTestcase = Integer.parseInt(br.readLine());
        int u, v;

        for (int i = 0; i < numOfTestcase; i++) {
            temp = getInputAndSplit();
            numOfVertex = temp[0];
            numOfEdge = temp[1];
            visited = new int[numOfVertex];
            graph = (ArrayList<Integer>[]) new ArrayList[numOfVertex];

            /* Declaration of each vertex's arraylist */
            for (int j = 0; j < numOfVertex; j++)
                graph[j] = new ArrayList<Integer>();

            for (int l = 0; l < numOfEdge; l++) {
                temp = getInputAndSplit();
                u = temp[0] - 1;
                v = temp[1] - 1;
                graph[u].add(v);
                graph[v].add(u);
            }

            for (ArrayList<Integer> vertex : graph)
                Collections.sort(vertex);

            result = "YES";

            for (int k = 0; k < numOfVertex; k++) {
//                visited = new int[numOfVertex];
                if (visited[k] == 0)
                    dfs(k, 1);
            }


            for (int n = 0; n < numOfVertex; n++) {
                for (int m : graph[n]) {
                    if (visited[n] == visited[m]) {
                        result = "NO";
                        break;
                    }
                }
            }

            stb.append(result).append("\n");
        }
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static int[] getInputAndSplit() throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }

    static void dfs(int srcVertex, int visitGroup) {
        visited[srcVertex] = visitGroup;

        for (int vertex : graph[srcVertex]) {
            if (visited[vertex] == 0)
                dfs(vertex, 3 - visitGroup);
            else if (visited[vertex] == visitGroup)
                result = "NO";
        }
    }
}