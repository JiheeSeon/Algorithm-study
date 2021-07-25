package Tree.MST;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 전기가부족해_10423 {
    int V;
    ArrayList<KruskalEdgeIW> edges;
    Set<Integer> powerPlants;
    int[] parent;

    public 전기가부족해_10423(int v, ArrayList<KruskalEdgeIW> edges, Set<Integer> powerPlants) {
        V = v;
        this.edges = edges;
        this.powerPlants = powerPlants;
        parent = IntStream.rangeClosed(0, V).toArray();
    }

    int solve() {
        int ans = 0;

        int vCnt = powerPlants.size();
        PriorityQueue<KruskalEdgeIW> pq = new PriorityQueue<>(edges);
        KruskalEdgeIW e;
        while(!pq.isEmpty() && vCnt < V){
            e = pq.poll();

            if(!union(e.v1, e.v2)) continue;

            vCnt++;
            ans += e.w;
        }

        return ans;
    }

    boolean union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return false;
        if(powerPlants.contains(pA) && powerPlants.contains(pB)) return false;

        if(powerPlants.contains(pA)) parent[pB] = pA;
        else if(powerPlants.contains(pB)) parent[pA] = pB;
        else {
            if (pA < pB) parent[pB] = pA;
            else parent[pA] = pB;
        }

        return true;
    }

    int find(int a) {
        return parent[a] == a ? a : (parent[a] = find(parent[a]));
    }
}

class MainA10423 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1]; int K = tmp[2];

        Set<Integer>powerPlants = Arrays.stream(InputProcessor.strToIntArr(br.readLine())).boxed().collect(Collectors.toCollection(HashSet::new));
        ArrayList<KruskalEdgeIW> edges = new ArrayList<>();
        for(int e = 0; e < E; e++){
            tmp = InputProcessor.strToIntArr(br.readLine());
            edges.add(new KruskalEdgeIW(tmp[0], tmp[1], tmp[2]));
        }

        System.out.println(new 전기가부족해_10423(V, edges, powerPlants).solve());
    }
}