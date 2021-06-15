package Backtrack.Acmicpc;

import java.io.*;
import java.util.regex.Pattern;
import java.util.*;

/*
문제 요약
길이가 5 이상인 경로가 있는지 확인하는 것
-> 모든 경로를 다 확인해야 함.

Backtracking vs DFS

backtrack
- 경로 중심, 모든 경로를 다 체크

dfs
- 깊게 들어감, 어떤 순서로 Visit 할지 보장된 게 없음.
- 한번에 최대한 깊게 들어가는 순회에 의의를 둠.
*/

class ABCDE_13023 {
    int V, E;
    ArrayList<Integer>[] graph;
    boolean[] check;

    public ABCDE_13023(int V, int E, int[][] relation){
        this.V = V;
        this.E = E;

        graph = new ArrayList[V];
        for(int v = 0; v < V; v++)
            graph[v] = new ArrayList<>();

        for(int e = 0; e < E; e++){
            graph[relation[e][0]].add(relation[e][1]);
            graph[relation[e][1]].add(relation[e][0]);
        }

        check = new boolean[V];
    }

    int backtrack(int nowV, int cnt){
        check[nowV] = true;
        cnt++;

        // 맨 마지막 정점에 도달한 경우 for 문을 안 거치므로
        // 찍은 정점의 개수를 기본으로 깔아주어야 함.
        int max = cnt;

        for(int nextV : graph[nowV]){
            if(check[nextV]) continue;
            max = Math.max(max, backtrack(nextV, cnt)); // 함수 리턴값이 거친 정점의 개수
            if(max >= 5) return max;
        }
        check[nowV] = false; // backtrack

        return max;
    }

    int getAns(){
        int max = 0;
        int ans = 0;

        for(int v = 0; v < V; v++){
            max = Math.max(max, backtrack(v, 0));
            if(max >= 5){
                ans = 1;
                break;
            }
        }

        return ans;
    }
}

class MainA13023 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int V = tmp[0]; int E = tmp[1];
        int[][] relation = new int[E][2];
        for(int e = 0; e < E; e++){
            relation[e] = strToIntArr(br.readLine());
        }

        System.out.println(new ABCDE_13023(V, E, relation).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
/*
Testcase
5 5
0 1
1 3
1 2
2 3
3 4
*/