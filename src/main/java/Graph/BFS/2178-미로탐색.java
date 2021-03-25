package Graph.BFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class Main2178{
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int maxY, maxX;
    static boolean[][] visitable;
    static int[][] visited;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        maxY = input[0]; maxX = input[1];

        visitable = new boolean[maxY][maxX];
        visited = new int[maxY][maxX];

        String temp;
        for(int y = 0; y < maxY; y++){
            temp = br.readLine();
            for(int x = 0; x < maxX; x++){ visitable[y][x] = temp.charAt(x) == '1'; }
        }

        bfs();
        System.out.println(visited[maxY - 1][maxX - 1]);
    }

    static void bfs(){
        visited[0][0] = 1; queue.add(0);

        int front; int frontY, frontX;
        while (!queue.isEmpty()){
            front = queue.poll();
            frontY = front / maxX;
            frontX = front % maxX;


            for(int i = 0; i < 4; i++){
                if (frontY + dy[i] < 0 || frontY + dy[i] >= maxY
                        || frontX + dx[i] < 0 || frontX + dx[i] >= maxX) continue;
                if (!visitable[frontY + dy[i]][frontX + dx[i]]
                        || visited[frontY + dy[i]][frontX + dx[i]] != 0) continue;

                visited[frontY + dy[i]][frontX + dx[i]] = visited[frontY][frontX] + 1;
                queue.add((frontY + dy[i]) * maxX + (frontX + dx[i]));
            }
        }
    }
}