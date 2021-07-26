package Tree.MST;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class 레드블루스패닝트리_4792 {
    int V, BE;
    ArrayList<KruskalEdgeIW> edges;
    int[] parent;

    public 레드블루스패닝트리_4792(int v, int be, ArrayList<KruskalEdgeIW> edges) {
        V = v;
        BE = be;
        this.edges = edges;
    }

    int solve() {
        // 파란 간선이 최소로 들어가는 스패닝 트리 > 파란 간선의 개수
        int min = MST(new PriorityQueue<>(edges));
        if(min == -1) return 0;

        // 파란 간선이 최대로 들어가는 스패닝 트리 > 파란 간선의 개수
        PriorityQueue<KruskalEdgeIW> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(edges);
        int max = MST(maxHeap);
        if(max == -1) return 0;

        return (max >= BE && BE >= min) ? 1 : 0;
    }

    int MST(PriorityQueue<KruskalEdgeIW> pq) {
        int cnt = 0;  // MST를 구성하는 간선의 개수
        int bCnt = 0; // MST를 구성하는 파란색 간선의 개수

        parent = IntStream.rangeClosed(0, V).toArray();
        KruskalEdgeIW e;

        while(cnt < V - 1){
            if(pq.isEmpty()) return -1; // 스패닝트리를 만들 수 없는 경우

            e = pq.poll();

            if(!union(e.v1, e.v2)) continue;

            cnt++;
            if(e.w == 1) bCnt++; // 파란색 간선일 경우
        }
        return bCnt;
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

class MainA4792 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp;
        int V, E, BE;

        char[] info;
        String s = br.readLine();

        ArrayList<KruskalEdgeIW> edges;
        StringBuilder stb = new StringBuilder();

        while(!s.equals("0 0 0")){
            tmp = InputProcessor.strToIntArr(s);
            V = tmp[0]; E = tmp[1]; BE = tmp[2];
            edges = new ArrayList<>();

            for (int e = 0; e < E; e++) {
                info = br.readLine().replace(" ", "").toCharArray();
                edges.add(new KruskalEdgeIW(info[1] - '0', info[2] - '0', info[0] == 'B' ? 1 : 0));
            }
            stb.append(new 레드블루스패닝트리_4792(V, BE, edges).solve()).append("\n");

            s = br.readLine();
        }
        System.out.print(stb);
    }
}
/*
6 8 1
R 1 2
B 1 3
B 1 4
R 3 4
B 2 5
B 2 6
R 5 6
R 2 3
*/