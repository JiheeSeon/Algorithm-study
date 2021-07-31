package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class 트리의지름_1967 {
    int V;
    ArrayList<EdgeA1967>[] tree;
    int maxPoint;
    int maxWeight;

    public 트리의지름_1967(int v, ArrayList<EdgeA1967>[] tree) {
        V = v;
        this.tree = tree;
    }

    int solve() {
        // first traverse to find first point
        // 루트로부터 가장 거리가 긴 리프노드 -> 루트를 안 거치는게 최대가 될지라도 최소의 보장수단
        maxWeight = -1;
        findMaxPoint(1, 0, new boolean[V + 1]);

        // second traverse to find max weight
        maxWeight = -1;
        findMaxPoint(maxPoint, 0, new boolean[V + 1]); // set first point to root

        return maxWeight;
    }

    void findMaxPoint(int now, int weightSum, boolean[] check) {
        check[now] = true;

        int childCnt = 0;
        for(EdgeA1967 childInfo: tree[now]){
            if(check[childInfo.childNode]) continue;

            childCnt++;
            findMaxPoint(childInfo.childNode, weightSum + childInfo.weight, check);
        }

        if(childCnt == 0){ // leaf node
            if(weightSum > maxWeight){
                maxPoint = now;
                maxWeight = weightSum;
            }
        }
    }
}

class EdgeA1967 {
    int childNode, weight;

    public EdgeA1967(int childNode, int weight) {
        this.childNode = childNode;
        this.weight = weight;
    }
}

class MainA1967{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());
        ArrayList<EdgeA1967>[] tree = new ArrayList[V + 1];
        for (int v = 1; v <= V; v++) tree[v] = new ArrayList<>();

        int[] tmp;
        for (int e = 0; e < V - 1; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            tree[tmp[0]].add(new EdgeA1967(tmp[1], tmp[2]));
            tree[tmp[1]].add(new EdgeA1967(tmp[0], tmp[2]));
        }
        System.out.println(new 트리의지름_1967(V, tree).solve());
    }
}