package CramForCodingTest.SampleCode;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Prim {
    int V, E;
    ArrayList<PrimEdge>[] graph;

    public Prim(int v, int e, ArrayList<PrimEdge>[] graph) {
        V = v;
        E = e;
        this.graph = graph;
    }

    int solve() {
        boolean[] selected = new boolean[V + 1];

        PriorityQueue<PrimEdge> pq = new PriorityQueue<>();

        selected[1] = true;
        pq.addAll(graph[1]);

        int loopCnt = 0;
        int ans = 0;
        PrimEdge now;

        while (loopCnt < V - 1) {
            if(pq.isEmpty()) break;

            now = pq.poll();

            // cycle은 이미 선택된 점으로 다시 가는 것을 의미
            if(selected[now.vertex]) continue;

            selected[now.vertex] = true;
            ans += now.weight;
            pq.addAll(graph[now.vertex]);
            loopCnt++;
        }

        return ans;
    }
}

class PrimEdge implements Comparable<PrimEdge>{
    int vertex, weight;

    public PrimEdge(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(PrimEdge o) {
        return Integer.compare(weight, o.weight);
    }
}

class PrimApp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];

        ArrayList<PrimEdge>[] graph = new ArrayList[V + 1];
        for(int v = 1; v <= V; v++) graph[v] = new ArrayList<>();

        for (int e = 0; e < E; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(new PrimEdge(tmp[1], tmp[2]));
            graph[tmp[1]].add(new PrimEdge(tmp[0], tmp[2])); // 9% wrong -> right
        }

        System.out.println(new Prim(V, E, graph).solve());
    }
}