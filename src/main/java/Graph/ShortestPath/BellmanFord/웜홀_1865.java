package Graph.ShortestPath.BellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/*
타임머신 vs 웜홀 문제

타임머신 문제의 특징
- 시작점이 정해져있음. 최단경로 구하는 문제
- 이미 도달할 수 없었던 정점(INF값으로 세팅)의 경우 dist를 업데이트하면 안됨.

웜홀의 특징
- 임의의 시작점에 대한 음의 사이클 존재 여부 문제
- 시작점으로부터 도달 가능한 정점이 아니었어도 (INF) dist 업데이트해야함.
- 결국은 도는 횟수로 음의 사이클만 체크

공통점
- 음수사이클인 경우는 isUpdated가 true인 경우로 체크 가능
- 둘다 INF 범위 조심해야 함.
*/

class 웜홀_1865 {
    final static int INF = 1000000000; //범위 조심, Integer.MAX_VALUE로 하면 틀림, 단절되었다는 표시
    int V; int[] dist;
    ArrayList<EdgeA1865> edges;

    public 웜홀_1865(int v, ArrayList<EdgeA1865> edges) {
        V = v;
        this.edges = edges;
        dist = new int[V + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        //임의의 한 지점에서 출발하는 경우를 모두 살피지 않아도
        // 사이클 존재유무는 임의의 출발점만 세팅해줘도 됨.
    }

    String solution() {
        boolean isUpdated = false;

        outerLoop:
        for (int v = 1; v <= V; v++) {
            isUpdated = false;

            for (EdgeA1865 edge : edges) {
                /*
                dist[edge.S] != INF 라고
                이전 정점이 단절된 정점일 경우를 배제하면
                단절된 정점끼리 cycle을 형성한 경우를 체크할 수 없음.
                */
                if(dist[edge.E] > dist[edge.S] + edge.T){
                    dist[edge.E] = dist[edge.S] + edge.T;
                    isUpdated = true;

                    if(v == V) break outerLoop;
                }
            }
            if(!isUpdated) break;
        }
        // isUpdated가 true -> 음수 사이클 존재 -> 출발위치로 돌아올 수 있음.
        return isUpdated ? "YES\n" : "NO\n";
    }
}
class MainA1865{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); int t = 0;

        int[] tmp;
        int V, E, W;
        ArrayList<EdgeA1865> edges;
        StringBuilder stb = new StringBuilder();

        while (t++ < T) {
            tmp = splitIntoIntArray(br.readLine());
            V = tmp[0]; E = tmp[1]; W = tmp[2];

            edges = new ArrayList<>();

            for (int e = 0; e < E; e++) {
                tmp = splitIntoIntArray(br.readLine());
                edges.add(new EdgeA1865(tmp[0], tmp[1], tmp[2]));
                edges.add(new EdgeA1865(tmp[1], tmp[0], tmp[2]));
            }
            for (int w = 0; w < W; w++) {
                tmp = splitIntoIntArray(br.readLine());
                edges.add(new EdgeA1865(tmp[0], tmp[1], -tmp[2]));
            }
            stb.append(new 웜홀_1865(V, edges).solution());
        }
        System.out.print(stb);
    }
    static int[] splitIntoIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
class EdgeA1865{
    int S, E, T;

    public EdgeA1865(int s, int e, int t) {
        S = s;
        E = e;
        T = t;
    }
}