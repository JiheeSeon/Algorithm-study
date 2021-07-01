package Heap;

import java.io.*;
import java.util.regex.Pattern;
import java.util.*;

/*
위상정렬

알고리즘 개요
1. 진입차수가 0인 정점을 큐에 삽입
2. 큐에서 원소를 꺼내 연결된 모든 간선 제거
3. 간선 제거 이후 진입차수가 0이 된 정점을 큐에 삽입
4. 큐가 빌 때까지 2-3 반복, 모든 원소를 방문하기 전에 큐가 빈다면 사이클 존재.
   모든 원소 방문했다면 큐에서 꺼낸 순서가 위상정렬의 결과

해당 문제에서 위상정렬을 사용해야하는 이유
- 먼저 푸는 것이 좋은 문제는, 반드시 먼저 풀어야 한다.
-> DAG, 사이클 없이 선수과목 그릴 때와 같은 그래프

변형된 부분
- 진입차수가 0이 되면 큐 가장 앞으로 보내야 함.
b/c 문제 번호가 낮은 것 (문제의 난이도가 낮은 것)부터 풀기로 했음.
-> 우선순위 큐 사용
*/
class 문제집_1766{
    int N, M;

    ArrayList<Integer>[] out;
    int[] degree;

    public 문제집_1766(int n, int m, int[][] orderPair) {
        N = n;
        M = m;

        out = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) out[i] = new ArrayList<>();

        // out, inDegree Map
        degree = new int[N + 1];
        for (int[] pair : orderPair) {
            out[pair[0]].add(pair[1]);
            degree[pair[1]] += 1;
        }
    }

    String getAns() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++)
            if(degree[i] == 0) pq.add(i);

        StringBuilder stb = new StringBuilder();

        int prob;
        while (!pq.isEmpty()) {
            prob = pq.poll();
            stb.append(prob).append(" ");

            // 진출차수 없앰
            for (int next : out[prob]) {
                degree[next] -= 1;
                if(degree[next] == 0) pq.add(next);
            }
        }
        return stb.toString();
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