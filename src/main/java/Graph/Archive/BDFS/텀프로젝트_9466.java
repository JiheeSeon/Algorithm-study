package Graph.Archive.BDFS;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
9466 텀프로젝트
혼자 할 수도 있음.
*/
class 텀프로젝트_9466 {
    int N; int[] wanted;
    int[] check;
    TreeSet<Integer> leafs;
    Set<Integer> team = new HashSet<>();

    public 텀프로젝트_9466(int N, int[] wanted){
        this.N = N;
        this.wanted = wanted;

        check = new int[N + 1];
        leafs = IntStream.rangeClosed(1, N).boxed().collect(Collectors.toCollection(TreeSet::new));

        for(int i = 0; i < N; i++){
            if(i + 1 == wanted[i]){
                check[i] = i + 1;
                team.add(i + 1);
            }
            leafs.remove(wanted[i]);
        }
    }

    int getAns(){
        for(int i : leafs){
            dfs(i);
        }

        for (int i : team) {
            System.out.print(i + " ");
        }
        System.out.println();
        return team.size();
    }

    int dfs(int now){
        if(now == wanted[now - 1]) return -1;

        int parent = dfs(wanted[now]);
        System.out.println("parent = " + parent);
        if(parent != -1){
            team.add(parent);
        }

        return parent;
    }
}

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int N; int[] wanted;
        StringBuilder stb = new StringBuilder();
        for(int t = 0; t < T; t++){
            N = Integer.parseInt(br.readLine());
            wanted = strToIntArr(br.readLine());
            stb.append(new 텀프로젝트_9466(N, wanted).getAns()).append("\n");
        }
        System.out.print(stb);
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}