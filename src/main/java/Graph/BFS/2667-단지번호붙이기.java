package Graph.BFS;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class Main2667{
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stb = new StringBuilder();

    static int[][] graph;
    static int[][] check;

    static int aptN = 1;
    static Queue<Integer> q = new LinkedList<>();

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        check = new int[N][N];

        for(int i = 0; i < N; i++)
            graph[i] = getStringToIntArray();

        for(int startPoint = 0; startPoint < N * N; startPoint++)
            bfs(startPoint);

        int[] result = new int[aptN];

        for(int y = 0; y < N; y++){
            for(int x = 0; x < N; x++){
                result[check[y][x]]++;
            }
        }

        Arrays.sort(result, 1, aptN);

        stb.append(aptN - 1).append("\n");
        for(int i = 1; i < aptN; i++)
            stb.append(result[i]).append("\n");

        System.out.println(stb);
    }
    static void bfs(int startPoint){
        int startY = startPoint / N, startX = startPoint % N;
        if (graph[startY][startX] == 0 || check[startY][startX] != 0) return;

        q.add(startPoint);
        check[startY][startX] = aptN;

        int current, currY, currX;
        int nextY, nextX;

        while(!q.isEmpty()) {
            current = q.poll();

            currY = current / N; currX = current % N;

            for (int i = 0; i < 4; i++) {
                nextY = currY + dy[i]; nextX = currX + dx[i];

                if (nextY < 0 || nextY >= N
                        || nextX < 0 || nextX >= N) continue;
                if (graph[nextY][nextX] == 0 || check[nextY][nextX] != 0) continue;

                check[nextY][nextX] = aptN;
                q.add(nextY * N + nextX);
            }
        }
        aptN++;
    }
    static int[] getStringToIntArray() throws IOException{
        return Pattern.compile("").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }
}