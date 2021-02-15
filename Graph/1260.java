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

        int[] NMV = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        numberOfVertex = NMV[0];
        numberOfEdges = NMV[1];
        firstVertexToSearch = NMV[2];
        check = new boolean[numberOfVertex + 1];
        graph = (ArrayList<Integer>[]) new ArrayList[numberOfVertex + 1];

        for (int i = 1; i <= numberOfVertex; i++)
            graph[i] = new ArrayList<Integer>();


        int[] edgeInput;
        for (int i = 1; i <= numberOfEdges; i++) {
            edgeInput = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            graph[edgeInput[0]].add(edgeInput[1]);
            graph[edgeInput[1]].add(edgeInput[0]);
        }

        for (int i = 1; i <= numberOfVertex; i++)
            Collections.sort(graph[i]);

        dfs(firstVertexToSearch);
        stb.append("\n");
        bfs(firstVertexToSearch);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int topVertex) {
        if(check[topVertex])
            return;

        check[topVertex] = true;
        stb.append(topVertex).append(" ");

        for(int i : graph[topVertex])
            if(!check[i])
                dfs(i);
    }

    static void bfs(int startVertex) {
        check = new boolean[numberOfVertex + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(startVertex);
        check[startVertex] = true;

        while(!q.isEmpty()){
            int frontVertex = q.remove();
            stb.append(frontVertex).append(" ");

            for(int i : graph[frontVertex]){
                if (!check[i]) {
                    check[i] = true;
                    q.add(i);
                }
            }
        }
    }
}