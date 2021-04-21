package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class 연결요소의개수_11724_UnionFind {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmp = strToIntArray(br.readLine());
        int N = tmp[0], M = tmp[1];
        parent = IntStream.rangeClosed(0, N).toArray();

        for (int i = 0; i < M; i++) {
            tmp = strToIntArray(br.readLine());
            union(tmp[0], tmp[1]);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            int p = find(i);
            set.add(p);
        }

        System.out.println(set.size());
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;
    }
    static int find(int a) {
        return a == parent[a] ? a : find(parent[a]);
    }
}
