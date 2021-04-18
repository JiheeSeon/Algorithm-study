package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class Main20040{
    static int[] parent;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        int[] tmp = strToIntArray(br.readLine(), " ");
        N = tmp[0]; M = tmp[1];
        parent = IntStream.range(0, N).toArray();

        for (int i = 0; i < M; i++) {
            tmp = strToIntArray(br.readLine(), " ");
            union(tmp[0], tmp[1], i + 1);
        }
        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
    }

    static void union(int a, int b, int order) {
        int pA = find(a);
        int pB = find(b);

        if(pA == pB){
            ans = Math.min(ans, order);
            return;
        }

        if(pA > pB) parent[pA] = pB;
        else parent[pB] = pA;
    }

    static int find(int a) {
        return a == parent[a] ? a : find(parent[a]);
    }

    static int[] strToIntArray(String s, String delimiter) {
        return Pattern.compile(delimiter).splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}