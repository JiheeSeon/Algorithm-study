package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

class 촌수계산_2644_DFS {
    static ArrayList<Integer>[] graph;
    static boolean[] check1;
    static int[] check2;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        check1 = new boolean[N];
        check2 = new int[N];

        int[] tmp = strToIntArray(br.readLine());
        int start = tmp[0] - 1; int end = tmp[1] - 1;

        int M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            tmp = strToIntArray(br.readLine());
            graph[tmp[0] - 1].add(tmp[1] - 1);
            graph[tmp[1] - 1].add(tmp[0] - 1);
        }

        dfs2(start, start, end);
        System.out.println(check2[end] == 0 ? -1 : check2[end]);
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    /* Solution 1
        level을 통해 깊이를 저장 -> backtracking와 유사, DFS스러운 방식
        개인적으로는 DFS스러운 solution 1의 형태를 좀 더 권장
    */
    static void dfs1(int curr, int target, int level) {
        if(!check1[curr] && curr == target){
            check1[curr] = true;
            result = level;
            return;
        } else if(check1[curr]) return;

        check1[curr] = true;

        for(int i : graph[curr]) {
            dfs1(i, target, level + 1);
        }
    }

    /*Solution 2
      다소 BFS 스러운 풀이 방식 - 이전 level을 check에 저장해서 1 더하는 방식
      BFS는 현재와 다음 넣을 값을 알고 큐에 넣는 한편
      DFS는 이전 값을 알려면 변수를 따로 받아야 함.
    */
    static void dfs2(int curr, int before, int target) {
        if(check2[curr] == 0 && curr == target){
            check2[curr] = check2[before];
            return;
        } else if(check2[curr] != 0) return;

        check2[curr] = check2[before] + 1; // 다소 BFS스러운 풀이방식

        for(int i : graph[curr]) {
            dfs2(i, curr, target);
        }
    }
}
