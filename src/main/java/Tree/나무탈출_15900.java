package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

/*Idea -> depth 를 더한다
 leaf에서 root로부터의 거리를 다 더하는 문제

 approach 1. parent + leaf traverse
 1. 각 노드의 상하관계 = parent 세팅
 2. leaf 를 구해서 leaf 로부터의 level 더하기

 approach 2. DFS
 parent는 check 배열과 같은 기능을 하고
 그냥 dfs 돌리고 아무데도 갈 수 없는 상황(리프)에서 depth 더해줌
*/
class 나무탈출_15900 {
    static int[] parent;
    static ArrayList<Integer>[] graph;
    static int depthSum = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        graph = new ArrayList[N +1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] tmp;
        for(int i = 0; i < N - 1 ; i++) {
            tmp = strToIntArray(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }
        setParent(1, 1, 0);
        System.out.println(depthSum % 2 == 0 ? "No" : "Yes");
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static void setParent(int now, int prev, int depth){
        if(parent[now] != 0) return;

        parent[now] = prev;

        int validLoop = 0;
        for (int i : graph[now]) {
            if(parent[i] == 0) {
                setParent(i, now, depth + 1);
                validLoop++;
            }
        }
        if(validLoop == 0) depthSum += depth;
    }

    static int getPathLength(int now, int len){
        if(now == 1) return len;
        return getPathLength(parent[now], len + 1);
    }

    private static class LeafInfo{
        int depth, number;

        public LeafInfo(int depth, int number) {
            this.depth = depth;
            this.number = number;
        }
    }
}
