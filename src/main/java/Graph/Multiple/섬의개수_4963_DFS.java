package Graph.Multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/*
그래프에서 동류 집합의 개수를 셀 때에는 union, bfs, dfs 모두 적용 가능

bfs와 dfs 를 동시에 사용 가능할 때에는 DFS가 더 효율적
union이 더 편한 간단한 연결 그래프인 경우는 제한적이며
일반적인 for문이 아니라 다방면의 탐색이 들어가야 하는 대부분의 경우
Union Find는 DFS/BFS + union find 작업인 반면,
BFS / DFS 는 새로운 스타트노드로부터 탐색할 수 있는 경우의 수를 세면 끝이기 때문에

Union Find << BFS << DFS
*/
class 섬의개수_4963_DFS {
    static int[][] graph;
    static boolean[][] check;
    static int yHeight, xWidth;

    final static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    final static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        String input;
        int[] tmp;

        while (!(input = br.readLine()).equals("0 0")) {
            tmp = strToIntArray(input);
            yHeight = tmp[1]; xWidth = tmp[0];

            graph = new int[yHeight][xWidth];
            check = new boolean[yHeight][xWidth];

            for (int y = 0; y < yHeight; y++) {
                graph[y] = strToIntArray(br.readLine());
            }

            int result = 0;
            for (int y = 0; y < yHeight; y++) {
                for (int x = 0; x < xWidth; x++) {
                    if(graph[y][x] == 1 && !check[y][x]){
                        dfs(new Point(y, x));
                        result++;
                    }
                }
            }
            stb.append(result).append("\n");
        }
        System.out.println(stb);
    }

    static void dfs(Point curr) {
        if(curr.y < 0 || curr.x < 0 || curr.y >= yHeight || curr.x >= xWidth
                || graph[curr.y][curr.x] == 0 || check[curr.y][curr.x]) return;

        check[curr.y][curr.x] = true;

        int nextY, nextX;
        for (int i = 0; i < 8; i++) {
            nextY = curr.y + dy[i];
            nextX = curr.x + dx[i];

            dfs(new Point(nextY, nextX));
        }
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    private static class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
