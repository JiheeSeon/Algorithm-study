package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

class 촌수계산_2644_DFS {
    static ArrayList<Integer>[] graph;
    static boolean[] check;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        check = new boolean[N];

        int[] tmp = strToIntArray(br.readLine());
        int start = tmp[0] - 1; int end = tmp[1] - 1;

        int M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            tmp = strToIntArray(br.readLine());
            graph[tmp[0] - 1].add(tmp[1] - 1);
            graph[tmp[1] - 1].add(tmp[0] - 1);
        }

        dfs(start, end, 0);
        System.out.println(check[end] ? result : -1);
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static void dfs(int curr, int target, int level) {
        if(!check[curr] && curr == target){
            check[curr] = true;
            result = level;
            return;
        } else if(check[curr]) return;

        check[curr] = true;

        for(int i : graph[curr]) {
            dfs(i, target, level + 1);
        }
    }
}
