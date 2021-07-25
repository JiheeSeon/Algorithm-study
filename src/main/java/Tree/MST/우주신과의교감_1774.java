package Tree.MST;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class 우주신과의교감_1774 {
    int V;
    ArrayList<KruskalEdgeDW> edgesAlreadyMade;
    ArrayList<KruskalEdgeDW> edges;
    int[] parent;

    double ans = 0.00;
    int cnt = 0;

    public 우주신과의교감_1774(int v, ArrayList<KruskalEdgeDW> edgesAlreadyMade, ArrayList<KruskalEdgeDW> edges) {
        V = v;
        this.edgesAlreadyMade = edgesAlreadyMade;
        this.edges = edges;
        parent =  IntStream.rangeClosed(0, V).toArray();
    }

    void runMst(ArrayList<KruskalEdgeDW> edgeList) {
        PriorityQueue<KruskalEdgeDW> pq = new PriorityQueue<>(edgeList);
        KruskalEdgeDW e;
        while(!pq.isEmpty() && cnt < V - 1){
            e = pq.poll();
            if(!union(e.v1, e.v2)) continue;

            cnt++;
            ans += e.w;
        }
    }

    double solve() {
        runMst(edgesAlreadyMade);
        ans = 0.0;
        runMst(edges);
        return ans;
    }

    boolean union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return false;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int a){
        return a == parent[a] ? a : (parent[a] = find(parent[a]));
    }
}

class MainA1774{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];

        Point[] points = new Point[V];
        for(int v = 0; v < V; v++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            points[v] = new Point(tmp[1], tmp[0]);
        }

        ArrayList<KruskalEdgeDW> edgesAlreadyMade = new ArrayList<>();
        double nowWeight;

        for(int e = 0; e < E; e++){
            tmp = InputProcessor.strToIntArr(br.readLine());
            nowWeight = getDistance(points[tmp[0] - 1].y, points[tmp[0] - 1].x, points[tmp[1] - 1].y, points[tmp[1] - 1].x);
            edgesAlreadyMade.add(new KruskalEdgeDW(tmp[0], tmp[1], nowWeight));
        }

        KruskalEdgeDW edge;
        ArrayList<KruskalEdgeDW> edgesToMake = new ArrayList<>();
        for(int i = 0;  i < V - 1; i++){
            for(int j = i + 1; j < V ; j++){
                edge = new KruskalEdgeDW(i + 1, j + 1, getDistance(points[i].y, points[i].x, points[j].y, points[j].x));
                if(!edgesAlreadyMade.contains(edge)) edgesToMake.add(edge);
            }
        }

        System.out.printf("%.2f", new 우주신과의교감_1774(V, edgesAlreadyMade, edgesToMake).solve());
    }

    static double getDistance(double aY, double aX, double bY, double bX) {
        return Math.sqrt((bY - aY) * (bY - aY) + (bX - aX) * (bX - aX));
    }
}