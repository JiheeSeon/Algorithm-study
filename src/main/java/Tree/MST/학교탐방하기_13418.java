package Tree.MST;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class 학교탐방하기_13418 {
    int V;
    KruskalEdgeIW firstEdge;
    ArrayList<KruskalEdgeIW> forGood;
    ArrayList<KruskalEdgeIW> forBad;
    int[] parent;

    public 학교탐방하기_13418(int v, KruskalEdgeIW firstEdge, ArrayList<KruskalEdgeIW> forGood, ArrayList<KruskalEdgeIW> forBad) {
        V = v;
        this.firstEdge = firstEdge;
        this.forGood = forGood;
        this.forBad = forBad;
    }

    int solve() {
        return getFatigueScore(-firstEdge.w, forBad) - getFatigueScore(firstEdge.w, forGood);
    }

    int getFatigueScore(int firstWeight, ArrayList<KruskalEdgeIW> edges) {
        int ret = firstWeight;

        parent = IntStream.rangeClosed(0, V).toArray();

        PriorityQueue<KruskalEdgeIW> pq = new PriorityQueue<>(edges);

        int cnt = 0;
        KruskalEdgeIW e;
        while (!pq.isEmpty() && cnt < V - 1) {
            e = pq.poll();

            ret += e.w;
            cnt++;
        }

        return ret * ret;
    }

    boolean union(int a, int b){
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

class MainA13418 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];

        ArrayList<KruskalEdgeIW> forGood = new ArrayList<>();
        ArrayList<KruskalEdgeIW> forBad = new ArrayList<>();

        tmp = InputProcessor.strToIntArr(br.readLine());
        KruskalEdgeIW firstEdge = new KruskalEdgeIW(tmp[0], tmp[1], tmp[2]);

        for (int e = 0; e < E; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            forGood.add(new KruskalEdgeIW(tmp[0], tmp[1], tmp[2]));
            forBad.add(new KruskalEdgeIW(tmp[0], tmp[1], -tmp[2]));
        }

        System.out.println(new 학교탐방하기_13418(V, firstEdge, forGood, forBad).solve());
    }
}