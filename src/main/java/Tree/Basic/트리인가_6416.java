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
        if(vertices.isEmpty() && edges.isEmpty()) return true;

        if(V - 1 != E) return false;

        Map<Integer, Integer> checkMap = new HashMap<>();
        for (int[] edge : edges) {
            if(edge[0] == edge[1]) return false;
            checkMap.put(edge[1], checkMap.getOrDefault(edge[1], 0) + 1);
        }
        System.out.println(checkMap);

        Set<Integer> rootCandidates = graph.keySet();
        rootCandidates.removeAll(checkMap.keySet());

        if(rootCandidates.size() != 1) return false;

        for (int c : checkMap.keySet()) {
            if(checkMap.get(c) >= 2) return false;
        }

        int root = -1;
        for (int i : rootCandidates) {
            root = i;
        }
        return dfs(root, new boolean[V + 1]);
    }

    boolean dfs(int node, boolean[] check) {
        if(check[node]) return false;

        check[node] = true;
        boolean ret = true;
        for (int child : graph.get(node)){
            if(!dfs(child, check)) ret = false;
        }
        return ret;
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
1 2 0 0
-1 -1
Case 1 is a tree.

1 2  2 1  0 0
-1 -1
Case 1 is not a tree.
*/