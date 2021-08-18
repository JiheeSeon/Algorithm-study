package Tree.Basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class K진트리_11812 {
    long N, K;
    HashMap<Long, Long>[] parent;
    final int LGN;
    HashMap<Pair, Integer> result;

    public K진트리_11812(long n, long k) {
        N = n;
        K = k;
        LGN = (int)(Math.log10(getLevel(N)) / Math.log10(2)) + 1;

        if(K != 1) {
            parent = new HashMap[LGN];
            for (int i = 0; i < parent.length; i++) parent[i] = new HashMap<>();
            for (HashMap<Long, Long> longLongHashMap : parent) longLongHashMap.put(1L, -1L);
        }

        result = new HashMap<>();
    }

    private int getLevel(long a) {
        return (int)Math.ceil(Math.log10(a * (K - 1) + 1) / Math.log10(K)) - 1;
    }

    long solve(long a, long b) {
        if(K == 1) return b > a ? b - a : a - b;

        Pair pair = new Pair(a, b);
        if(result.containsKey(pair)){
            return result.get(pair);
        }

        int levelA = getLevel(a);
        int levelB = getLevel(b);

        // swap
        if(a > b){
            long tmp = a;
            int levelTmp = levelA;
            a = b;
            levelA = levelB;
            b = tmp;
            levelB = levelTmp;
        }

        // bottom up traverse for b
        ArrayList<Long> visitedNodes = new ArrayList<>();
        bottomUpDfs(b, visitedNodes);
        setSparseMap(visitedNodes);

        // bottom up traverse for a
        visitedNodes = new ArrayList<>();
        bottomUpDfs(a, visitedNodes);
        setSparseMap(visitedNodes);

        long deltaLevel = levelB - levelA;
        long digit = (long)(Math.log10(deltaLevel)/Math.log10(2)) + 1;
        long ancestorA = a, ancestorB = b;

        // 노드 a와 level 같은 조상으로 대치
        while (--digit >= 0) {
            if ((deltaLevel & (1L << digit)) != 0) {
                ancestorB = parent[(int) digit].get(ancestorB) == -1 ? -1 : parent[(int) digit].get(ancestorB);
                if(ancestorB == 1) break;
            }
        }

        if(a == ancestorB){
            result.put(pair, levelB - levelA);
            return result.get(pair);
        }


        // b와 a의 공통조상 찾기
        digit = (long) (Math.log10(levelA) / Math.log10(2)) + 1;
        while (--digit >= 0) {
            if(Objects.equals(parent[(int) digit].get(ancestorA), parent[(int) digit].get(ancestorB))) continue;

            ancestorA = parent[(int) digit].get(ancestorA);
            ancestorB = parent[(int) digit].get(ancestorB);
        }

        result.put(pair, getLevel(a) + 1 - getLevel(ancestorA) + getLevel(b) + 1 - getLevel(ancestorB));
        return result.get(pair);
    }

    private void bottomUpDfs(long now, ArrayList<Long> visitedNodes) {
        if(parent[0].containsKey(now)) return;

        visitedNodes.add(now);
        long p = (now + (K - 2)) / K;
        parent[0].put(now, p);

        bottomUpDfs(p, visitedNodes);
    }

    private void setSparseMap(ArrayList<Long> nodes) {
        for (int i = 1; i < parent.length; i++) {
            for (long node : nodes) {
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
        long N = tmp[0]; long K = tmp[1]; long Q = tmp[2];

        K진트리_11812 solution = new K진트리_11812(N, K);
        StringBuilder stb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();
            stb.append(solution.solve(tmp[0], tmp[1])).append("\n");
        }
        System.out.print(stb);
    }
}
