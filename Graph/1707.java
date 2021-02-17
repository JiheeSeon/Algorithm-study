import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Main1707 {
    static ArrayList<Integer>[] graph;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int[] VE = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            int numberOfVertex = VE[0];
            int numberOfEdges = VE[1];

            graph = (ArrayList<Integer>[]) new ArrayList[numberOfVertex + 1];
            check = new int[numberOfVertex + 1];

            for (int j = 1; j <= numberOfVertex; j++)
                graph[j] = new ArrayList<>();

            int[] temp;
            int u, v;

            for (i = 1; i <= numberOfEdges; i++) {
                temp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
                u = temp[0];
                v = temp[1];

                graph[u].add(v);
                graph[v].add(u);
            }

            for (i = 1; i <= VE[0]; i++)
                if (check[i] == 0)
                    dfs(i, check[i]);

            boolean result = true;

            for (i = 1; i <= numberOfVertex; i++) {
                for (int k : graph[i]) {
                    if (check[i] == check[k])
                        result = false;
                }
            }

            stb.append(result ? "YES" : "NO").append("\n");
        }
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int topVertex, int checkMode) {
        check[topVertex] = checkMode;

        for (int i : graph[topVertex]) {
            if (check[i] == 0)
                dfs(i, 3 - checkMode);
        }
    }
}