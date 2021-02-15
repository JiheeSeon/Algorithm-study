import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Main11724 {
    static ArrayList<Integer>[] graph;
    static boolean[] check;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int i = 1;
        int[] VE = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int numberOfVertex = VE[0];
        int numberOfEdge = VE[1];

        check = new boolean[numberOfVertex + 1];
        graph = (ArrayList<Integer>[]) new ArrayList[numberOfVertex + 1];

        for (i = 1; i <= numberOfVertex; i++)
            graph[i] = new ArrayList<>();

        int[] temp;

        for (i = 1; i <= numberOfEdge; i++) {
            temp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            graph[temp[0]].add(temp[1]);
            graph[temp[1]].add(temp[0]);
        }

        for (i = 1; i <= numberOfVertex; i++){
            if(!check[i]){
                dfs(i);
                result += 1;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }

    static void dfs(int topVertex) {
        if (check[topVertex])
            return;

        check[topVertex] = true;

        for (int i : graph[topVertex])
            if (!check[i])
                dfs(i);
    }

}