package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 트리인가_6416 {
    int E;
    ArrayList<int[]> edges;
    Map<Integer, ArrayList<Integer>> graph;

    public 트리인가_6416(ArrayList<int[]> edges) {
        E = edges.size();
        this.edges = edges;
    }

    boolean solve() {
        graph = new HashMap<>();
        ArrayList<Integer> valList;

        Set<Integer> in = new HashSet<>();
        Set<Integer> out = new HashSet<>();

        for (int[] edge : edges) {
            valList = graph.getOrDefault(edge[0], new ArrayList<>());
            valList.add(edge[1]);
            graph.put(edge[0], valList);

            out.add(edge[0]);
            in.add(edge[1]);
        }

        out.removeAll(in);

        if(out.size() != 1 || in.size() != E) return false;

        int root = 0;
        for(int i : out) root = i;

        in.add(root);
        dfs(root, in);

        return in.size() == 0;
    }

    void dfs(int node, Set<Integer> unvisitedSet) {
        if(!unvisitedSet.contains(node)) return;

        unvisitedSet.remove(node); // check[node] = true;
        ArrayList<Integer> children = graph.getOrDefault(node, new ArrayList<>());
        for(int next : children) dfs(next, unvisitedSet);
    }
}

class MainA6416{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = 0;
        String s;
        ArrayList<int[]> edges;
        boolean breakFlag = false;
        StringBuilder stb = new StringBuilder();

        String[] tmp;
        int[] edge;

        while(true){
            edges = new ArrayList<>();

            LL:
            while((s = br.readLine()).length() > 0) {
                tmp = s.split("  ");
                for (String str : tmp){
                    edge = InputProcessor.strToIntArr(str);
                    if(!(edge[0] <= 0 && edge[1] <= 0)) edges.add(edge);
                    if(edge[0] < 0 && edge[1] < 0){
                        breakFlag = true; break LL;
                    }
                }
            }

            stb.append("Case ").append(++t).append(" is ").append(new 트리인가_6416(edges).solve() ? "" : "not ").append("a tree.\n");
            if(breakFlag) break;
        }

        System.out.print(stb);
    }
}
