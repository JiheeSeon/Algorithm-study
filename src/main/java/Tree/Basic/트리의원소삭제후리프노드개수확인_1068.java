package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 트리의원소삭제후리프노드개수확인_1068 {
    int N, D, root;
    int[] parent;
    ArrayList<Integer>[] topDownGraph;
    Set<Integer> leafSet;

    public 트리의원소삭제후리프노드개수확인_1068(int n, int d, int[] parent) {
        N = n;
        D = d;
        this.parent = parent;
    }

    int solve() {
        setTopDownGraph();
        if(root == D) return 0;

        // set leaf
        leafSet = IntStream.range(0, N).boxed().collect(Collectors.toCollection(HashSet::new));
        for(int idx = 0; idx < N; idx++) leafSet.remove(parent[idx]);

        dfs(root, false);
        return leafSet.size();
    }

    void setTopDownGraph() {
        topDownGraph = new ArrayList[N];
        for(int i = 0; i < N; i++) topDownGraph[i] = new ArrayList<>();

        root = -1;
        for(int idx = 0; idx < N; idx++){
            if(parent[idx] == -1){
                root = idx;
                continue;
            }
            topDownGraph[parent[idx]].add(idx);
        }
    }

    void dfs(int now, boolean deleteFlag) {
        if(now == D) deleteFlag = true;

        // 없애야 하는 노드의 줄기쪽에 있는 단말노드일 때
        if(topDownGraph[now].size() == 0 && deleteFlag){
            leafSet.remove(now); // 자기 자신 = 단말노드를 leafSet에서 제거
            // D만 자식으로 둔 경우 부모가 리프노드가 됨 -> leafSet에 추가
            if(topDownGraph[parent[D]].size() == 1) leafSet.add(parent[D]);
            return;
        }

        // internal node인 경우
        for (int child : topDownGraph[now]) {
            dfs(child, deleteFlag);
        }
    }
}

class MainA1068{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] parent = InputProcessor.strToIntArr(br.readLine());
        int D = Integer.parseInt(br.readLine());

        System.out.println(new 트리의원소삭제후리프노드개수확인_1068(N, D, parent).solve());
    }
}
/*
12
9 10 1 2 2 2 4 4 5 10 -1 9
4
4
*/