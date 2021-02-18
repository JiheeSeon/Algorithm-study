import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

class Main2667 {
    static int[][] input;
    static int[][] check;
    static int[] dy = {1, -1, 0, 0}; //하상좌우
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        check = new int[N + 1][N + 1];
        input = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++)
            input[i] = Pattern.compile("").splitAsStream("0" + br.readLine()).mapToInt(Integer::parseInt).limit(N + 1).toArray();


        int num = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (check[i][j] == 0 && input[i][j] != 0) {
                    num++;
                    dfs(i, j, num);
                }
            }
        }

        int[] res = new int[num + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (check[i][j] != 0) res[check[i][j]]++;
            }
        }

        Arrays.sort(res);

        stb.append(Integer.toString(num)).append("\n");

        for (int i = 1; i <= num; i++) {
            if (res[i] != 0)
                stb.append(res[i]).append("\n");
        }
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int y, int x, int apt) {
        if (y <= 0 || x <= 0 || y >= input[1].length || x >= input[1].length) return; //삐져나가는 경우
        if (input[y][x] == 0) return; //단지 인지
        if (check[y][x] != 0) return; //이미 간 데인지
        check[y][x] = apt;

        for (int i = 0; i < 4; i++) {
            dfs(y + dy[i], x + dx[i], apt);
        }
    }
}