package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// root부터 아래로 타고 내려가는 방식
class 트리_원소삭제_TopDown {
    static int[] parent;
    static int toErase;
    static Map<Integer, ArrayList<Integer>> children;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        parent = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        int root = -2;
        children = new HashMap<>();
        ArrayList<Integer> toPut;
        for (int i = 0; i < N; i++) {
            toPut = children.containsKey(parent[i]) ? children.get(parent[i]) : new ArrayList<>();
            toPut.add(i);
            children.put(parent[i], toPut);

            if(parent[i] == -1) root = i;
        }

        toErase = Integer.parseInt(br.readLine()); // 사용자가 삭제하고자 하는 노드

        // 루트를 삭제하면 남는 원소는 0개
        if(toErase == root){
            System.out.println(0);
            return;
        }

        erase(root, false);

        // 트리 원소 삭제 후 leaf node 체크
        Set<Integer> leafs = IntStream.range(-1, N).boxed().collect(Collectors.toCollection(HashSet::new));
        for (int i = 0; i < N; i++) {
            if(parent[i] == -2) leafs.remove(i);
            else leafs.remove(parent[i]);
        }

        System.out.println(leafs.size());
    }

    static void erase(int curr, boolean eraseFlag) {
        // 지워야 하는 노드인지 확인
        if(curr == toErase) eraseFlag = true;
        if(eraseFlag) parent[curr] = -2;

        // 자식노드가 없는 경우
        if(!children.containsKey(curr)) return;

        ArrayList<Integer> ch = children.get(curr);
        for (int c : ch) {
            erase(c, eraseFlag);
        }
    }

    static void displayChildren() {
        System.out.println("display start");
        for(int i : children.keySet()){
            System.out.println("key = " + i);
            ArrayList<Integer> ar = children.get(i);
            for (int a : ar) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}
