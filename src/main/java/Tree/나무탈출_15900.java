package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

/*Idea -> depth 를 더한다*/
class 나무탈출_15900 {
    static Integer[] parent;
    static ArrayList<Integer>[] graph;
    static Set<Integer> alreadyRegistered = new HashSet<>();
    static ArrayList<LeafInfo> lf = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        parent = new Integer[N + 1];
        graph = new ArrayList[N +1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] tmp;
        for(int i = 0; i < N - 1 ; i++) {
            tmp = strToIntArray(br.readLine());
            graph[tmp[0]].add(tmp[1]);
            graph[tmp[1]].add(tmp[0]);
        }
        setParent(1, 1, 0);

        int res = 0;
        for(LeafInfo linfo : lf){
            res += linfo.depth;
        }
//        System.out.println(res);
        System.out.println(res % 2 == 0 ? "No" : "Yes");
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static void setParent(int now, int prev, int depth){
//        if(parent[now] != 0) return;

        parent[now] = prev;
        alreadyRegistered.add(now);

        int validLoop = 0;
        for (int i : graph[now]) {
            if(!alreadyRegistered.contains(i)){
                setParent(i, now, depth + 1);
                validLoop++;
            }
        }

        if(validLoop == 0) lf.add(new LeafInfo(depth, now));
    }

    static int getPathLength(int now, int len){
        if(now == 1) return len;
        return getPathLength(parent[now], len + 1);
    }

    private static class LeafInfo{
        int depth, number;

        public LeafInfo(int depth, int number) {
            this.depth = depth;
            this.number = number;
        }
    }
}
