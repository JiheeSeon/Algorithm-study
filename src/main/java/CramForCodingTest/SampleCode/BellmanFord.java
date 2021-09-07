package CramForCodingTest.SampleCode;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
상정한 문제 : negative cycle이 존재하는지 여부
(1865 웜홀 문제 참고)

Bellman Ford (Feat. Dijkstra과의 차이점)
** 모든 edge에 대한 relaxation 임에 주의 **

앞서 dijkstra는 현재 점과 연결된 edge relaxation만 했음.
왜냐하면 앞에서 정해지면 그게 최단경로인게 확실했기 때문

But Bellman Ford의 경우, 음수가중치가 생기면서 계속 edge 간의 상호작용이 생김.
앞에서 정해지는 dist가 최단경로라는 보장이 없어서 계속 바뀜 until 정점의 개수 - 1
좀 더 자세히 말하면,
내부 for문에서 edge를 업데이트하면서 이전 edge로 dist가 변했을 때 이번 edge의 dist 변함.
이러한 edge 상호작용은 총 V - 1번 반복될 때 정상적으로 stable하게 끝남.
(v는 사실상 idx의 역할만 할뿐, 경유점으로서의 역할은 전혀 하지 않는다)
*/

class BellmanFord {
    final static int INF = 10000000;
    int V, E, W;
    int[] dist;
    ArrayList<BellmanFordEdge> edges;

    public BellmanFord(int v, int e, int w, ArrayList<BellmanFordEdge> edges) {
        V = v;
        E = e;
        W = w;
        this.edges = edges;
        dist = new int[V + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0; // start 정점에 대한 처리
    }

    String solve() {
        boolean isUpdated = false;

        outerLoop:
        for (int v = 0; v < V; v++) {
            isUpdated = false;

            for (BellmanFordEdge edge : edges) {
                if (dist[edge.dstV] > dist[edge.srcV] + edge.weight) {
                    dist[edge.dstV] = dist[edge.srcV] + edge.weight;
                    isUpdated = true;
                    if (v == V - 1) break outerLoop; // V번째에서도 변하면 노답
                }
            }
            if(!isUpdated) break;
        }

        return isUpdated ? "YES" : "NO";
    }
}

class BellmanFordEdge {
    int srcV, dstV, weight;

    public BellmanFordEdge(int srcV, int dstV, int weight) {
        this.srcV = srcV;
        this.dstV = dstV;
        this.weight = weight;
    }
}

class BellmanFordApp{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] tmp;
        int V, E, W;
        ArrayList<BellmanFordEdge> edges;
        StringBuilder stb = new StringBuilder();

        while (T-- > 0) {
            edges = new ArrayList<>();

            tmp = InputProcessor.strToIntArr(br.readLine());
            V = tmp[0]; E = tmp[1]; W = tmp[2];

            for(int e = 0; e < E; e++) {
                tmp = InputProcessor.strToIntArr(br.readLine());
                edges.add(new BellmanFordEdge(tmp[0], tmp[1], tmp[2]));
                edges.add(new BellmanFordEdge(tmp[1], tmp[0], tmp[2]));
            }

            for (int w = 0; w < W; w++) {
                tmp = InputProcessor.strToIntArr(br.readLine());
                edges.add(new BellmanFordEdge(tmp[0], tmp[1], -tmp[2]));
            }
            stb.append(new BellmanFord(V, E, W, edges).solve()).append("\n");
        }

        System.out.print(stb);
    }
}