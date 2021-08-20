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

        for(int leaf : leafSet){
            bottomUpDfs(leaf);
        }

        StringBuilder stb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            stb.append(result[i]).append(" ");
        }
        return stb.toString();
    }

    int bottomUpDfs(int node) {
        if(node == ROOT) return 0;
        if(result[node] != 0) return result[node]; // 이미 조상이 업데이트할건 없음.

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

        TreeSet<Integer> leafSet = IntStream.rangeClosed(1, V).boxed().collect(Collectors.toCollection(TreeSet::new));
        leafSet = (TreeSet<Integer>) leafSet.descendingSet();

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
/*
27 5
-1 1 1 1 2 2 3 4 4 4 4 5 5 9 9 9 10 11 12 15 15 15 17 17 17 22 25
4 10
2 15
5 20
12 40
15 100
0 15 0 10 35 15 0 10 10 10 10 75 35 10 110 10 10 10 75 110 110 110 110 10 10 10 110 10
*/