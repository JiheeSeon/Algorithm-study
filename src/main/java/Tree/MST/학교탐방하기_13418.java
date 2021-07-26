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
    ArrayList<KruskalEdgeIW> forGood;
    ArrayList<KruskalEdgeIW> forBad;
    int[] parent;

    public 학교탐방하기_13418(int v,  ArrayList<KruskalEdgeIW> forGood, ArrayList<KruskalEdgeIW> forBad) {
        V = v;
        this.forGood = forGood;
        this.forBad = forBad;
    }

    long solve() {
        return getFatigueScore(forBad) - getFatigueScore(forGood);
    }

    long getFatigueScore(ArrayList<KruskalEdgeIW> edges) {
        long ret = 0;

        parent = IntStream.rangeClosed(0, V).toArray();

        PriorityQueue<KruskalEdgeIW> pq = new PriorityQueue<>(edges);

        int cnt = 0;
        KruskalEdgeIW e;

        while (!pq.isEmpty() && cnt < V - 1) {
            e = pq.poll();
            if(!union(e.v1, e.v2)) continue;

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
        int V = tmp[0] + 1; int E = tmp[1] + 1;

        ArrayList<KruskalEdgeIW> forGood = new ArrayList<>();
        ArrayList<KruskalEdgeIW> forBad = new ArrayList<>();

        /*문제의 조건을 잘 읽자 ^*^*/
        for (int e = 0; e < E; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            forGood.add(new KruskalEdgeIW(tmp[0], tmp[1], 1 - tmp[2]));
            forBad.add(new KruskalEdgeIW(tmp[0], tmp[1], tmp[2] - 1));
        }

        System.out.println(new 학교탐방하기_13418(V, forGood, forBad).solve());
    }
}