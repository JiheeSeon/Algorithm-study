package Sort.TopologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class ACMCraft_1005 {
    int N, K, B;
    int[] time; // index - 1
    int[][] rules;

    int[] inDegree;
    ArrayList<Integer>[] graph;
    ArrayList<Integer>[] prev;
    int[] dp;

    public ACMCraft_1005(int n, int k, int b, int[] time, int[][] rules) {
        N = n;
        K = k;
        B = b;
        this.time = time;
        this.rules = rules;

        inDegree = new int[N + 1];
        graph = new ArrayList[N + 1];
        prev= new ArrayList[N + 1];
        dp = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            prev[i] = new ArrayList<>();
        }

        for(int[] rule: rules){
            graph[rule[0]].add(rule[1]);
            prev[rule[1]].add(rule[0]);
            inDegree[rule[1]]++;
        }
    }

    int getAns() {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++){
            if(inDegree[i] == 0) q.add(i);
        }

        int now;
        while (!q.isEmpty()) {
            now = q.poll();

            for(int next: graph[now]){
                inDegree[next]--;
                if(inDegree[next] == 0) q.add(next);
            }

            // distance set
            if(prev[now].isEmpty()) dp[now] = time[now - 1];
            else{
                for (int front : prev[now])
                    dp[now] = Math.max(dp[now], dp[front] + time[now - 1]);
            }

            if(now == B) return dp[B];
        }

        return Arrays.stream(dp).max().getAsInt();
    }
}

class MainA1006{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N, K, B;
        int[] tmp, time;
        int[][] rules;

        StringBuilder stb = new StringBuilder();

        for(int t = 0; t < T; t++){
            tmp = strToIntArr(br.readLine());
            N = tmp[0]; K = tmp[1];
            rules = new int[K][];
            time = strToIntArr(br.readLine());

            for(int k = 0; k < K; k++){
                rules[k] = strToIntArr(br.readLine());
            }
            B = Integer.parseInt(br.readLine());

            stb.append(new ACMCraft_1005(N, K, B, time, rules).getAns()).append("\n");
        }

        System.out.print(stb);
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}