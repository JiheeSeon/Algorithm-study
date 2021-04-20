package Graph.Both;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class 바이러스_2606_BFS {
    static ArrayList<Integer>[] graph;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        check = new boolean[N];

        int[] tmp;
        for (int i = 0; i < M; i++) {
            tmp = strToIntArray(br.readLine());
            graph[tmp[0] - 1].add(tmp[1] - 1);
            graph[tmp[1] - 1].add(tmp[0] - 1);
        }

        bfs();

        int ans = 0;
        for (int i = 1; i < N; i++) {
            if(check[i]) ans++;
        }
        System.out.println(ans);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int now;

        while (!q.isEmpty()) {
            now = q.poll();

            for(int i : graph[now]){
                if(check[i]) continue;

                q.add(i);
                check[i] = true;
            }
        }
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}