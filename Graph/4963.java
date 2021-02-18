import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main4963 {
    static int[][] graph;
    static int[][] check;
    static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();

        int[] wh;
        int w, h;

        while (true) {
            wh = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            w = wh[0]; h = wh[1];

            if (w == 0 && h== 0)
                break;

            graph = new int[h][w];
            check = new int[h][w];

            for (int i = 0; i < h; i++)
                graph[i] = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();


            int num = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (check[i][j] == 0 && graph[i][j] != 0) {
                        num++;
                        dfs(i, j, num);
                    }
                }
            }
            stb.append(num).append("\n");
        }

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int y, int x, int apt) {
//        System.out.println(graph[0].length);
        if (y < 0 || x < 0 || y >= graph.length || x >= graph[0].length) return; //삐져나가는 경우
        if (graph[y][x] == 0) return; //단지 인지
        if (check[y][x] != 0) return; //이미 간 데인지
        check[y][x] = apt;

        for (int i = 0; i < 8; i++) {
            dfs(y + dy[i], x + dx[i], apt);
        }
    }

}