import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Main1707 {
    static ArrayList<Integer>[] graph;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));;
        int T = Integer.parseInt(br.readLine());

        for (int m = 0; m < T; m++) {
            int[] VE = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            graph = (ArrayList<Integer>[]) new ArrayList[VE[0] + 1];
            check = new int[VE[0] + 1];

            for (int j = 1; j <= VE[0]; j++)
                graph[j] = new ArrayList<>();

            int[] temp;
            int i;

            for (i = 1; i <= VE[1]; i++) {
                temp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
                graph[temp[0]].add(temp[1]);
                graph[temp[1]].add(temp[0]);
            }

            for (i = 1; i <= VE[0]; i++)
                if (check[i] == 0)
                    dfs(i, check[i]);

            boolean result = true;


            for (i = 1; i <= VE[0]; i++)
                for (int k : graph[i])
                    if (check[i] == check[k])
                        result = false;

            if (result) {
                stb.append("YES").append("\n");
            } else {
                stb.append("NO").append("\n");
            }

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