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
*/


class BellmanFord {
    final static int INF = 10000000;
    int V, E, W;
    int[] dist;
    ArrayList<BellmanFordEdge>[] graph;

    public BellmanFord(int v, int e, int w, ArrayList<BellmanFordEdge>[] graph) {
        V = v;
        E = e;
        W = w;
        this.graph = graph;
        dist = new int[V + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0; // start 정점에 대한 처리
    }

    String solve() {
        boolean isUpdated = false;

        outerLoop:
        for (int v = 0; v < V; v++) {
            isUpdated = false;

            // 이렇게 하면 틀림
            for (BellmanFordEdge connected : graph[v]) {
                if (dist[connected.vertex] > dist[v] + connected.weight) {
                    dist[connected.vertex] = dist[v] + connected.weight;
                    isUpdated = true;
                    if (v == V - 1) break outerLoop;
                }
            }
            if(!isUpdated) break;
        }

        return isUpdated ? "YES" : "NO";
    }
}

class BellmanFordEdge {
    int vertex, weight;

    public BellmanFordEdge(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

class BellmanFordApp{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] tmp;
        int V, E, W;
        ArrayList<BellmanFordEdge>[] graph;
        StringBuilder stb = new StringBuilder();

        while (T-- > 0) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            V = tmp[0]; E = tmp[1]; W = tmp[2];

            graph = new ArrayList[V + 1];
            for (int v = 1; v <= V; v++) graph[v] = new ArrayList<>();

            for(int e = 0; e < E; e++) {
                tmp = InputProcessor.strToIntArr(br.readLine());
                graph[tmp[0]].add(new BellmanFordEdge(tmp[1], tmp[2]));
                graph[tmp[1]].add(new BellmanFordEdge(tmp[0], tmp[2]));
            }

            for (int w = 0; w < W; w++) {
                tmp = InputProcessor.strToIntArr(br.readLine());
                graph[tmp[0]].add(new BellmanFordEdge(tmp[1], -tmp[2]));
            }
            stb.append(new BellmanFord(V, E, W, graph).solve()).append("\n");
        }

        System.out.print(stb);
    }
}