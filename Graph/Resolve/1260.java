package Basic;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class Main1260Resolve {
    static int numOfVertex;
    static int numOfEdge;
    static int startVertex;

    static int[] input;
    static boolean[] visited;
    static boolean[] isInQueue;
    static ArrayList<Integer>[] graph; // i번째 노드와 연결된 정점을 링크드리스트 형태로 가짐

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input = getInputAndSplit();
        numOfVertex = input[0];
        numOfEdge = input[1];
        startVertex = input[2] - 1;
        visited = new boolean[numOfVertex];
        isInQueue = new boolean[numOfVertex];

        graph = (ArrayList<Integer>[]) new ArrayList[numOfVertex];

        for (int i = 0; i < numOfVertex; i++)
            graph[i] = new ArrayList<Integer>();

        int u, v, w;
        for (int i = 0; i < numOfEdge; i++) {
            input = getInputAndSplit();

            u = input[0] - 1;
            v = input[1] - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 0; i < numOfVertex; i++)
            Collections.sort(graph[i]);

        dfs(startVertex);
        stb.append("\n");
        bfs();

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static int[] getInputAndSplit() throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }

    static void dfs(int srcVertex) { // x를 방문했고, log처럼 함수 콜스택에 순차적으로 쌓임
        visited[srcVertex] = true; // 해당 정점과 연결된 정점에 대해 방문 여부 확인하고자
        stb.append(srcVertex + 1).append(" "); //방문 기록 출력

        for (int dstVertex : graph[srcVertex]) {
            if (!visited[dstVertex])
                dfs(dstVertex);
        }
    }

    static void bfs() {
        Queue<Integer> willVisit = new LinkedList<>();

        willVisit.add(startVertex);
        isInQueue[startVertex] = true;

        int visiting;

        while (!willVisit.isEmpty()) {
            visiting = willVisit.poll();
            stb.append(visiting + 1).append(" ");

            for (int nextVertex : graph[visiting]) {
                if (!isInQueue[nextVertex]) { // 연결된 거
                    isInQueue[nextVertex] = true;
                    willVisit.add(nextVertex);
                }
            }
        }
    }
}