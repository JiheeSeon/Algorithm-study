package Tree.Basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

class 개미굴_14725 {
    String rootNode;
    Map<String, 개미굴_14725> childrenMap = new TreeMap<>();

    public 개미굴_14725(String rootNode) {
        this.rootNode = rootNode;
    }
}

class MainA14725{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int branchN = Integer.parseInt(br.readLine());

        String[] tmp; int level;
        개미굴_14725 tree = new 개미굴_14725("");
        개미굴_14725 now;

        for (int i = 0; i < branchN; i++) {
            tmp = br.readLine().split(" ");
            level = Integer.parseInt(tmp[0]);

            now = tree;
            for (int l = 1; l <= level; l++) {
                now = now.childrenMap.computeIfAbsent(tmp[l], 개미굴_14725::new);
            }
        }

        StringBuilder stb = new StringBuilder();
        solve(tree, -1, stb);
        System.out.print(stb);
    }

    static void solve(개미굴_14725 now, int level, StringBuilder stb) {
        if(level > 0) stb.append("-".repeat(level * 2));
        if(level > -1) stb.append(now.rootNode).append("\n");

        for (Map.Entry<String, 개미굴_14725> childEntry : now.childrenMap.entrySet()) {
            solve(childEntry.getValue(), level + 1, stb);
        }
    }
}