package CramForCodingTest.SampleCode;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Kruskal {
    int V, E;
    PriorityQueue<KruskalEdge> pq;
    int[] parent;

    public Kruskal(int v, int e, PriorityQueue<KruskalEdge> edges) {
        V = v;
        E = e;
        pq = edges;
        parent = IntStream.rangeClosed(0, V).toArray();
    }

    int solve() {
        KruskalEdge now;

        int sum = 0; int cnt = 0;
        while (cnt < V - 1 || !pq.isEmpty()) {
            now = pq.poll();

            if(!union(now.v1, now.v2)) continue;
            sum += now.weight;
            cnt++;
        }
        return sum;
    }

    boolean union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return false;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int a) {
        return parent[a] == a ? a : (parent[a] = find(parent[a]));
    }
}

class KruskalEdge implements Comparable<KruskalEdge>{
    int v1, v2, weight;

    public KruskalEdge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(KruskalEdge o) {
        return Integer.compare(weight, o.weight);
    }
}

class KruskalApp{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];

        PriorityQueue<KruskalEdge> edges = new PriorityQueue<>();
        for (int e = 0; e < E; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            edges.add(new KruskalEdge(tmp[0], tmp[1], tmp[2]));
        }

        System.out.println(new Kruskal(V, E, edges).solve());
    }
}