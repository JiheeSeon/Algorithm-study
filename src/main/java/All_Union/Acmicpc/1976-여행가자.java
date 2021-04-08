package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class Main1976{
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parent = IntStream.rangeClosed(0, N).toArray();

        int M = Integer.parseInt(br.readLine());

        int[] tmp;
        for (int i = 1; i <= N; i++) {
            tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j <= tmp.length; j++){
                if(tmp[j - 1] == 1) union(i, j);
            }
        }
        tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        int previous = find(tmp[0]);
        boolean ifYes = true;
        for(int i = 1; i < tmp.length; i++){
            if(find(tmp[i]) != previous){
                ifYes = false;
                break;
            }
        }
        System.out.println(ifYes ? "YES" : "NO");

    }

    static void union(int x, int y) {
        int pX = find(x);
        int pY = find(y);

        if(pX == pY) return;

        if(pX > pY) parent[pX] = pY;
        else parent[pY] = pX;
    }

    static int find(int x) {
        return x == parent[x]
                ? x : (parent[x] = find(parent[x]));
    }
}
//5
//5
//0 1 0 0 1
//1 0 1 1 0
//0 1 0 0 0
//0 1 0 0 0
//1 0 0 0 0
//5 3 2 3 4