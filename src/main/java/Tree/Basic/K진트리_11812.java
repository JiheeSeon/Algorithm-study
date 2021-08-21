package Tree.Basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class K진트리_11812 {
    long N; int K;
    final int LGN;
    HashMap<Long, Long>[] parent;

    HashMap<Pair, Long> LCA;
    HashMap<Pair, Integer> result;

    public K진트리_11812(long n, int k) {
        N = n;
        K = k;
        LGN = (int)(Math.log10(getLevel(N)) / Math.log10(2));

        if(K != 1) {
            parent = new HashMap[LGN + 1];
            for (int i = 0; i < parent.length; i++) parent[i] = new HashMap<>();
            for (HashMap<Long, Long> longLongHashMap : parent) longLongHashMap.put(1L, -1L);
        }

        LCA = new HashMap<>();
        result = new HashMap<>();
    }

    private int getLevel(long a) {
        return (int)Math.ceil(Math.log10(a * (K - 1) + 1) / Math.log10(K)) - 1;
    }

    private void bottomUpDfs(long now, ArrayList<Long> visitedNodes) {
        if(parent[0].containsKey(now)) return;

        visitedNodes.add(now);
        long p = (now + K - 2) / K;
        parent[0].put(now, p);
        bottomUpDfs(p, visitedNodes);
    }

    private void setSparseMap(ArrayList<Long> nodes) {
        long innerVal;

        for (int i = 1; i < parent.length; i++) {
            for (long node : nodes) {
                if(parent[i].containsKey(node)) continue;

                innerVal = parent[i - 1].get(node);

                if(innerVal == -1L) parent[i].put(node, -1L);
                else parent[i].put(node, parent[i - 1].get(innerVal));
            }
        }
    }

    int solve(long a, long b) {
        if(K == 1) return (int) Math.abs(b - a);

        Pair pair = new Pair(a, b);
        if(result.containsKey(pair)) return result.get(pair);
        if(LCA.containsKey(pair)) return getLevel(a) + getLevel(b) - 2 * getLevel(LCA.get(pair));

        // swap
        if(a > b) { long tmp = a; a = b; b = tmp; }

        int levelB, levelA;
        ArrayList<Long> visitedNodes;

        // get level & set sparse map with bottom-up traverse
        // 1. for b
        visitedNodes = new ArrayList<>();
        levelB = getLevel(b);
        bottomUpDfs(b, visitedNodes);
        setSparseMap(visitedNodes);

        // 2. for a
        visitedNodes = new ArrayList<>();
        levelA = getLevel(a);
        bottomUpDfs(a, visitedNodes);
        setSparseMap(visitedNodes);

        int deltaLevel = levelB - levelA;
        int digit = (int)(Math.log10(deltaLevel)/Math.log10(2)) + 1;
        long ancestorA = a, ancestorB = b;

        // make b into same level with a -> 노드 a와 level 같은 조상으로 대치
        int res = deltaLevel;

        while (--digit >= 0) {
            if ((deltaLevel & (1 << digit)) != 0) {
                ancestorB = parent[digit].get(ancestorB) == -1 ? -1 : parent[digit].get(ancestorB);
                if(ancestorB == 1) break;
            }
        }

        if(a == ancestorB){
            result.put(pair, res);
            return res;
        }

        ArrayList<Long> visitedNodesA = new ArrayList<>();
        ArrayList<Long> visitedNodesB = new ArrayList<>();

        long oldA, oldB;

        // b와 a의 공통조상 찾기
        digit = (int) (Math.log10(levelA) / Math.log10(2)) + 1;
        while (--digit >= 0) {
            if(Objects.equals(parent[digit].get(ancestorA), parent[digit].get(ancestorB))) continue;

            oldA = ancestorA;
            oldB = ancestorB;

            ancestorA = parent[digit].get(ancestorA);
            ancestorB = parent[digit].get(ancestorB);

            while (oldA != ancestorA) {
                visitedNodesA.add(oldA);
                oldA = parent[0].get(oldA);
            }

            while (oldB != ancestorB) {
                visitedNodesB.add(oldB);
                oldB = parent[0].get(oldB);
            }

            res += 2 * (1 << digit);
        }
        res += 2;

        long lca = parent[0].get(ancestorA);
        for (long vA : visitedNodesA) {
            for (long vB : visitedNodesB) {
                LCA.put(new Pair(vA, vB), lca);
            }
        }

        result.put(pair, res);
        return res;
    }

    private class Pair{
        long a, b;

        public Pair(long a, long b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Math.min(a, b) == Math.min(pair.a, pair.b) && Math.max(a, b) == Math.max(pair.a, pair.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Math.min(a, b), Math.max(a, b));
        }
    }
}

class MainA11812{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();
        long N = tmp[0]; int K = (int)tmp[1]; int Q = (int)tmp[2];

        K진트리_11812 solution = new K진트리_11812(N, K);
        StringBuilder stb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();
            stb.append(solution.solve(tmp[0], tmp[1])).append("\n");
        }
        System.out.print(stb);
    }
}

/*
TC
30 4 6
29 8
3 13
29 13
14 5
8 29
13 5
3
5
1
3
3
3

30 4 2
1 30
29 1
3
3

1000000000000000 2 3
1 1000000000000000
1000000000000 6
78 1000000000
49
39
35

1000000000000000 2 7
1 1000000000000000
1000000000000 6
78 1000000000
6 1000000000000
39 599999
1000000000000000 1
599999 39
*/