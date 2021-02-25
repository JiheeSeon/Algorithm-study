package Graph;

import java.io.*;
import java.util.regex.Pattern;

class Main10451 {
    static boolean[] check;
    static int[] nextNodes;
    static int numberOfCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();

        int numberOfTestCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < numberOfTestCase; i++) {
            int numberOfNodes = Integer.parseInt(br.readLine());

            numberOfCycle = 0;
            check = new boolean[numberOfNodes + 1];

            nextNodes = Pattern.compile(" ").splitAsStream("0 " + br.readLine()).mapToInt(Integer::parseInt).limit(numberOfNodes + 1).toArray();

            for (int j = 1; j <= numberOfNodes; j++) {
                if (!check[j]) {
                    dfs(j);
                    numberOfCycle++;
                }
            }
            stb.append(numberOfCycle).append("\n");
        }

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int vertex) {
        if (check[vertex]) return;

        check[vertex] = true;

        if (!check[nextNodes[vertex]])
            dfs(nextNodes[vertex]);
    }

}