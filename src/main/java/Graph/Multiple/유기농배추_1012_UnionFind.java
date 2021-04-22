package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class 유기농배추_1012_UnionFind {
    static int yHeight;
    static int xWidth;

    static boolean[][] graph;
    static boolean[][] check;

    static int[] dy = {-1, 1, 0, 0}; // U D L R
    static int[] dx = {0, 0, -1, 1};

    static Map<Point, Point> parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] tmp;

        for(int t = 0; t < T; t++) {
            parent = new HashMap<>();
            tmp = strToIntArray(br.readLine()); // M N 배추 심어진 위치의 개수
            yHeight = tmp[1]; xWidth = tmp[0];
            int K = tmp[2];

            graph = new boolean[yHeight][xWidth];
            check = new boolean[yHeight][xWidth];

            for (int i = 0; i < K; i++) {
                tmp = strToIntArray(br.readLine());
                graph[tmp[1]][tmp[0]] = true;
            }

            int cnt = 0;

            for (int y = 0; y < yHeight; y++) {
                for (int x = 0; x < xWidth; x++) {
                    if(!check[y][x] && graph[y][x]) {
                        Point p = new Point(y, x);
                        dfs(p, p);
                    }
                }
            }

            for (Map.Entry<Point, Point> entry : parent.entrySet()) {
                find(entry.getKey());
            }

            Set<Point> set = new HashSet<>(parent.values());

            stb.append(set.size()).append("\n");
        }
        System.out.print(stb);
    }

    static void dfs(Point curr, Point prev) {
        if (curr.y < 0 || curr.x < 0 || curr.y >= yHeight || curr.x >= xWidth
                || check[curr.y][curr.x] || !graph[curr.y][curr.x]) return;

        check[curr.y][curr.x] = true;
        union(curr, prev);

        for (int i = 0; i < 4; i++) {
            dfs(new Point(curr.y + dy[i], curr.x + dx[i]), curr);
        }
    }

    static void union(Point p1, Point p2) {
        if(!parent.containsKey(p1)) parent.put(p1, p1);
        if(!parent.containsKey(p2)) parent.put(p2, p2);

        Point pP1 = find(p1);
        Point pP2 = find(p2);

        if(pP1.equals(pP2)) return;

        if(pP1.compareTo(pP2) < 0) parent.put(pP2, pP1);
        else parent.put(pP1, pP2);
    }

    static Point find(Point p) {
        if(p.equals(parent.get(p)))
            return p;
        else{
            Point pp = find(parent.get(p));
            parent.put(p, pp);
            return pp;
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
            if(y == o.y)
                return Integer.compare(y, o.x);
            else
                return Integer.compare(y, o.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return y == point.y && x == point.x;
        }
    }
}
