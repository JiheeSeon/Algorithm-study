import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class Main1260 {
    static boolean[] check;
    static ArrayList<Integer>[] graph;
    static StringBuilder stb = new StringBuilder();
    static int numberOfVertex;
    static int numberOfEdges;
    static int firstVertexToSearch;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] VEF = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        numberOfVertex = VEF[0];
        numberOfEdges = VEF[1];
        firstVertexToSearch = VEF[2];
        check = new boolean[numberOfVertex + 1];
        graph = (ArrayList<Integer>[]) new ArrayList[numberOfVertex + 1];

        for (int i = 1; i <= numberOfVertex; i++)
            graph[i] = new ArrayList<Integer>();

        int[] edgeInput;
        int u, v;
        for (int i = 1; i <= numberOfEdges; i++) {
            edgeInput = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            u = edgeInput[0];
            v = edgeInput[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <= numberOfVertex; i++)
            Collections.sort(graph[i]); // 각 노드마다 연결된 간선(노드) 재배열

        dfs(firstVertexToSearch);
        stb.append("\n");
        bfs(firstVertexToSearch);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int topVertex) {
        if (check[topVertex])
            return; // pop

        check[topVertex] = true;
        stb.append(topVertex).append(" ");

        for (int i : graph[topVertex])
            if (!check[i])
                dfs(i); // push
    }

    static void bfs(int startVertex) {
        check = new boolean[numberOfVertex + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(startVertex);
        check[startVertex] = true;

        while (!q.isEmpty()) {
            int frontVertex = q.remove();
            stb.append(frontVertex).append(" ");

            for (int i : graph[frontVertex]) {
                if (!check[i]) {
                    check[i] = true;
                    q.add(i);
                }
            }
        }
    }
}