package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 노드사이의거리_1240 {
    int V;
    final int ROOT;

    ArrayList<Integer>[] graph;
    Map<PairA1240, Integer> weights;
    Set<Integer> vertices;
    int maxLevel = 0;

    ArrayList<Map<Integer, Integer>> sparseMapList;
    ArrayList<Map<Integer, Integer>> sparseMapListForDistance;
    Map<Integer, Integer> levels;
    Map<PairA1240, Integer> resMap;

    public 노드사이의거리_1240(int V, int root, Map<PairA1240, Integer> weights, ArrayList<Integer>[] graph, Set<Integer> vertices) {
        this.V = V;
        this.ROOT = root;
        this.weights = weights;
        this.graph = graph;
        this.vertices = vertices;

        sparseMapList = new ArrayList<>();
        sparseMapList.add(new HashMap<>());
        sparseMapList.get(0).put(ROOT, -1);

        sparseMapListForDistance = new ArrayList<>();
        sparseMapListForDistance.add(new HashMap<>());
        sparseMapListForDistance.get(0).put(ROOT, 0);
        sparseMapListForDistance.get(0).put(0, 0);

        levels = new HashMap<>();
        resMap = new HashMap<>();

        init();
    }

    void init() {
        dfs(ROOT, new boolean[V + 1], 0);
        setSparseInformation();
    }

    void dfs(int node, boolean[] check, int level) {
        check[node] = true;
        maxLevel = Math.max(level, maxLevel);
        levels.put(node, level);

        for (int child : graph[node]) {
            if(check[child]) continue;

            sparseMapList.get(0).put(child, node);
            sparseMapListForDistance.get(0).put(child, weights.get(new PairA1240(child, node)));
            dfs(child, check, level + 1);
        }
    }

    void setSparseInformation() {
        int end = (int)(Math.log10(maxLevel) / Math.log10(2));

        int nextNode;
        int nextD;
        for (int i = 1; i <= end; i++) {
            sparseMapList.add(new HashMap<>());
            sparseMapListForDistance.add(new HashMap<>());

            for (int vertex : vertices) {
                if(vertex == ROOT){
                    sparseMapList.get(i).put(vertex, -1);
                    sparseMapListForDistance.get(i).put(vertex, 0);
                    continue;
                }

                nextNode = sparseMapList.get(i - 1).get(vertex);
                nextD = sparseMapListForDistance.get(i - 1).get(vertex);

                if(nextNode== -1){
                    sparseMapList.get(i).put(vertex, -1);
                    sparseMapListForDistance.get(i).put(vertex, nextD);
                } else {
                    sparseMapList.get(i).put(vertex, sparseMapList.get(i - 1).get(nextNode));
                    sparseMapListForDistance.get(i).put(vertex, nextD + sparseMapListForDistance.get(i - 1).get(nextNode));
                }
            }
        }
    }

    int solve(int a, int b) {
        PairA1240 p = new PairA1240(a, b);
        if(resMap.containsKey(p)) return resMap.get(p);

        int levelA = levels.get(a);
        int levelB = levels.get(b);

        // 1. swap
        if (levelA > levelB) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 2. make into same level
        int deltaDist = levelB - levelA;
        int targetDist = (int)(Math.log10(deltaDist) / Math.log10(2)) + 1;
        int pathN;

        int res = 0;
        while (--targetDist >= 0) {
            pathN = 1 << targetDist;

            if((deltaDist & pathN) != 0){
                res += sparseMapListForDistance.get(targetDist).get(b);
                b = sparseMapList.get(targetDist).get(b) == -1 ? -1 : sparseMapList.get(targetDist).get(b);
                if(b == ROOT) break;
            }
        }

        if(a == b){
            resMap.put(p, res);
            return res;
        }

        deltaDist = levelA;
        targetDist = (int)(Math.log10(deltaDist) / Math.log10(2)) + 1;

        int newA, newB;

        while (--targetDist >= 0) {
            newA = sparseMapList.get(targetDist).get(a);
            newB = sparseMapList.get(targetDist).get(b);

            if(Objects.equals(newA, newB)) continue;

            a = newA; b = newB;

            pathN = 1 << targetDist;
            res += sparseMapListForDistance.get(pathN).get(a);
            res += sparseMapListForDistance.get(pathN).get(b);
        }

        res += sparseMapListForDistance.get(0).get(a) + sparseMapListForDistance.get(0).get(b);

        resMap.put(p, res);
        return res;
    }
}

class PairA1240 {
    int a, b;

    public PairA1240(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairA1240 pairA1240 = (PairA1240) o;
        return (a == pairA1240.a && b == pairA1240.b) || (a == pairA1240.b && b == pairA1240.a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.min(a, b), Math.max(a, b));
    }

    @Override
    public String toString() {
        return "{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}

class MainA1240{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int V = tmp[0]; int Q = tmp[1];
        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) graph[v] = new ArrayList<>();

        Set<Integer> vertices = new TreeSet<>();
        Map<PairA1240, Integer> weights = new HashMap<>();

        for (int i = 0; i < V - 1; i++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);

            weights.put(new PairA1240(tmp[0], tmp[1]), tmp[2]);
            vertices.add(tmp[0]);
            vertices.add(tmp[1]);
        }

        int root = 0;
        for (int v : vertices) {
            root = v;
            break;
        }

        노드사이의거리_1240 solution = new 노드사이의거리_1240(V, root, weights, graph, vertices);
        StringBuilder stb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            stb.append(solution.solve(tmp[0], tmp[1])).append("\n");
        }
        System.out.print(stb);
    }
}