package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main4803{
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int testcaseN = 1;
        int treeNFlag;

        while(true) {
            int[] input = strInputToIntArray(br);
            int vertexN = input[0]; int edgeN = input[1];

            if(vertexN == 0 && edgeN == 0) break;

            parent = IntStream.rangeClosed(0, vertexN).toArray();
            treeNFlag = 0;
            for (int i = 0; i < edgeN; i++) {
                input = strInputToIntArray(br);
                union(input[0], input[1]);
            }
            for(int i = 1; i < parent.length; i++) find(i);

            Map<Integer, Integer> ancestors = new HashMap<>();

            Set<Integer> set = Arrays.stream(parent).boxed().filter(i->i > 0).collect(Collectors.toSet());
            treeNFlag = set.size();

            if(treeNFlag == 0) stb.append("Case ").append(testcaseN).append(": No trees.\n");
            else if (treeNFlag == 1) stb.append("Case ").append(testcaseN).append(": There is one tree.\n");
            else stb.append("Case ").append(testcaseN).append(": A forest of ").append(treeNFlag).append(" trees.\n");

            testcaseN++;
        }
        System.out.println(stb);
    }

    static void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX == parentY){
            for(int i = 1; i < parent.length; i++){
                if (find(parent[i]) == parentX) parent[i] = -1;
            }
            parent[x] = parent[y] = -1;
            return;
        }

        if(parentX > parentY) parent[parentX] = parentY;
        else parent[parentY] = parentX;
    }

    static int find(int x) {
        if(x == -1 || parent[x] == -1) return -1;

        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }
    static int[] strInputToIntArray(BufferedReader br) throws IOException{
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }
}