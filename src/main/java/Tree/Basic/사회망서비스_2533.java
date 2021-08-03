package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class 사회망서비스_2533 {
    int V;
    int[][] dp;
    ArrayList<Integer>[] graphOriginal;
    ArrayList<Integer>[] graph;

    public 사회망서비스_2533(int v, ArrayList<Integer>[] graphOriginal) {
        V = v;
        this.graphOriginal = graphOriginal;
        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    void topDownDfs(int now, boolean[] check) {
        check[now] = true;

        for (int child : graphOriginal[now]) {
            if(check[child]) continue;

            graph[now].add(child);
            topDownDfs(child, check);
        }
    }

    int solve() {
        topDownDfs(1, new boolean[V + 1]);

        dp = new int[V + 1][2];
        for (int v = 1; v <= V; v++) Arrays.fill(dp[v], -1);

        return Math.min(solve(1, 0), solve(1, 1));
    }

    /*
    루트 노드가 root고, 본인 노드의 상태(얼리어답터인지)가 주어질 때
    서브트리에서 필요한 얼리어댑터의 최소 수
    */
    int solve(int root, int isEarlyAdapter) {
        // 자식이 없는 리프노드일 경우
        if (graph[root].isEmpty()) {
            return isEarlyAdapter;
        }

        // 이미 구해진 값이 dp 배열에 저장되어 있는 경우
        if(dp[root][isEarlyAdapter] != -1)
            return dp[root][isEarlyAdapter];

        dp[root][isEarlyAdapter] = isEarlyAdapter;

        if(isEarlyAdapter == 1){
            // 본인이 얼리어답터이면, 자식은 얼리어답터여도 되고 아니어도 된다.
            for (int child : graph[root]) {
                dp[root][isEarlyAdapter] += Math.min(solve(child, 0), solve(child, 1));
            }
        } else{
            // 본인이 얼리어답터가 아니면 자식은 무조건 얼리어답터여야 한다.
            for (int child : graph[root]) {
                dp[root][isEarlyAdapter] += solve(child, 1);
            }
        }

        return dp[root][isEarlyAdapter];
    }
}

class MainA2533{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for(int v = 1; v <= V; v++)
            graph[v] = new ArrayList<>();

        int[] tmp;
        for (int e = 0; e < V - 1; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }
        System.out.println(new 사회망서비스_2533(V, graph).solve());
    }
}