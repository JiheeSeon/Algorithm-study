package Tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class 전력난_6497 {
    int V;
    ArrayList<KruskalEdge> edges;

    public 전력난_6497(int v, ArrayList<KruskalEdge> edges) {
        V = v;
        this.edges = edges;
    }

    int solve() {
        int ans = 0; int cnt = 0;

        int[] parent = IntStream.range(0, V).toArray();
        PriorityQueue<KruskalEdge> pq = new PriorityQueue<>(edges);

        KruskalEdge e;
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
        return a == parent[a] ? a : (parent[a] = find(parent[a], parent));
    }
}

class MainA6497{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        String s;
        int[] tmp;
        int V, E;
        int sum;
        while(!(s = br.readLine()).equals("0 0")){
            tmp = strToIntArr(s);
            V = tmp[0]; E = tmp[1];

            sum = 0;
            ArrayList<KruskalEdge> edges = new ArrayList<>();
            for(int e = 0; e < E; e++){
                tmp = strToIntArr(br.readLine());
                edges.add(new KruskalEdge(tmp[0], tmp[1], tmp[2]));
                sum += tmp[2];
            }
            stb.append(sum - new 전력난_6497(V, edges).solve()).append("\n");
        }
        System.out.print(stb);
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
