package Tree;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class MainA11725{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++)
            graph[i] = new ArrayList<>();

        int[] tmp;
        for(int i = 0; i < N - 1; i++){
            tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }

        System.out.print(new 트리의_부모찾기_11725_sol2().solution(graph));
    }
}

class 트리의_부모찾기_11725_sol2  {
    public String solution(ArrayList<Integer>[] graph) {
        StringBuilder stb = new StringBuilder();

        int[] parent = dfs(1, graph, new int[graph.length], 1);
        for(int i = 2; i < parent.length; i++)
            stb.append(parent[i]).append("\n");
        return stb.toString();
    }

    int[] dfs(int now, ArrayList<Integer>[] graph, int[] parent, int p){
        if(parent[now] != 0) return parent;

        parent[now] = p;
        for(int child: graph[now]){
            dfs(child, graph, parent, now);
        }

        return parent;
    }
}