package Graph.ShortestPath.FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

class 케빈베이컨의6단계법칙_1389 {
    int V; int[][] graph;
    final static int INF = 987654321;

    public 케빈베이컨의6단계법칙_1389(int v, int[][] graph) {
        V = v;
        this.graph = graph;
    }

    int solution() {
        int[][] ret = new int[V][V];
        for(int i = 0; i < V; i++){
            for (int j = 0; j < V; j++) {
                if(i == j) ret[i][j] = 0;
                else ret[i][j] = graph[i][j] == 0 ? INF : graph[i][j];
            }
        }

        for(int i = 0; i < V; i++){
            for(int s = 0; s < V; s++){
                for(int e = 0; e < V; e++){
                    if(ret[s][i] != INF && ret[i][e] != INF && ret[s][e] > ret[s][i] + ret[i][e])
                        ret[s][e] = ret[s][i] + ret[i][e];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int minIdx = Integer.MAX_VALUE;
        int sum;
        for(int i = 0; i < V; i++){
            sum = Arrays.stream(ret[i]).sum();
            if(min > sum){
                min = sum;
                minIdx = i + 1;
            }
        }
        return minIdx;
    }
}
class MainA1389{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = splitIntoIntArray(br.readLine());
        int V = tmp[0], E = tmp[1];
        int[][] graph = new int[V][V];
        for (int i = 0; i < E; i++) {
            tmp = splitIntoIntArray(br.readLine());
            graph[tmp[0] - 1][tmp[1] - 1] = 1;
            graph[tmp[1] - 1][tmp[0] - 1] = 1;
        }
        System.out.println(new 케빈베이컨의6단계법칙_1389(V, graph).solution());
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}