package Tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 다리만들기2_17472 {
    int V;
    ArrayList<KruskalEdge> edges;

    public 다리만들기2_17472(int v, ArrayList<KruskalEdge> edges) {
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

class Pair{
    int v1, v2;

    public Pair(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return (v1 == pair.v1 && v2 == pair.v2) || (v1 == pair.v2 && v2 == pair.v1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2);
    }
}

class CellInfo{
    int islandLabel, startLocation, endLocation;

    @Override
    public String toString() {
        return "CellInfo{" +
                "islandLabel=" + islandLabel +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                '}';
    }

    public CellInfo(int islandLabel, int startLocation, int endLocation) {
        this.islandLabel = islandLabel;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }
}

//class Island{
//    int label;
//    int sY, sX, eY, eX;
//
//    public Island(int label, int sY, int sX, int eY, int eX) {
//        this.label = label;
//        this.sY = sY;
//        this.sX = sX;
//        this.eY = eY;
//        this.eX = eX;
//    }
//}

class MainA17472{
    static int yHeight, xWidth;
    static int[][] graph;
    static int[][] check;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        yHeight = tmp[0]; xWidth = tmp[1];

        graph = new int[yHeight][xWidth];
        for(int y = 0; y < yHeight; y++)
            graph[y] = strToIntArr(br.readLine());

        check = new int[yHeight][xWidth];

        Map<Integer, ArrayList<CellInfo>> xMap = new TreeMap<>(); // y ->
        Map<Integer, ArrayList<CellInfo>> yMap = new TreeMap<>(); // x ->

        int mark = 1;
        int maxY, maxX;

        for(int x = 0; x < xWidth; x++){
            for(int y = 0; y < yHeight; y++){
                if(graph[y][x] == 1 && check[y][x] == 0){
                    tmp = getMaxCoordinates(y, x, mark);
                    maxY = tmp[0]; maxX = tmp[1];

                    for(int xx = x; xx <= maxX; xx++) {
                        yMap.putIfAbsent(xx, new ArrayList<>());
                        yMap.get(xx).add(new CellInfo(mark, y, maxY));
                    }

                    for(int yy = y; yy <= maxY; yy++) {
                        xMap.putIfAbsent(yy, new ArrayList<>());
                        xMap.get(yy).add(new CellInfo(mark, x, maxX));
                    }

                    mark++;
                }
            }
        }

        Map<Pair, KruskalEdge> edgeMap = new HashMap<>();
        enterToMap(edgeMap, xMap);
        enterToMap(edgeMap, yMap);

        System.out.println(new 다리만들기2_17472(mark - 1, new ArrayList<>(edgeMap.values())).solve());
    }

    static void enterToMap(Map<Pair, KruskalEdge> edgeMap , Map<Integer, ArrayList<CellInfo>> map) {
        int cost;
        ArrayList<CellInfo> cellInfos;
        for (Map.Entry<Integer, ArrayList<CellInfo>> entry: map.entrySet()) {
            if(entry.getValue().size() == 1) continue;

            cellInfos = entry.getValue();

            for(int i = 0; i < cellInfos.size() - 1; i++){
                if(edgeMap.containsKey(new Pair(cellInfos.get(i).islandLabel, cellInfos.get(i + 1).islandLabel))) continue;
                cost = cellInfos.get(i + 1).startLocation - cellInfos.get(i).endLocation - 1;
                if(cost < 0) cost = cellInfos.get(i).startLocation - cellInfos.get(i + 1).endLocation - 1;

                if(cost < 2) continue;

                edgeMap.put(new Pair(cellInfos.get(i).islandLabel, cellInfos.get(i + 1).islandLabel),
                        new KruskalEdge(cellInfos.get(i).islandLabel, cellInfos.get(i + 1).islandLabel, cost));
            }
        }
    }

    static void display() {
        for(int y = 0; y < yHeight; y++){
            for(int x = 0; x < xWidth; x++){
                System.out.print(check[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static int[] getMaxCoordinates(int sY, int sX, int mark) {
        int eY = -1, eX = -1;

        for(int y = sY; y < yHeight; y++){
            if(graph[y][sX] == 0){
                eY = y - 1;
                break;
            }
        }

        for(int x = sX; x < xWidth; x++){
            if(graph[sY][x] == 0){
                eX = x - 1;
                break;
            }
        }

        if(eY == -1) eY = yHeight - 1;
        if(eX == -1) eX = xWidth - 1;

        for(int y = sY; y <= eY; y++) {
            for (int x = sX; x <= eX; x++) {
                check[y][x] = mark;
            }
        }

        return new int[]{eY, eX};
    }
}