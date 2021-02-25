package Graph.Resolve;

import java.io.*;
import java.util.Comparator;
import java.util.regex.Pattern;

class Main4963Resolve {
    static int islandNum;

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    static int[][] graph;
    static int[][] numbering;

    static int rowSize, colSize;
    static int[] colRow;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int r, c;
        while (true) {
            islandNum = 0;
            colRow = processInput(2);

            rowSize = colRow[1];
            colSize = colRow[0];
            if (rowSize == 0 && colSize == 0) break;

            graph = new int[rowSize][colSize];
            numbering = new int[rowSize][colSize];

            for (r = 0; r < rowSize; r++)
                graph[r] = processInput(colSize);

            for (r = 0; r < rowSize; r++) {
                for (c = 0; c < colSize; c++) {
                    if (graph[r][c] != 0 && numbering[r][c] == 0)
                        dfs(r, c, ++islandNum);
                }
            }

            stb.append(islandNum).append("\n");
        }
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static int[] processInput(int limitSize) throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(limitSize).toArray();
    }

    static void dfs(int x, int y, int island){
        if (x < 0 || x >= rowSize || y < 0 || y >= colSize) return;
        if (numbering[x][y] != 0) return;
        if (graph[x][y] == 0) return;

        numbering[x][y] = island;

        for (int i = 0; i < 8; i++)
            dfs(x + dx[i], y + dy[i], island);
    }
}