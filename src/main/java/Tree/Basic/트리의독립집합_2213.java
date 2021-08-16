package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 트리의독립집합_2213{
    int V;
    int[] weights;
    ArrayList<Integer>[] tree;

    final int IN_SET = 1;
    final int NOT_IN_SET = 0;

    final int REAL_ROOT = 1;
    final int NOMINAL_ROOT = 0;

    IndependentSetInfo[][] setInfos;

    public 트리의독립집합_2213(int V, int[] weights, ArrayList<Integer>[] graph) {
        this.V = V;
        this.weights = weights;

        tree = new ArrayList[V + 1];
        for (int v = 0; v <= V; v++) tree[v] = new ArrayList<>();
        graph[NOMINAL_ROOT].add(REAL_ROOT);

        setTree(NOMINAL_ROOT, graph, new boolean[V + 1]);

        setInfos = new IndependentSetInfo[V + 1][2];
    }

    public void setTree(int now, ArrayList<Integer>[] graph, boolean[] check) {
        check[now] = true;

        for (int child : graph[now]) {
            if(check[child]) continue;

            tree[now].add(child);
            setTree(child, graph, check);
        }
    }

    String solve() {
        IndependentSetInfo rInfo = solve(NOMINAL_ROOT, NOT_IN_SET, new IndependentSetInfo());
        StringBuilder stb = new StringBuilder();
        stb.append(rInfo.setSize).append("\n");
        rInfo.independentSet.forEach(r -> stb.append(r).append(" "));

        return stb.toString();
    }

    // 인자 : 위에서 주는 것 - 방문할 노드, 이번 노드 방문 여부
    private IndependentSetInfo solve(int now, int setFlag, IndependentSetInfo independentSetInfo) {
        if(setInfos[now][setFlag] != null) return setInfos[now][setFlag];

        IndependentSetInfo setInfoIfNotContainNextNode;
        IndependentSetInfo setInfoIfContainNextNode;


        if (setFlag == IN_SET) {
            independentSetInfo.setSize = weights[now];
            independentSetInfo.independentSet.add(now);
        }

        for(int child : tree[now]){
            setInfoIfNotContainNextNode = solve(child, NOT_IN_SET, new IndependentSetInfo());

            //  현재 원소를 포함해야 할 경우
            if (setFlag == IN_SET) {
                independentSetInfo.addAllElements(setInfoIfNotContainNextNode.independentSet);
                independentSetInfo.setSize += independentSetInfo.setSize;
            } else {
                // 현재 원소를 포함하지 않아야 할 경우
                setInfoIfContainNextNode = solve(child, IN_SET, new IndependentSetInfo());

                if (setInfoIfContainNextNode.setSize >= setInfoIfNotContainNextNode.setSize) {
                    independentSetInfo.addAllElements(setInfoIfContainNextNode.independentSet);
                    independentSetInfo.setSize += setInfoIfContainNextNode.setSize;
                } else {
                    independentSetInfo.addAllElements(setInfoIfNotContainNextNode.independentSet);
                    independentSetInfo.setSize += setInfoIfNotContainNextNode.setSize;
                }
            }
        }

        System.out.println(now + " > [" + (setFlag == IN_SET ? "In set" : "Not in set") + "] " + independentSetInfo);

        setInfos[now][setFlag] = independentSetInfo;
        return independentSetInfo;
    }

    private class IndependentSetInfo{
        Set<Integer> independentSet;
        int setSize;

        public IndependentSetInfo() {
            independentSet = new TreeSet<>();
            setSize = 0;
        }

        public void addAllElements(Set<Integer> setToAdd){
            this.independentSet.addAll(setToAdd);
        }

        @Override
        public String toString() {
            return "{" +
                    "independentSet=" + independentSet +
                    " (" + setSize +
                    ") }";
        }
    }
}

class MainA2213{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());
        int[] weights = InputProcessor.strToIntArr("0 " + br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for (int v = 0; v <= V; v++)
            graph[v] = new ArrayList<>();

        int[] tmp;
        for (int e = 0; e < V - 1; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        System.out.println(new 트리의독립집합_2213(V, weights, graph).solve());
    }
}