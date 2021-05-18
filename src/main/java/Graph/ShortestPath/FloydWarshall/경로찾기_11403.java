package Graph.ShortestPath.FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class 경로찾기_11403 {
    int V;
    int[][] graph;

    public 경로찾기_11403(int v, int[][] graph) {
        V = v;
        this.graph = graph;
    }

    int[][] solution() {
        for(int i = 0; i < V; i++){
            for(int j = 0; j < V; j++){
                for(int k = 0; k < V; k++){
                    if(graph[j][i] == 1 && graph[i][k] == 1) graph[j][k] = 1;
                }
            }
        }
        return graph;
    }
}
class MainA11403{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        for(int i = 0; i < N; i++)
            graph[i] = splitIntoIntArray(br.readLine());

        int[][] ans = new 경로찾기_11403(N, graph).solution();
        StringBuilder stb = new StringBuilder();
        for(int[] ar: ans){
            for(int i : ar)
                stb.append(i).append(" ");
            stb.append("\n");
        }
        System.out.print(stb);
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
