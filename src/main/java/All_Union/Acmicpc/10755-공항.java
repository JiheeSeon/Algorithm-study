package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

class Main10755{
    static int planeN;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int gateN = Integer.parseInt(br.readLine());
        parent = IntStream.rangeClosed(0, gateN).toArray();

        planeN = Integer.parseInt(br.readLine());

        int [] gateOfPlanes = new int[planeN];
        for (int i = 0; i < planeN; i++) {
            gateOfPlanes[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(gateOfPlanes));
    }

    static int solution(int[] gateOfPlanes) {
        int result = 0;

        for (int g : gateOfPlanes) {
            if(find(g) == 0) break; // 현재 내 조부모가 0이면 이미 간 곳들의 집합밖에 없음
            union(find(g), find(g) - 1); // 이미 도킹된 게이트의 경우 연결해줄 수 있음.
            System.out.println(Arrays.toString(parent));
            result++;
        }

        return result;
    }

    // aIdx가 속해있는 집합 A와 bIdx가 속해있는 집합 B의 루트 중 더 작은 값으로 루트만 바꿔친다.
    static void union(int aIdx, int bIdx) {
        int parentA = find(aIdx);
        int parentB = find(bIdx);

        if(parentA == parentB) return;

        if(parentA > parentB) parent[parentA] = parentB;
        else parent[parentB] = parentA;
    }

    // 최고조 부모 찾고 가는 길에 보이는 노드들의 부모 최고조 부모로 세팅
    static int find(int nodeIdx) {
        return parent[nodeIdx] == nodeIdx
                ? nodeIdx
                : (parent[nodeIdx] = find(parent[nodeIdx]));
    }
}