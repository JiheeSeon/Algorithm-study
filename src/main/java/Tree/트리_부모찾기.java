package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/*
11725 다시 풀어보기!

문제 분석
어떤 게 부모이고 자식인지 상하관게를 알 수 없다
-> 1이 루트 = 1과 함께 나오는 숫자는 무조건 1의 자식이 된다.
-> 루트인 1로부터 어느 노드로든 갈 수 있다.

아이디어
- graph로 갈 수 있는 모든 노드를 양방향으로 넣어놓음
- 1로부터 방문할 수 있는 자식 노드를 순회
> 이 때 이미 parent가 정해진 애는 갈 수 없음.
  (내려오는 길에 이미 마킹 된 애므로, 사실 부모인 것)

정리
사실상 preorder로 방문하고 내려가는 구조
parent 배열은 check 와 같은 기능을 수행
*/

class 트리_부모찾기 {
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // initialization
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();

        int[] tmp;
        for(int i = 0; i < N - 1; i++){
            tmp = strToIntArray(br.readLine());
            // 양방향 그래프로 넣어줌
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        StringBuilder stb = new StringBuilder();
        int[] parent = setParent(1, 1, new int[N + 1]);
        for (int i = 2; i <= N; i++) {
            stb.append(parent[i]).append("\n");
        }
        System.out.print(stb);
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static int[] setParent(int parent, int child, int[] parents) {
        if(parents[child] != 0) return parents;

        parents[child] = parent;
        for(int grandChild : graph[child]) {
            setParent(child, grandChild, parents);
        }

        return parents;
    }
}
