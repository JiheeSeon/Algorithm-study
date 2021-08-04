package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 가장가까운공통조상_3584 {
    int V, root;
    ArrayList<Integer>[] tree;
    int[][] parent;
    int[] levels;
    int depthOfTree = -1;
    int lgOfDepth;

    public 가장가까운공통조상_3584(int v, int root, ArrayList<Integer>[] tree) {
        V = v;
        this.root = root;
        this.tree = tree;
        levels = new int[V + 1];
    }

    int[] dfs(int now, int level, boolean[] check, int[] parent) {
        check[now] = true;
        levels[now] = level;
        if(level > depthOfTree) depthOfTree = level;

        for (int child : tree[now]) {
            if(check[child]) continue;

            parent[child] = now;
            dfs(child, level + 1, check, parent);
        }
        return parent;
    }

    void init() {
        int[] p0 = dfs(root, 0, new boolean[V + 1], new int[V + 1]);
        lgOfDepth = (int)(Math.log10(depthOfTree) / Math.log10(2));
        parent = new int[lgOfDepth + 1][V + 1];
        parent[0] = p0.clone();
        parent[0][root] = -1;

        for (int i = 1; i <= lgOfDepth; i++) {
            Arrays.fill(parent[i], -1);
        }

        for (int lgLv = 1; lgLv <= lgOfDepth; lgLv++) {
            for (int v = 1; v <= V; v++) {
                // 0% IndexOutOfBounds -> 50% IndexOutOfBounds
                parent[lgLv][v] = parent[lgLv - 1][v] == -1 ? -1 : parent[lgLv - 1][parent[lgLv - 1][v]];
            }
        }
    }

    int solve(int A, int B) {
        init();

        // levels[a] < levels[b] 가 되도록 만들기
        if(levels[A] > levels[B]){
            int tmp = A; A = B; B = tmp;
        }

        // adjust level of b -> make b ancestor of b same level with a
        int diffLevel = levels[B] - levels[A];
        int lgOfDiffLevel = (int)(Math.log10(diffLevel)/Math.log10(2)) + 1;

        while(--lgOfDiffLevel >= 0){
            if(parent[lgOfDiffLevel][B] == 0) return 1;

            if((diffLevel & (1 << lgOfDiffLevel)) != 0){
                B = parent[lgOfDiffLevel][B] == -1 ? -1 : parent[lgOfDiffLevel][B];
                if(B == -1) return 1;
            }
        }

        if(A == B) return A;

        int lgOfBothLevel = (int)(Math.log10(A) / Math.log10(2)) + 1;

        // parametric search
        while (--lgOfBothLevel >= 0) {
            if(parent[lgOfBothLevel][A] == parent[lgOfBothLevel][B]) continue;

            A = parent[lgOfBothLevel][A];
            B = parent[lgOfBothLevel][B];
        }

        return parent[0][A];
    }
}

class MainA3584{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int V, root; int[] tmp;
        LinkedList<Integer> rootCandidates;
        ArrayList<Integer>[] tree;
        StringBuilder stb = new StringBuilder();

        while (--T >= 0) {
            V = Integer.parseInt(br.readLine());
            tree = new ArrayList[V + 1];
            for(int v = 1; v <= V; v++) tree[v] = new ArrayList<>();
            rootCandidates = IntStream.rangeClosed(1, V).boxed().collect(Collectors.toCollection(LinkedList::new));

            for (int e = 0; e < V - 1; e++) {
                tmp = InputProcessor.strToIntArr(br.readLine());
                rootCandidates.remove((Object)tmp[1]);
                tree[tmp[0]].add(tmp[1]);
            }

            root = rootCandidates.get(0);
            tmp = InputProcessor.strToIntArr(br.readLine());
            stb.append(new 가장가까운공통조상_3584(V, root, tree).solve(tmp[0], tmp[1])).append("\n");
        }

        System.out.print(stb);
    }
}