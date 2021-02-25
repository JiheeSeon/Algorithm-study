package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Main11724 {
    static ArrayList<Integer>[] graph;
    static boolean[] check;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int i = 1;
        int[] VE = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int numberOfVertex = VE[0];
        int numberOfEdge = VE[1];

        check = new boolean[numberOfVertex + 1];
        graph = (ArrayList<Integer>[]) new ArrayList[numberOfVertex + 1];

        for (i = 1; i <= numberOfVertex; i++)
            graph[i] = new ArrayList<>();

        int[] temp;
        int u, v;

        for (i = 1; i <= numberOfEdge; i++) {
            temp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            u = temp[0];
            v = temp[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        for (i = 1; i <= numberOfVertex; i++) { //모든 정점을 start vertex로 놓고
            if (!check[i]) {
                dfs(i);
                result += 1;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }

    static void dfs(int topVertex) {
        if (check[topVertex])
            return;

        check[topVertex] = true;

        for (int i : graph[topVertex])
            if (!check[i])
                dfs(i);
    }

}