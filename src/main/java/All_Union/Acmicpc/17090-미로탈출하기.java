package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class Main17090{
    static Map<Point, Point> parent = new HashMap<>();
    static Set<Point> cycles = new HashSet<>();
    static Set<Point> notCycles = new HashSet<>();
    static char[][] maze;
    static Point[][] points;
    static boolean[][] check;

    static int colN; static int rowN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        colN = tmp[0]; rowN = tmp[1];
        maze = new char[colN][rowN];
        check = new boolean[colN][rowN];
        points = new Point[colN][rowN];

        String tmpS;
        for (int y = 0; y < colN; y++) {
            tmpS = br.readLine();
            for (int x = 0; x < rowN; x++) {
                maze[y][x] = tmpS.charAt(x);

                points[y][x] = new Point(y, x);
                parent.put(points[y][x], points[y][x]);
            }
        }

        for (int y = 0; y < colN; y++) {
            for (int x = 0; x < rowN; x++) {
                Point p = points[y][x];
                if(!check[p.y][p.x]) dfs(p, p);
            }
        }

        int ans = 0;

        for(Point p : parent.values()){
            Point ancestor = find(p);
            if(!cycles.contains(ancestor)) ans++;
        }

        System.out.println(ans);
    }

    static void dfs(Point curr, Point prev) {
        if(check[curr.y][curr.x]){
            if(prev != curr) {
                union(prev, curr);
                if(!notCycles.contains(find(curr)))
                    cycles.add(find(prev));
            }
            return;
        }

        check[curr.y][curr.x] = true;
        union(curr, prev);
        if(cycles.contains(find(curr))) return;

        Point next;
        int nextY = -1, nextX = -1;
        switch (maze[curr.y][curr.x]) {
            case 'U' -> {
                nextY = curr.y - 1;
                nextX = curr.x;
            }
            case 'D' -> {
                nextY = curr.y + 1;
                nextX = curr.x;
            }
            case 'L' -> {
                nextY = curr.y;
                nextX = curr.x - 1;
            }
            case 'R' -> {
                nextY = curr.y;
                nextX = curr.x + 1;
            }
        }

        if (nextY < 0 || nextY >= colN || nextX < 0 || nextX >= rowN)
            notCycles.add(find(curr));
        else
            dfs(points[nextY][nextX], curr);
    }

    static void union(Point p1, Point p2) {
        Point pP1 = find(p1);
        Point pP2 = find(p2);

        if (pP1.equals(pP2) && (p1 != p2)){ // 사이클일 때
            cycles.add(pP1);
            return;
        }

        if(pP2.compareTo(pP1) < 0) parent.put(pP1, pP2);
        else parent.put(pP2, pP1);
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

    static private class Point implements Comparable<Point>{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return y == point.y && x == point.x;
        }

        @Override
        public int compareTo(Point o) {
            return y == o.y ? Integer.compare(x, o.x) : Integer.compare(y, o.y);
        }

        @Override
        public String toString() {
            return  "y=" + y + ", x=" + x ;
        }
    }

    static void displayParent() {
        for (int c = 0; c < colN; c++) {
            for (int r = 0; r < rowN; r++) {
                System.out.print(parent.get(points[c][r]) + "\t");
            }
            System.out.println();
        }
    }
}