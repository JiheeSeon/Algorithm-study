package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 트리인가_6416 {
    int V, E;
    Set<Integer> vertices;
    ArrayList<int[]> edges;
    Map<Integer, ArrayList<Integer>> graph;

    public 트리인가_6416(Set<Integer> vertices, ArrayList<int[]> edges, Map<Integer, ArrayList<Integer>> graph) {
        this.vertices = vertices;
        this.edges = edges;

        V = vertices.size(); E = edges.size();
        this.graph = graph;
    }

    boolean solve() {
        if(V == 0) return true;

        Set<Integer> nodeWithOneParent = new HashSet<>();
        for (int[] edge : edges) {
            if(edge[0] == edge[1]) return false;
            if(nodeWithOneParent.contains(edge[1])) return false;
            nodeWithOneParent.add(edge[1]);
        }

        Set<Integer> rootCandidates = graph.keySet();
        rootCandidates.removeAll(nodeWithOneParent);

        // 루트는 오직 한개여야 한다.
        if(rootCandidates.size() != 1) return false;

        if(V - 1 != E) return false;

        int root = -1;
        for (int i : rootCandidates) {root = i;}
        return dfs(root, new boolean[V + 1]);
    }

    boolean dfs(int node, boolean[] check) {
        if(check[node]) return false;

        check[node] = true;
        if(!graph.containsKey(node)) return true;

        for (int child : graph.get(node)){
            if(!dfs(child, check)) return false;
        }
        return true;
    }
}

class MainA6416{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = 0;
        String s;

        Map<Integer, ArrayList<Integer>> graph;
        ArrayList<int[]> edges;
        boolean breakFlag = false;
        StringBuilder stb = new StringBuilder();

        String[] tmp;
        int[] edge;
        ArrayList<Integer> nextVertices;
        Set<Integer> vertices;

        while(true){
            edges = new ArrayList<>();
            graph = new HashMap<>();
            vertices = new HashSet<>();

            LL:
            while((s = br.readLine()).length() > 0) {
                tmp = s.split("  ");
                for (String str : tmp){
                    edge = InputProcessor.strToIntArr(str);
                    if(!(edge[0] <= 0 && edge[1] <= 0)){
                        edges.add(edge);

                        nextVertices = graph.getOrDefault(edge[0], new ArrayList<>());
                        nextVertices.add(edge[1]);
                        graph.put(edge[0], nextVertices);

                        vertices.add(edge[0]); vertices.add(edge[1]);
                    }
                    if(edge[0] < 0 && edge[1] < 0){
                        breakFlag = true; break LL;
                    }
                }
            }

            stb.append("Case ").append(++t).append(" is ").append(new 트리인가_6416(vertices, edges, graph).solve() ? "" : "not ").append("a tree.\n");
            if(breakFlag) break;
        }

        System.out.print(stb);
    }
}
/*
1 2  0 0

1 2  2 1  0 0

1 1  0 0
-1 -1

Case 1 is a tree.
Case 1 is not a tree.
Case 1 is not a tree.

1 2
2 3
3 2
4 5
0 0
-1 -1
Case 1 is not a tree.

1 2  2 3  0 0
-1 -1

*/