package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Main3780{
    /*문제 요약
    A의 루트 노드를 B 트리의 자식 or 루트 노드의 자식으로 넣어버리기 (B에 A 흡수)
    */
    static Map<Enterprise, Enterprise> parent;
    static Enterprise[] enterprises;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        int N;
        String[] input;
        int arbitraryIdx, centerIdx;

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            enterprises = new Enterprise[N + 1];

            for (int j = 1; j < N + 1; j++) {
                enterprises[j] = new Enterprise(j);
                parent.put(enterprises[j], enterprises[j]);
            }

            input = br.readLine().split(" ");

            while(!input[0].equals("O")) {
                if(input[0].equals("E")){
                    System.out.println("finding ");

                    arbitraryIdx = Integer.parseInt(input[1]);
                    find(arbitraryIdx, enterprises[arbitraryIdx].dst);
                    stb.append(enterprises[arbitraryIdx].dst).append("\n");
                } else{
                    centerIdx = Integer.parseInt(input[1]);
                    arbitraryIdx = Integer.parseInt(input[2]);

                    System.out.println("union "+ centerIdx + " " +arbitraryIdx);
                    union(centerIdx, arbitraryIdx);
                }
                input = br.readLine().split(" ");

                System.out.println(Arrays.toString(enterprises));
                System.out.println();
            }
        }
        System.out.println(stb);
    }

    static void union(int a, int b) {
        Enterprise pA = find(a, enterprises[a].dst);
        Enterprise pB = find(b, enterprises[b].dst);

        if(!pA.equals(pB)){ // pB가 무조건 PA를 흡수
            parent.put(pA, pB);
            pA.dst += (enterprises[b].dst + Math.abs(a - b) % 1000); // 거리 update
            System.out.println(enterprises[b].dst);
        }
    }

    static Enterprise find(int x, Integer x_updatedD) {
        if(enterprises[x].equals(parent.get(enterprises[x])))
            return enterprises[x];
        else{
            // x의 distance에 자신의 부모까지의 거리를 추가해줌
            x_updatedD += parent.get(enterprises[x]).dst;
            Enterprise pE = find(parent.get(enterprises[x]).idx, x_updatedD);
            System.out.println(" " + x_updatedD);
            parent.put(enterprises[x], pE);
            return pE;
        }
    }

    static private class Enterprise{
        Integer dst = 0; // 나의 직속 부모와의 거리
        final int idx;

        public Enterprise(int idx) {
            this.idx = idx;
        }

        @Override
        public String toString() {
            return dst+" ";
        }
    }
}