package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class 촌수계산_2644_BFS {
    static ArrayList<Integer>[] graph;
    static int[] check;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        check = new int[N];

        int[] tmp = strToIntArray(br.readLine());
        int start = tmp[0] - 1;
        int end = tmp[1] - 1;

        int M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            tmp = strToIntArray(br.readLine());
            graph[tmp[0] - 1].add(tmp[1] - 1);
            graph[tmp[1] - 1].add(tmp[0] - 1);
        }
        check[end] = -1;

        bfs(start);
        System.out.println(check[end]);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        check[start] = 0;

        int now;
        while (!q.isEmpty()) {
            now = q.poll();

            for(int i : graph[now]){
                if(check[i] != 0 && check[i] != -1) continue;

                q.add(i);
                check[i] = check[now] + 1;
            }
        }
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
