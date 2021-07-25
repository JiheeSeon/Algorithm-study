package Tree.MST;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
불필요한 간선 제거해주는 작업
*/

class 나만안되는연애_14621 {
    int V;
    ArrayList<PrimEdge>[] graph;

    public 나만안되는연애_14621(int v, ArrayList<PrimEdge>[] graph) {
        V = v;
        this.graph = graph;
    }

    int solve() {
        Set<Integer> vSet = new HashSet<>();
        PriorityQueue<PrimEdge> pq = new PriorityQueue<>();
        vSet.add(1);
        pq.addAll(graph[1]);

        PrimEdge e;
        int ans = 0;
        while (vSet.size() < V) {
            if(pq.isEmpty()) return -1;

            e = pq.poll();
            if(vSet.contains(e.nextV)) continue;

            vSet.add(e.nextV);
            pq.addAll(graph[e.nextV]);
            ans += e.w;
        }
        return ans;
    }
}

class MainA14621{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];

        boolean[] isWomen = new boolean[V + 1];
        char[] sexCharArray = br.readLine().replace(" ", "").toCharArray();
        for(int i = 0; i < V; i++){
            isWomen[i + 1] = sexCharArray[i] == 'W';
        }

        ArrayList<PrimEdge>[] graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();

        for (int e = 0; e < E; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            if(isWomen[tmp[0]] == isWomen[tmp[1]]) continue;

            graph[tmp[0]].add(new PrimEdge(tmp[1], tmp[2]));
            graph[tmp[1]].add(new PrimEdge(tmp[0], tmp[2]));
        }

        System.out.println(new 나만안되는연애_14621(V, graph).solve());
    }
}