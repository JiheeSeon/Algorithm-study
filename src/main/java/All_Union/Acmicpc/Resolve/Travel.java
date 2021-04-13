package All_Union.Acmicpc.Resolve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/*
문제
다른 도시를 경유해서 갈 수 있는지 없는지
-> 연결되어있는지 여부만 체크하면 됨. 같은 set인가?
* */
public class Travel {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = IntStream.rangeClosed(0, N).toArray();

        int[] tmp;
        for (int i = 0; i < N; i++) {
            tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            for (int j = i; j < N; j++) {
                if(tmp[j] == 1) union(i + 1, j + 1);
            }
        }

        int[] path = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int ancestor = find(path[0]); boolean flag = true;
        for (int p : path) {
            if(find(p) != ancestor) flag= false;
        }

        System.out.println(flag ? "YES" : "NO");
    }
    static void union(int a, int b){
        int pA = find(a); int pB = find(b);

        if(pA == pB) return;

        if(pA > pB) parent[pA] = parent[pB];
        else parent[pB] = parent[pA];
    }

    static int find(int a) {
        return a == parent[a] ? a : (parent[a] = find(parent[a]));
    }
}
