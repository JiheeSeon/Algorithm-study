package Sort.TopologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class 음악프로그램_2623 {
    int N, M;
    ArrayList<Integer>[] graph;
    int[] inDegree;

    public 음악프로그램_2623(int n, int m, int[][] input) {
        N = n;
        M = m;

        inDegree = new int[N + 1];
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for(int j = 0; j < M; j++){
            for(int k = 1; k < input[j][0]; k++) {
                graph[input[j][k]].add(input[j][k + 1]);
                inDegree[input[j][k + 1]]++;
            }
        }
    }

    String getAns() {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++)
            if(inDegree[i] == 0) q.add(i);

        StringBuilder stb = new StringBuilder();
        int now;

        boolean[] check = new boolean[N + 1];

        int idx = 0;
        for(int i = 0; i < N; i++) {
            if(q.isEmpty()) return "0\n";

            now = q.poll();

            check[now] = true;
            stb.append(now).append("\n");

            for(int next: graph[now]){
                inDegree[next]--;
                if(inDegree[next] == 0) q.add(next);
            }
        }
        return stb.toString();
    }
}
class MainA2623{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int N = tmp[0]; int M = tmp[1];
        int[][] input = new int[M][];
        for(int m = 0; m < M; m++)
            input[m] = strToIntArr(br.readLine());

        System.out.print(new 음악프로그램_2623(N, M, input).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}