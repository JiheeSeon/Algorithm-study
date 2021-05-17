package Graph.ShortestPath.BellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

/*유의할 점
1. 계속 음수로 빼다보면 int 범위를 넘어설 수 있음
-> INF를 Long으로 지정해주어야 함.
2. 기존에 무한대였던 애에 값을 더해서 비교하는 일이 없도록 함.
-> 다익스트라에서 신경쓰지 않았던 부분
   왜냐하면 다익스트라는 시작정점을 받아서 그로부터 시작하므로
   dist값이 0이어서 무한대에 무언가를 더하는 일이 없음.
   즉, 벨만포드는 EdgeList를 토대로 돌기 때문에 start 정점 관련 간선이 나오리라는 보장이 없음.
-> 처음엔 sort를 했지만 이 역시 정점이 1이 아닌 경우 커버하기 어려움.
*/
class 타임머신_11657 {
    static final long INF = Long.MAX_VALUE; // long으로 지정

    ArrayList<EdgeA11657> edges;
    int V; long[] dist;

    public 타임머신_11657(ArrayList<EdgeA11657> edges, int V) {
        this.edges = edges;
        this.V = V;

        dist = new long[V + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
    }

    String solution() {
        boolean isUpdated = false;

        outerLoop:
        for(int v = 1; v <= V; v++){
            isUpdated = false;

            for(EdgeA11657 edge: edges){
                if(dist[edge.S] != INF && dist[edge.E] > dist[edge.S] + edge.W){
                    dist[edge.E] = dist[edge.S] + (long)edge.W;
                    isUpdated = true;

                    if(v == V) break outerLoop;
                }
            }
            if(!isUpdated) break;
        }

        if(isUpdated) return "-1";

        StringBuilder stb = new StringBuilder();
        for(int i = 2; i <= V; i++)
            stb.append(dist[i] == INF ? -1 : dist[i]).append("\n");

        return stb.toString();
    }
}

class MainA11657{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = splitIntoIntArray(br.readLine());
        int V = tmp[0]; int E = tmp[1];

        ArrayList<EdgeA11657> edges = new ArrayList<>();
        for(int e = 0; e < E; e++)
            edges.add(new EdgeA11657(splitIntoIntArray(br.readLine())));

        System.out.print(new 타임머신_11657(edges, V).solution());
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
class EdgeA11657 implements Comparable<EdgeA11657> {
    int S, E, W;

    public EdgeA11657(int[] sew) {
        S = sew[0];
        E = sew[1];
        W = sew[2];
    }

    @Override
    public int compareTo(EdgeA11657 o) {
        if(S == o.S){
            return E == o.E ? Integer.compare(W, o.W) : Integer.compare(E, o.E);
        } else return Integer.compare(S, o.S);
    }
}
/*
6 16
6 3 9
6 4 5
4 1 4
3 6 9
4 3 1
5 3 3
4 6 5
2 3 7
3 5 3
3 4 1
1 3 -2
1 4 4
2 1 8
1 2 8
3 2 7
2 5 -2


3 2
2 3 -2
3 2 -2

-1
-1

4 3
1 2 1
3 4 -1
4 3 -1

1
-1
-1

2 3
1 2 3
1 2 2
1 2 1


3 1
2 3 -10000

-1
-1


4 5
1 4 3
4 2 4
2 3 -4
3 4 -2
4 3 3

-1


3 2
2 3 -1
3 2 -1

-1
-1

3 3
1 2 3
2 1 -1000
2 1 5

 -1


2 2
1 2 -1
2 1  -1

-1
*/