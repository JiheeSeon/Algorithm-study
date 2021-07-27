package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class 트리의부모찾기_11725 {
    int N;
    ArrayList<Integer>[] graph;
    boolean[] check;
    int[] parent;

    public 트리의부모찾기_11725(int n, ArrayList<Integer>[] graph) {
        N = n;
        this.graph = graph;
        check = new boolean[N + 1];
        parent = new int[N + 1];
    }

    String solve() {
        dfs(1);

        StringBuilder stb = new StringBuilder();
        for(int i = 2; i <= N ; i++)
            stb.append(parent[i]).append("\n");

        return stb.toString();
    }

    /*
    루트로부터의 dfs (트리의 탐색)

    루트가 주어졌기 때문에 Top -> Bottom 방향으로 탐색 진행해야.
    무방향 그래프인 graph + 방문여부를 저장하는 check [방향성 지정하는 효과]
    -> Tree = Directed Acyclic Graph 에서의 탐색 효과
    */
    void dfs(int now) {
        check[now] = true;

        for (int child : graph[now]) {
            if(check[child]) continue; // 이미 방문했으면 부모로 이어지는 간선

            parent[child] = now;
            dfs(child);
        }
    }
}

class MainA11725{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        int[] tmp;
        for (int i = 0; i < N - 1; i++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        System.out.print(new 트리의부모찾기_11725(N, graph).solve());
    }
}