package Graph.ShortestPath.FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

class 맥주마시면서걸어가기_9205 {
    int V;
    int[][] graph;

    final static int INF = 987654321;

    public 맥주마시면서걸어가기_9205(int v, int[][] graph) {
        V = v;
        this.graph = graph;
    }

    String solution() {
        int[][] check = new int[V][V];

        for(int i = 0; i < V; i++)
            check[i] = graph[i].clone();

        int updatedD;
        for(int i = 0; i < V; i++){
            for(int s = 0; s < V; s++){
                for(int e = 0; e < V; e++) {
                    if (s == e) continue;

                    updatedD = check[s][i] + check[i][e];
                    if (check[s][i] != INF && check[i][e] != INF && check[s][e] > updatedD){
                        check[s][e] = check[s][i] + check[i][e];
                    }
                }
            }
        }

        return check[0][V - 1] == INF ? "sad\n" : "happy\n";
    }
}
class MainA9205{
    final static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] tmp;
        int V;
        int convenienceN;
        PointA9205[] points;
        int[][] graph;

        StringBuilder stb = new StringBuilder();

        while(T-- > 0){
            convenienceN = Integer.parseInt(br.readLine());
            V = convenienceN + 2;
            points = new PointA9205[V];

            tmp = splitIntoIntArray(br.readLine());
            points[0] = new PointA9205(tmp[1], tmp[0]);

            for(int i = 0; i < convenienceN; i++) {
                tmp = splitIntoIntArray(br.readLine());
                points[i + 1] = new PointA9205(tmp[1], tmp[0]);
            }

            tmp = splitIntoIntArray(br.readLine());
            points[convenienceN + 1] = new PointA9205(tmp[1], tmp[0]);

            graph = makeGraph(points, V);
            stb.append(new 맥주마시면서걸어가기_9205(V, graph).solution());
        }
        System.out.print(stb);
    }

    static int[][] makeGraph(PointA9205[] points, int V) {
        int[][] graph = new int[V][V];

        int dist;
        for(int i = 0; i < V; i++){
            for(int j = 0; j < V; j++){
                if(i == j) graph[i][j] = 0;
                else {
                    dist = Math.abs(points[j].y - points[i].y) + Math.abs(points[j].x - points[i].x);
                    graph[i][j] = dist > 1000 ? INF : dist;
                }
            }
        }

        return graph;
    }

    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
class PointA9205{
    int y, x;

    public PointA9205(int y, int x) {
        this.y = y;
        this.x = x;
    }
}