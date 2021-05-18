package Graph.ShortestPath.FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

class 플로이드_11404 {
    int V;
    int[][] graph;

    final static int INF = 987654321;

    public 플로이드_11404(int v, int[][] graph) {
        V = v;
        this.graph = graph;
    }

    int[][] solution() {
        int[][] graph = new int[V][V];
        for(int i = 0; i < V; i++) {
            for(int j = 0; j < V; j++){
                if(i == j) graph[i][j] = 0;
                else graph[i][j] = (this.graph[i][j] == 0) ? INF : this.graph[i][j];
            }
        }

        for (int i = 0; i < V; i++) {
            for (int s = 0; s < V; s++) {
                for (int e = 0; e < V; e++) {
                    if (graph[s][i] != INF && graph[i][e] != INF && graph[s][e] > graph[s][i] + graph[i][e]) {
                        graph[s][e] = graph[s][i] + graph[i][e];
                    }
                }
            }
        }
        return graph;
    }
}

class MainA11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        int[][] graph = new int[V][V];
        int[] tmp;
        for (int e = 0; e < E; e++) {
            tmp = splitIntoIntArray(br.readLine());
            graph[tmp[0] - 1][tmp[1] - 1] = graph[tmp[0] - 1][tmp[1] - 1] == 0
                    ? tmp[2]
                    : Math.min(graph[tmp[0] - 1][tmp[1] - 1], tmp[2]);
        }

        graph = new 플로이드_11404(V, graph).solution();

        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                stb.append(graph[i][j] == 987654321 ? 0 : graph[i][j]).append(" ");
            }
            stb.append("\n");
        }

        System.out.print(stb);
    }

    static int[] splitIntoIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}