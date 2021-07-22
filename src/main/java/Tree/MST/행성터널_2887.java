package Tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class 행성터널_2887 {
    int V;
    ArrayList<KruskalEdge> edges;

    public 행성터널_2887(int v, ArrayList<KruskalEdge> edges) {
        V = v;
        this.edges = edges;
    }

    int solve() {
        int ans = 0;
        int[] parent = IntStream.rangeClosed(0, V).toArray();
        PriorityQueue<KruskalEdge> pq = new PriorityQueue<>(edges);

        KruskalEdge e; int cnt = 0; // local variable
        while (!pq.isEmpty() && cnt < V - 1) {
            e = pq.poll();
            if(!union(e.v1, e.v2, parent)) continue;

            ans += e.w;
            cnt++;
        }

        return ans;
    }

    boolean union(int a, int b, int[] parent) {
        int pA = find(a, parent);
        int pB = find(b, parent);

        if(pA == pB) return false;

        if (pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int a, int[] parent) {
        return a == parent[a] ? a : (parent[a] = find(parent[a], parent));
    }
}

class PointA2887{
    int label;
    int x, y, z;

    public PointA2887(int label, int x, int y, int z) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class MainA2887{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        if(V == 1){
            System.out.println(0);
            return;
        }

        ArrayList<KruskalEdge> edges = new ArrayList<>();
        PointA2887[] points = new PointA2887[V];

        int[] tmp;
        for(int v = 0; v < V; v++){
            tmp = strToIntArr(br.readLine());
            points[v] = new PointA2887(v + 1, tmp[0], tmp[1], tmp[2]);
        }

        Arrays.sort(points, Comparator.comparing(a -> a.x));
        addToEdges(V, points, edges);

        Arrays.sort(points, Comparator.comparing(a -> a.y));
        addToEdges(V, points, edges);

        Arrays.sort(points, Comparator.comparing(a -> a.z));
        addToEdges(V, points, edges);

        System.out.println(new 행성터널_2887(V, edges).solve());
    }

    static void addToEdges(int V, PointA2887[] points, ArrayList<KruskalEdge> edges) {
        edges.add(new KruskalEdge(points[0].label, points[1].label, getCost(points, 0, 1)));

        for(int v = 0; v < V - 1; v++){
            edges.add(new KruskalEdge(points[v].label, points[v + 1].label, getCost(points, v, v + 1)));
        }
    }

    static int getCost(PointA2887[] points, int i, int j) {
        return Math.min(
                Math.abs(points[i].z - points[j].z),
                Math.min(
                        Math.abs(points[i].x - points[j].x), Math.abs(points[i].y - points[j].y)));
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}