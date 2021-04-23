package Tree;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class 트리의부모찾기_11725 {
    static Point[] points;
    static int[] parent;
    static ArrayList<Integer>[] graph;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        points = new Point[N - 1];
        parent = new int[N + 1]; // 1-N
        graph = new ArrayList[N + 1];

        check = new boolean[N + 1];

        for(int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        int[] tmp;
        for (int i = 0; i < N - 1; i++) {
            tmp = strToIntArray(br.readLine());

            points[i] = new Point(tmp[0], tmp[1]);
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        parent[1] = 1;
        dfs(1, 1);

        StringBuilder stb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            stb.append(parent[i]).append("\n");
        }
        System.out.print(stb);
    }

    static void dfs(int curr, int prev) { // prev가 부모
        if(check[curr]) return;
        check[curr] = true;

        for(int i : graph[curr]){
            parent[curr] = prev;
            dfs(i, curr);
        }
    }

    static int[] strToIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
    static class Point implements Comparable<Point>{
        int min, max;

        public Point(int a, int b){
            if(a < b){
                min = a; max = b;
            } else{
                min = b; max = a;
            }
        }

        @Override
        public int compareTo(Point o) {
            return min == o.min ? Integer.compare(max, o.max) : Integer.compare(min, o.min);
        }
    }
}