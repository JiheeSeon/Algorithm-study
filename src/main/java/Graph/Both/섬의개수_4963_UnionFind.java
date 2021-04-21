package Graph.Both;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*
생각 정리
Q.
y가 달라질 때마다 한줄씩 받는다고 할 때
인접한 x가 1이면 붙이고, 위로 인접한 애가 1이면 또 union하도록 하는 것은 불가한가?
Union find를 적용할 때에도 꼭 DFS로 탐색해야 할까?
A.
답은 꼭 DFS로 탐색해야 할 듯. 왜냐하면 대각체크를 하기 어려움.
그를 고려해 구현하다보면 사실상 DFS의 효과를 내야 할 듯.

실수했던 부분
testcase가 여러개일 때 parent hashmap 을 초기화시키지 않아 누적됨.
*/

class 섬의개수_4963_UnionFind {
    static int[][] graph;
    static boolean[][] check;
    static int yHeight, xWidth;

    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    static Map<Point, Point> parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        String input;
        int[] tmp;

        while(!(input = br.readLine()).equals("0 0")){
            tmp = strToIntArray(input);
            yHeight = tmp[1]; xWidth = tmp[0];
            graph = new int[yHeight][xWidth];
            check = new boolean[yHeight][xWidth];
            parent = new HashMap<>();

            for (int y = 0; y < yHeight; y++) {
                graph[y] = strToIntArray(br.readLine());

                for(int x = 0; x < xWidth; x++){
                    if(graph[y][x] == 1){
                        Point p = new Point(y, x);
                        parent.put(p, p);
                    }
                }
            }

            for (int y = 0; y < yHeight; y++) {
                for (int x = 0; x < xWidth; x++) {
                    if(graph[y][x] == 1 && !check[y][x])
                        dfs(new Point(y, x), new Point(y, x));
                }
            }

            for(Point p : parent.values()) find(p);
            Set<Point> parentCount = new HashSet<>(parent.values());
            stb.append(parentCount.size()).append("\n");
        }
        System.out.println(stb);
    }

    static void dfs(Point curr, Point prev){
        if(curr.y < 0 || curr.x < 0 || curr.y >= yHeight || curr.x >= xWidth
                || graph[curr.y][curr.x] == 0 || check[curr.y][curr.x]) return;

        union(prev, curr);
        check[curr.y][curr.x] = true;

        int nextY, nextX;
        for (int i = 0; i < 8; i++) {
            nextY = curr.y + dy[i];
            nextX = curr.x + dx[i];

            dfs(new Point(nextY, nextX), curr);
        }
    }

    static void union(Point p1, Point p2) {
        Point pP1 = find(p1);
        Point pP2 = find(p2);

        if(pP1.equals(pP2)) return;

        if(pP1.compareTo(pP2) < 0) parent.put(pP2, pP1);
        else parent.put(pP1, pP2);
    }

    static Point find(Point p) {
        if (p.equals(parent.get(p))) {
            return p;
        }else{
            Point pP = find(parent.get(p));
            parent.put(p, pP);
            return pP;
        }
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static private class Point implements Comparable<Point>{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Point o) {
            return y == o.y ? Integer.compare(x, o.x) : Integer.compare(y, o.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return y == point.y && x == point.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }

        @Override
        public String toString() {
            return "Point(" + "y=" + y + ", x=" + x + ')';
        }
    }
}
