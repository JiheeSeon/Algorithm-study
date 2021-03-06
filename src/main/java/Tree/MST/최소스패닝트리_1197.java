package Tree.MST;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/*
TC
7 11
1 2 2
2 3 5
7 2 7
7 3 2
6 7 9
3 6 3
3 5 6
3 1 20
1 4 10
4 5 1
5 6 23
19

4 5
1 2 10
2 3 20
1 3 15
2 4 24
3 4 30
49
*/

class 최소스패닝트리_1197 {
    int V, E;
    int[][] input;

    public 최소스패닝트리_1197(int V, int E, int[][] input){
        this.V = V;
        this.E = E;
        this.input = input;
    }

    long solution_kruskal(){
        long ans = 0;
        int[] parent = IntStream.rangeClosed(0, V).toArray();

        ArrayList<EdgeA1197_Kruskal> graph = new ArrayList<>();
        for(int e = 0; e < E; e++){
            graph.add(new EdgeA1197_Kruskal(input[e][0], input[e][1], input[e][2]));
        }
        Collections.sort(graph);

        int edgeCnt = 0;
        for(EdgeA1197_Kruskal e : graph){
            if(!union(parent, e.v1, e.v2)) continue;

            ans += e.w;
            if(++edgeCnt == E - 1) return ans;
        }
        return ans;
    }

    boolean union(int[] parent, int a, int b){
        int pA = find(parent, a);
        int pB = find(parent, b);

        if(pA == pB) return false;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;

        return true;
    }

    int find(int[] parent, int a){
        return a == parent[a] ? a : (parent[a] = find(parent, parent[a]));
    }

    long solution_prim(){
        long ans = 0;
        ArrayList<EdgeA1197_Prim>[] graph = new ArrayList[V + 1];
        int startV = -1;

        // setting graph
        for(int v = 1; v <= V; v++)
            graph[v] = new ArrayList<>();

        for (int[] info : input) {
            if(startV == -1) startV = info[0];
            graph[info[0]].add(new EdgeA1197_Prim(info[1], info[2]));
            graph[info[1]].add(new EdgeA1197_Prim(info[0], info[2]));
        }
        boolean[] hasParent = new boolean[V + 1];

        // setting other variables
        Set<Integer> selectedV = new HashSet<>();
        PriorityQueue<EdgeA1197_Prim> edgePQ = new PriorityQueue<>();
        edgePQ.addAll(graph[startV]);
        selectedV.add(startV);

        EdgeA1197_Prim selectedEdge;

        while(!edgePQ.isEmpty() && selectedV.size() < V){
            // 선택된 정점과 연결되는 간선 중 가장 가중치가 작은 것 추가
            selectedEdge = edgePQ.poll();

            // 이미 선택된 정점과 연결되어 있는 경우
            if(selectedV.contains(selectedEdge.v)) continue;
            // 트리의 조건 : 부모는 무조건 하나
            if(hasParent[selectedEdge.v]) continue;

            ans += selectedEdge.w;
            selectedV.add(selectedEdge.v);
            hasParent[selectedEdge.v] = true;
            edgePQ.addAll(graph[selectedEdge.v]);
        }

        return ans;
    }
}

class EdgeA1197_Kruskal implements Comparable<EdgeA1197_Kruskal>{
    int v1, v2;
    long w;

    public EdgeA1197_Kruskal(int v1, int v2, int w){
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }

    @Override
    public int compareTo(EdgeA1197_Kruskal o) {
        return Long.compare(w, o.w);
    }
}

class EdgeA1197_Prim implements Comparable<EdgeA1197_Prim> {
    int v; long w;

    public EdgeA1197_Prim(int v, long w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(EdgeA1197_Prim o) {
        return Long.compare(w, o.w);
    }
}


class MainA1197{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmp = strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];
        int[][] input = new int[E][3];

        for(int e = 0; e < E; e++)
            input[e] = strToIntArr(br.readLine());

        System.out.println(new 최소스패닝트리_1197(V, E, input).solution_prim());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}

