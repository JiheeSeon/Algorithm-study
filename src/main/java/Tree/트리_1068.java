package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

// ConcurrentModification Exception
// :: list 순회 도중 remove 등 list의 상태를 변경할 때 발생

class 트리_1068 {
    static int del;
    static Map<Integer, ArrayList<Integer>> graph = new LinkedHashMap<>();
    static int[] parent;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parent = strToIntArray(br.readLine());

        ArrayList<Integer> children;
        int root = 0;
        for(int i = 0; i < N; i++){
            if(parent[i] == -1) root = i;

            if(!graph.containsKey(parent[i])) {
                children = new ArrayList<>();
                children.add(i);
                graph.put(parent[i], children);
            } else{
                children = graph.get(parent[i]);
                children.add(i);
                graph.put(parent[i], children);
            }
        }

        del = Integer.parseInt(br.readLine());

        if(del == root){
            System.out.println(0);
            return;
        }

        dfsDel(del);
        dfsLeaf(root);

        System.out.println(ans);
    }

    static int[] strToIntArray(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static void dfsDel(int curr) {
        if(!graph.containsKey(curr)){
            // 이미 리프노드일 경우
            if(curr == del){
                ArrayList<Integer> a = graph.get(parent[curr]);
                if(a.size() == 1) graph.remove(parent[curr]);
                else {
                    int idx = 0;
                    for (int i : a) {
                        if (i == del){
                            break;
                        }
                        idx++;
                    }
                    a.remove(idx);
                    graph.put(parent[curr], a);
                }
            }
            graph.remove(curr);
            parent[curr] = -2; // orphan node
            return;
        }

        ArrayList<Integer> children = graph.get(curr);

        for(int i : children) dfsDel(i);

        // 자신을 자식으로 가지고 있는 부모에게 자신을 제거해주어야 함.
        if(curr == del){
            ArrayList<Integer> a = graph.get(parent[curr]);
            if(a.size() == 1) graph.remove(parent[curr]);
            else {
                int idx = 0;
                for (int i : a) {
                    if (i == del) break;
                    idx++;
                }
                a.remove(idx);
                graph.put(parent[curr], a);
            }
        }
        graph.remove(curr);
        parent[curr] = -2;
    }

    static void dfsLeaf(int curr) {
        if(!graph.containsKey(curr) || parent[curr] == -2 || curr == del){
            if(curr != del) ans++;
            return;
        }

        for(int i : graph.get(curr)) dfsLeaf(i);
    }

    static void display(Map<Integer, ArrayList<Integer>> map) {
        for (int i : map.keySet()) {
            ArrayList<Integer> ar = map.get(i);
            for(int k : ar)
                System.out.print(k +" ");
            System.out.println();
        }
        System.out.println();
    }
}

/*Testcase
14
-1 0 0 1 1 2 2 3 5 5 6 7 7 12
7

5

5
4 4 4 4 -1
0

3

2
-1 0
1

*/