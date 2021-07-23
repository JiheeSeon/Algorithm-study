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

class 별자리만들기_4386 {
    int V;
    ArrayList<KruskalEdge_4386> edges;

    public 별자리만들기_4386(int v, ArrayList<KruskalEdge_4386> edges) {
        V = v;
        this.edges = edges;
    }

    double solve() {
        double ans = 0.00;
        int cnt = 0;
        KruskalEdge_4386 e;
        int[] parent = IntStream.rangeClosed(0, V).toArray();

        PriorityQueue<KruskalEdge_4386> pq = new PriorityQueue<>(edges);
        while (!pq.isEmpty() && cnt < V - 1) {
            e = pq.poll();
            if(!union(e.v1, e.v2, parent)) continue;

            cnt++;
            ans += e.w;
        }

        return ans;
    }

    boolean union(int a, int b, int[] parent){
        int pA = find(a, parent);
        int pB = find(b, parent);

        if(pA == pB) return false;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int a, int[] parent) {
        return parent[a] == a ? a : (parent[a] = find(parent[a], parent));
    }
}

class Star{
    int id;
    double y, x;

    public Star(int id, double y, double x) {
        this.id = id;
        this.y = y;
        this.x = x;
    }
}

class KruskalEdge_4386 implements Comparable<KruskalEdge_4386>{
    int v1, v2;
    double w;

    public KruskalEdge_4386(int v1, int v2, double w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }

    @Override
    public int compareTo(KruskalEdge_4386 o) {
        return Double.compare(w, o.w);
    }
}

class MainA4386{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        double[] tmp;
        Star[] stars = new Star[V];
        for(int v = 0; v < V; v++){
            tmp = strToFloatArr(br.readLine());
            stars[v] = new Star(v + 1, tmp[1], tmp[0]);
        }

        ArrayList<KruskalEdge_4386> edges = new ArrayList<>();

        Arrays.sort(stars, Comparator.comparing(a -> a.y));
        for(int i = 0; i < V - 1; i++)
            edges.add(new KruskalEdge_4386(stars[i].id, stars[i+1].id, getDistance(stars[i].y, stars[i].x, stars[i + 1].y, stars[i + 1].x)));

        Arrays.sort(stars, Comparator.comparing(a -> a.x));
        for(int i = 0; i < V - 1; i++)
            edges.add(new KruskalEdge_4386(stars[i].id, stars[i+1].id, getDistance(stars[i].y, stars[i].x, stars[i + 1].y, stars[i + 1].x)));

        System.out.printf("%.2f", new 별자리만들기_4386(V, edges).solve());
    }

    static double getDistance(double sY, double sX, double eY, double eX) {
        return Math.sqrt((eY - sY) * (eY - sY) + (eX - sX) * (eX - sX));
    }

    static double[] strToFloatArr(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToDouble(Double::parseDouble).toArray();
    }
}
