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
    HashMap<Pair, Integer> result;
    Map<Long, Integer> level;

    public K진트리_11812(long n, int k) {
        N = n;
        K = k;
        LGN = (int)(Math.log10(getLevel(N)) / Math.log10(2));

        if(K != 1) {
            parent = new HashMap[LGN + 1];
            for (int i = 0; i < parent.length; i++) parent[i] = new HashMap<>();
            for (HashMap<Long, Long> longLongHashMap : parent) longLongHashMap.put(1L, -1L);
        }

        result = new HashMap<>();
        level = new HashMap<>();
        level.put(1L, 0);
    }

    long solve(long a, long b) {
        if(K == 1) return Math.abs(b - a);

        Pair pair = new Pair(a, b);
        if(result.containsKey(pair)) return result.get(pair);

        // swap
        if(a > b) { long tmp = a; a = b; b = tmp; }

        int levelB, levelA;
        ArrayList<Long> visitedNodes;

        // get level & set sparse map with bottom-up traverse
        // 1. for b
        if(level.containsKey(b)) levelB = level.get(b);
        else {
            visitedNodes = new ArrayList<>();
            levelB = bottomUpDfs(b, visitedNodes);
            setSparseMap(visitedNodes);
        }

        // 2. for a
        if(level.containsKey(a)) levelA = level.get(a);
        else {
            visitedNodes = new ArrayList<>();
            levelA = bottomUpDfs(a, visitedNodes);
            setSparseMap(visitedNodes);
        }

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
            return result.get(pair);
        }


        // b와 a의 공통조상 찾기
        digit = (int) (Math.log10(levelA) / Math.log10(2)) + 1;
        while (--digit >= 0) {
            if(Objects.equals(parent[digit].get(ancestorA), parent[digit].get(ancestorB))) continue;

            ancestorA = parent[digit].get(ancestorA);
            ancestorB = parent[digit].get(ancestorB);
            res += 2 * (1 << digit);
        }

        result.put(pair, res + 2);
        return result.get(pair);
    }

    private int getLevel(long a) {
        return (int)Math.ceil(Math.log10(a * (K - 1) + 1) / Math.log10(K)) - 1;
    }

    private int bottomUpDfs(long now, ArrayList<Long> visitedNodes) {
        if(parent[0].containsKey(now) && level.containsKey(now)) return level.get(now);

        visitedNodes.add(now);
        long p = (now + (K - 2)) / K;
        parent[0].put(now, p);
        level.put(now, bottomUpDfs(p, visitedNodes) + 1);
//        level.computeIfAbsent(now, s -> bottomUpDfs(p, visitedNodes) + 1);

        return level.get(now);
    }

    private void setSparseMap(ArrayList<Long> nodes) {
        for (long node : nodes) {
            for (int i = 1; i < parent.length; i++) {
                parent[i].put(node, parent[i - 1].get(node) == -1 ? -1 : parent[i - 1].get(parent[i - 1].get(node)));
            }
        }
    }

    private class Pair{
        long a, b;

        public Pair(long a, long b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
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
30 4 5
29 8
29 13
14 5
8 29
13 5
3
5
3
3
3
*/