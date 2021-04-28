package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class BST표현2_일반그래프 {
    static ArrayList<Character>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];
        for(int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        char[] nodeInfo;
        for (int i = 0; i < N; i++) {
            nodeInfo = br.readLine().replace(" ", "").toCharArray();
            graph[nodeInfo[0]- 'A'].add(nodeInfo[1]);
            graph[nodeInfo[0]- 'A'].add(nodeInfo[2]);
        }

        StringBuilder stb = new StringBuilder();
        stb.append(preorder('A', new StringBuilder())).append("\n");
        stb.append(inorder('A', new StringBuilder())).append("\n");
        stb.append(postorder('A', new StringBuilder())).append("\n");
        System.out.print(stb);
    }

    static StringBuilder preorder(char current, StringBuilder stb) {
        if (current == '.') return stb;

        stb.append(current);
        for(char c : graph[current - 'A'])
            preorder(c, stb);

        return stb;
    }

    static StringBuilder inorder(char current, StringBuilder stb) {
        if (current == '.') return stb;

        inorder(graph[current - 'A'].get(0), stb);
        stb.append(current);
        inorder(graph[current - 'A'].get(1), stb);

        return stb;
    }

    static StringBuilder postorder(char current, StringBuilder stb) {
        if (current == '.') return stb;

        for(char c : graph[current - 'A'])
            postorder(c, stb);

        stb.append(current);
        return stb;
    }
}
