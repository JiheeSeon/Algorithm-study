package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class 나무탈출_15900 {
    int V;
    ArrayList<Integer>[] graph;
    boolean[] check;
    int ans = 0;

    public 나무탈출_15900(int v, ArrayList<Integer>[] graph) {
        V = v;
        this.graph = graph;
        check = new boolean[V + 1];
    }

    // 리프노드로부터의 높이들의 합이 홀수인지
    String solve() {
        backtrack(1, 0);
//        System.out.println(ans);
        return ans % 2 == 1 ? "Yes" : "No";
    }

    void backtrack(int now, int level) {
        check[now] = true;

        int leafChecker = 0;
        for (int next : graph[now]) {
            if(check[next]) continue;

            leafChecker++;
            backtrack(next,level + 1);
        }

        // leaf node 도달했을 때 level 추가 => backtrack
        if(leafChecker == 0) ans += level;
    }

    /*
    [ 기존 로직 ]
    자식노드마다 그를 루트로 하는 트리의 레벨 합 + 1(나와 연결되어있는 브랜치)
    를 최종합에 더해서 return

    [ 틀린 이유 ]
    위에서 공통적으로 타고가는 브랜치라 할지라도,
    어떤 리프노드로 가는 브랜치인지에 따라 부모 브랜치가 중복으로 몇번이고 들어감.
    이는 리프노드에서 최종적으로 결정됨. 아래 가지수에 따라 중복이 몇번 들어갈지 결정
    아래의 서브트리의 level 합만으로는 상위 트리와의 관계성을 명확히 하기 어려움.

    [ 지향점 ]
    결국은 backtrack 중 맨 마지막 리프에서 레벨을 더해주는 방식으로 진행해야 함.
    */

    int wrongRecursiveSolution(int node) {
        check[node] = true;
        int subTreeN = 0;
        int ret = 0;
        int tmp;

        for (int child : graph[node]) {
            if(check[child]) continue;

            tmp = wrongRecursiveSolution(child);
            ret += (tmp + 1);
            subTreeN++;
        }

        return subTreeN == 0 ? 0 : ret;
    }
}

class MainA15900{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 게임말이 게임판에 존재하지 않아 고를 수 없는 사람이 지게 된다.
        int V = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) graph[v] = new ArrayList<>();

        int[] tmp;
        for (int e = 0; e < V - 1; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        System.out.println(new 나무탈출_15900(V, graph).solve());
    }
}
/*
8
8 1
1 4
7 4
6 4
6 5
1 3
2 3
No ()

17
1 8
3 5
14 9
13 9
12 3
8 15
1 4
6 10
11 6
6 2
9 17
7 4
16 8
5 9
6 4
3 1
Yes(29)

17
13 17
14 8
16 13
9 4
4 2
4 8
5 2
10 6
2 6
1 2
11 7
12 7
15 12
3 1
13 7
3 7
Yes(27)
*/