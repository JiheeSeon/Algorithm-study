package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class Main11717{
    static int[] parent;
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        int N = input[0]; int numOfOp = input[1];
        parent = IntStream.rangeClosed(0, N).toArray();

        int[] tmp;
        for (int i = 0; i < numOfOp; i++) {
            tmp =  Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            if(tmp[0] == 0) union(tmp[1], tmp[2]);
            else stb.append(find(tmp[1]) == find(tmp[2]) ? "YES" : "NO").append("\n");
        }
        System.out.println(stb.toString());
    }

    static void union(int x, int y) {
        int pX = find(x);
        int pY = find(y);

        if(pX == pY) return;

        if(pX < pY) parent[pY] = pX;
        else parent[pX] = pY;
    }
    static int find(int x){
        return x == parent[x]
                ? x
                : (parent[x] = find(parent[x]));
    }
}