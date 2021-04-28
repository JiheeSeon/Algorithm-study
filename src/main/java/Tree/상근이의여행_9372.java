package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

class 상근이의여행_9372 {
    static boolean[] check;
    static ArrayList<Integer>[] graph;
    static int a = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int nationN, planeN;

        int[] tmp;
        StringBuilder stb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            tmp = strToIntArray(br.readLine());
            nationN = tmp[0]; planeN = tmp[1];

            // initialization of graph
            check = new boolean[nationN + 1];
            graph = new ArrayList[nationN + 1];

            for(int i = 0; i < nationN + 1; i++)
                graph[i] = new ArrayList<>();

            for(int p = 0; p < planeN; p++){
                tmp = strToIntArray(br.readLine());
                graph[tmp[0]].add(tmp[1]);
                graph[tmp[1]].add(tmp[0]);
            }

            stb.append(makeTree(1, 0)).append("\n");
            System.out.println();
        }
        System.out.println(stb);
    }

    static int makeTree(int curr, int ans) {
        if(check[curr]) return ans;

        check[curr] = true; ans++;

        ArrayList<Integer> g = graph[curr];

        for(int next : g){
            if(!check[next])
                ans = makeTree(next, ans);

//            System.out.println("next = " + next +", ans = " + ans);
        }
        return ans;
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
