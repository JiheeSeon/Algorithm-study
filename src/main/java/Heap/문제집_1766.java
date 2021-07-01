package Heap;

import java.io.*;
import java.util.regex.Pattern;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class 문제집_1766{
    int N, M;
    int[][] orderPair;
    int[] check;

    int loopIdx = 1;

    ArrayList<Integer>[] winFrom;
    ArrayList<Integer>[] loseFrom;
    Set<Integer> winnerSet, diffWinnerSet;
    PriorityQueue<Problem> pq;

    public 문제집_1766(int n, int m, int[][] orderPair) {
        N = n;
        M = m;
        this.orderPair = orderPair;

        winFrom = new ArrayList[N + 1];
        loseFrom = new ArrayList[N + 1];
        check = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            winFrom[i] = new ArrayList<>();
            loseFrom[i] = new ArrayList<>();
        }

        winnerSet = IntStream.rangeClosed(1, N).boxed().collect(Collectors.toCollection(TreeSet::new));
        for(int[] pair : orderPair){
            winFrom[pair[0]].add(pair[1]);
            loseFrom[pair[1]].add(pair[0]);
            winnerSet.remove(pair[1]); // 한번이라도 지면 제거
        }
        diffWinnerSet = IntStream.rangeClosed(1, N).boxed().collect(Collectors.toCollection(TreeSet::new));
        diffWinnerSet.removeAll(winnerSet);
    }

    String getAns() {
        pq = new PriorityQueue<>();
        StringBuilder stb = new StringBuilder();
        // 무조건 먼저 풀 애들을 set 에 넣어줌.
        for (int i : winnerSet) {
            stb.append(i).append(" ");
            check[i] = M + 1;
        }

        // 먼저 풀 애들이 상대했던 애들을 DFS로 타고감
        for(int i : winnerSet) getRankToRegister(i);

        // 사실상 rank 와 무관한 것끼리의 순서 (무조건적인 순서 강제 X)
        int ret, rankToRegister;
        for(int i = 1; i <= N; i++){
            if(check[i] != 0) continue;

            rankToRegister = getRankToRegister(i);

            check[i] = rankToRegister;
            pq.add(new Problem(i, rankToRegister, loopIdx++));
        }

        Problem p;
        while (!diffWinnerSet.isEmpty()) {
            p = pq.poll();
            if(!diffWinnerSet.contains(p.num)) continue;
//            System.out.println(p);
            stb.append(p.num).append(" ");
            diffWinnerSet.remove(p.num);
        }

        return stb.toString();
    }

    private int getRankToRegister(int i) {
        int rankToRegister;
        int ret;
        rankToRegister = M;

        for(int j : winFrom[i]) {
            ret = dfs(j, M, loopIdx);
            rankToRegister = Math.min(rankToRegister, ret);
            pq.add(new Problem(j, ret, loopIdx++));
        }
        return rankToRegister;
    }

    int dfs(int probN, int cnt, int time) {
        if(loseFrom[probN].isEmpty() || check[probN] == M + 1) return cnt - 1;

        int rankToRegister = cnt - 1;
        int loopIdx = 1;
        for(int winner : loseFrom[probN]){
            rankToRegister = Math.min(rankToRegister, dfs(winner, cnt, time + loopIdx++));
        }

        check[probN] = rankToRegister;
        pq.add(new Problem(probN, rankToRegister, time));
        return rankToRegister;
    }
}

class Problem implements Comparable<Problem>{
    int num, rank, time;

    public Problem(int num, int rank, int time){
        this.num = num;
        this.rank = rank;
        this.time = time;
    }

    @Override
    public int compareTo(Problem p){
        if (rank == p.rank) {
            return time == p.time ? Integer.compare(num, p.num) : Integer.compare(p.time, time);
        } else return Integer.compare(rank, p.rank);
    }

    @Override
    public String toString() {
        return "Problem (" + "num=" + num + ", rank=" + rank + ", time=" + time + ')';
    }
}

class MainA1766{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArr(br.readLine());
        int N = tmp[0]; int M = tmp[1];

        int[][] orderPair = new int[M][2];
        for(int m = 0; m < M; m++){
            orderPair[m] = strToIntArr(br.readLine());
        }

        System.out.println(new 문제집_1766(N, M, orderPair).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}