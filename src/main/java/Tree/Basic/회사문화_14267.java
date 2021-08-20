package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 회사문화_14267 {
    final int V;
    final int ROOT;

    int[] parent;
    int[][] praises;
    int[] result;
    Set<Integer> leafSet;
    Map<Integer, Integer> praiseMap;

    public 회사문화_14267(int v, int ROOT, int[] parent, Set<Integer> leafSet, int[][] praises) {
        V = v;
        this.ROOT = ROOT;

        this.parent = parent;
        this.leafSet = leafSet;
        this.praises = praises;

        result = new int[V + 1];
        praiseMap = new HashMap<>();
    }

    String solve() {
        for (int[] praise : praises)
            praiseMap.put(praise[0], praiseMap.getOrDefault(praise[0], 0) + praise[1]);

        for(int leaf : leafSet) bottomUpDfs(leaf);

        StringBuilder stb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            stb.append(result[i]).append(" ");
        }
        return stb.toString();
    }

    int bottomUpDfs(int node) {
        if(node == ROOT) return 0;

        int res = bottomUpDfs(parent[node]);
        if(praiseMap.containsKey(node)) res += praiseMap.get(node);
        result[node] += res;
        return res;
    }
}

class MainA14267{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int praiseN = tmp[1];

        Set<Integer> leafSet = IntStream.rangeClosed(1, V).boxed().collect(Collectors.toCollection(TreeSet::new));
        int root = 0;
        int[] parent = InputProcessor.strToIntArr("0 " + br.readLine());
        for (int i : parent) {
            leafSet.remove(i);
            if(i == -1) root = i;
        }

        int[][] praises = new int[praiseN][2];
        for (int p = 0; p < praiseN; p++)
            praises[p] = InputProcessor.strToIntArr(br.readLine());

        System.out.println(new 회사문화_14267(V, root, parent, leafSet, praises).solve());
    }
}