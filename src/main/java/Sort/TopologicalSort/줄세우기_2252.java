package Sort.TopologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class 줄세우기_2252 {
    int N, M;
    int[][] input;

    ArrayList<Integer>[] graph;
    int[] inDegree;

    public 줄세우기_2252(int n, int m, int[][] input) {
        N = n;
        M = m;
        this.input = input;

        inDegree = new int[N + 1];
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int[] pair : input) {
            graph[pair[0]].add(pair[1]);
            inDegree[pair[1]]++;
        }
    }

    String getAns() {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) q.add(i);
        }

        int now;
        StringBuilder stb = new StringBuilder();
        while (!q.isEmpty()) {
            now = q.poll();
            stb.append(now).append(" ");

            for(int next: graph[now]){
                inDegree[next]--;
                if(inDegree[next] == 0) q.add(next);
            }
        }
        return stb.toString();
    }
}
class MainA2252{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int N = tmp[0]; int M = tmp[1];

        int[][] input = new int[M][2];
        for(int m = 0; m < M; m++)
            input[m] = strToIntArr(br.readLine());

        System.out.println(new 줄세우기_2252(N, M, input).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
