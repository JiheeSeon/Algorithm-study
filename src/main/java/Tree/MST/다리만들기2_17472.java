package Tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class 다리만들기2_17472 {
    int V;
    LinkedList<KruskalEdge> edges;

    public 다리만들기2_17472(int v, LinkedList<KruskalEdge> edges) {
        V = v;
        this.edges = edges;
    }

    int solve() {
        int cnt = 0;
        int ans = 0;
        KruskalEdge e;

        PriorityQueue<KruskalEdge> pq = new PriorityQueue<>(edges);
        int[] parent = IntStream.rangeClosed(0, V).toArray();

        while (cnt < V - 1) {
            if(pq.isEmpty()) return -1;

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

    int find(int a, int[] parent){
        return a == parent[a] ? a : (parent[a] = find(parent[a], parent));
    }
}

class GuidelineA17472 implements Comparable<GuidelineA17472> {
    int label, coordinate;

    public GuidelineA17472(int label, int coordinate) {
        this.label = label;
        this.coordinate = coordinate;
    }

    @Override
    public int compareTo(GuidelineA17472 o) {
        return Integer.compare(coordinate, o.coordinate);
    }
}

class PairA17472 {
    int v1, v2;

    public PairA17472(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairA17472 pairA17472 = (PairA17472) o;
        return v1 == pairA17472.v1 && v2 == pairA17472.v2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2);
    }
}

class MainA17472{
    static int yHeight, xWidth;
    static int[][] graph;
    static int[][] check;

    static int[] dy = {-1, 1, 0, 0}; // 상하좌우
    static int[] dx = {0, 0, -1, 1};

    static TreeMap<Integer, LinkedList<GuidelineA17472>> xMap = new TreeMap<>();
    static TreeMap<Integer, LinkedList<GuidelineA17472>> yMap = new TreeMap<>();

    static LinkedList<KruskalEdge> edges = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        yHeight = tmp[0];
        xWidth = tmp[1];

        graph = new int[yHeight][xWidth];
        for (int y = 0; y < yHeight; y++)
            graph[y] = strToIntArr(br.readLine());

        check = new int[yHeight][xWidth];

        int mark = 1;
        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                if(check[y][x] == 0 && graph[y][x] == 1){
                    dfs(y, x, mark++, 0);
                }
            }
        }
        setEdges();
        System.out.println(new 다리만들기2_17472(mark - 1, edges).solve());
    }

    static void dfs(int y, int x, int mark, int direction) {
        if(y < 0 || y >= yHeight || x < 0 || x >= xWidth || graph[y][x] == 0){
            switch (direction) {
                case 0 ->{
                    xMap.putIfAbsent(x, new LinkedList<>());
                    xMap.get(x).add(new GuidelineA17472(mark, y + 1));
                }
                case 1 -> {
                    xMap.putIfAbsent(x, new LinkedList<>());
                    xMap.get(x).add(new GuidelineA17472(mark, y - 1));
                }
                case 2->{
                    yMap.putIfAbsent(y, new LinkedList<>());
                    yMap.get(y).add(new GuidelineA17472(mark, x + 1));
                }
                case 3-> {
                    yMap.putIfAbsent(y, new LinkedList<>());
                    yMap.get(y).add(new GuidelineA17472(mark, x - 1));
                }
            }
            return;
        }
        if(check[y][x] != 0) return;

        check[y][x] = mark;
        for (int i = 0; i < 4; i++) {
            dfs(y + dy[i], x + dx[i], mark, i);
        }
    }

    static void setEdges() {
        TreeMap<Integer, LinkedList<GuidelineA17472>>[] maps = new TreeMap[]{xMap, yMap};
        LinkedList<GuidelineA17472> guidelineA17472s;
        int cost; PairA17472 p;

        Map<PairA17472, Integer> costs = new HashMap<>();

        for(Map<Integer, LinkedList<GuidelineA17472>> m : maps) {
            for (Map.Entry<Integer, LinkedList<GuidelineA17472>> entry : m.entrySet()) {
                if(entry.getValue().size() <= 2) continue;

                guidelineA17472s = entry.getValue();
                Collections.sort(guidelineA17472s);

                for(int i = 2; i < guidelineA17472s.size(); i += 2) {
                    cost = guidelineA17472s.get(i).coordinate - guidelineA17472s.get(i - 1).coordinate - 1;
                    if(cost < 2) continue;
                    p = new PairA17472(guidelineA17472s.get(i).label, guidelineA17472s.get(i - 1).label);
                    costs.put(p, Math.min(cost, costs.getOrDefault(p, Integer.MAX_VALUE)));
                }
            }
        }

        KruskalEdge edge;
        for (Map.Entry<PairA17472, Integer> entry : costs.entrySet()) {
            edge = new KruskalEdge(entry.getKey().v1, entry.getKey().v2, entry.getValue());
            edges.add(new KruskalEdge(entry.getKey().v1, entry.getKey().v2, entry.getValue()));
        }
    }

    static int[] strToIntArr(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}