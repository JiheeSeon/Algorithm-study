package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class 트리의지름_1167 {
    int V;
    ArrayList<EdgeA1167>[] graph;

    int maxWeight;
    int maxPoint;

    public 트리의지름_1167(int v, ArrayList<EdgeA1167>[] graph) {
        V = v;
        this.graph = graph;
    }

    int solve() {
        // 루트노드를 알 수 없는 경우 -> 임의의 노드로부터의 거리가 최대인 정점
        maxWeight = -1;
        dfsToGetMaxPoint(1, 0, new boolean[V + 1]);

        maxWeight = -1;
        dfsToGetMaxPoint(maxPoint, 0, new boolean[V + 1]);

        return maxWeight;
    }

    void dfsToGetMaxPoint(int now, int weightAcc, boolean[] check) {
        check[now] = true;

        int childBranchCnt = 0;
        for (EdgeA1167 branchInfo : graph[now]) {
            if(check[branchInfo.childNode]) continue;

            childBranchCnt++;
            dfsToGetMaxPoint(branchInfo.childNode, weightAcc + branchInfo.weight, check);
        }

        // when reaches to leaf node
        if(childBranchCnt == 0){
            if(maxWeight < weightAcc){
                maxPoint = now;
                maxWeight = weightAcc;
            }
        }
    }
}

class EdgeA1167 {
    int childNode, weight;

    public EdgeA1167(int childNode, int weight) {
        this.childNode = childNode;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{" +
                "childNode=" + childNode +
                ", weight=" + weight +
                '}';
    }
}

class MainA1167{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        ArrayList<EdgeA1167>[] graph = new ArrayList[V + 1];
        for(int vIdx = 1; vIdx <= V; vIdx++)
            graph[vIdx] = new ArrayList<>();

        int v; int[] tmp;
        for (int vCnt = 0; vCnt < V; vCnt++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            v = tmp[0];

            for(int i = 1; i < tmp.length - 1; i += 2)
                graph[v].add(new EdgeA1167(tmp[i], tmp[i + 1]));
        }

        System.out.println(new 트리의지름_1167(V, graph).solve());
    }
}