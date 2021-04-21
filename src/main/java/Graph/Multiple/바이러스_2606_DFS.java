package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

class 바이러스_2606_DFS {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        visited = new boolean[N];

        int[] tmp;
        for (int i = 0; i < M; i++) {
            tmp = strToIntArray(br.readLine());
            graph[tmp[0] - 1].add(tmp[1] - 1);
            graph[tmp[1] - 1].add(tmp[0] - 1);
        }

        dfs(0);

        int ans = 0;
        for (int i = 1; i < N; i++)
            if(visited[i]) ans++;

        System.out.println(ans);
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static void dfs(int curr) {
        visited[curr] = true;

        for (int i : graph[curr])
            if(!visited[i]) dfs(i);
    }
}
