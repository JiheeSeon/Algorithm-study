package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class Main2606 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parent = IntStream.rangeClosed(0, N).toArray();

        int[] tmp;
        for (int i = 0; i < M; i++) {
            tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            union(tmp[0], tmp[1]);
        }

        int ans = 0;
        for (int i = 2; i < parent.length; i++) {
            if(find(parent[i]) == 1) ans++;
        }
        System.out.println(ans);
    }

    static void union(int a, int b){
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return;

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;
    }

    static int find(int a) {
        return a == parent[a] ? a : (parent[a] = find(parent[a]));
    }
}