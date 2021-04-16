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

    static Integer tmp = 0;
    static Map<Enterprise, Enterprise> parent;
    static Enterprise[] enterprises;
    static int ttmp;

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
                    arbitraryIdx = Integer.parseInt(input[1]);
                    find(enterprises[arbitraryIdx], enterprises[arbitraryIdx].dst);
                    stb.append(enterprises[arbitraryIdx].getDst()).append("\n");
                } else{
                    centerIdx = Integer.parseInt(input[1]);
                    arbitraryIdx = Integer.parseInt(input[2]);
                    union(centerIdx, arbitraryIdx);
                    System.out.println(Arrays.toString(enterprises));
                }

                input = br.readLine().split(" ");

            }
        }
        System.out.println(stb);
    }

    static void union(int a, int b) {
        Enterprise pA = find(enterprises[a], enterprises[a].dst);
        Enterprise pB = find(enterprises[b], enterprises[b].dst);

        if(!pA.equals(pB)){ // pB가 무조건 PA를 흡수
            parent.put(pA, pB);
            pA.setDst((enterprises[b].getDst() + Math.abs(a - b) % 1000) % 1000); // 거리 update
        }
    }

    static Enterprise find(Enterprise x, PassedDistance firstD) {
        if(x.equals(parent.get(x))) {
            return x;
        } else{
            // x의 distance에 자신의 부모까지의 거리를 추가
            firstD.setD(firstD.d + parent.get(x).getDst() % 1000);
            Enterprise pE = find(parent.get(x), firstD);
//            parent.put(x, pE);
            return pE;
        }
    }

    static private class Enterprise{
        PassedDistance dst;
        final int idx;

        public Enterprise(int idx) {
            dst = new PassedDistance(0);
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "(" + idx+" "+dst + ")";
        }

        public void setDst(int d){
            dst.setD(d);
        }

        public int getDst() {
            return dst.d;
        }
    }

    static private class PassedDistance{
        int d;

        public PassedDistance(int d) {
            this.d = d;
        }

        public void setD(int d) {
            this.d = d;
        }

        @Override
        public String toString() {
            return d+"";
        }
    }
}
/*
2
13
I 1 2
E 2
E 1
I 3 2
I 4 5
I 5 3
E 3
I 9 8
I 10 9
I 13 9
E 13
I 2 10
E 5
E 4
O
//next
1
20
I 16 1
I 15 9
I 8 6
I 5 19
I 1 17
I 16 1
I 15 9
I 8 6
I 5 19
I 1 17
I 14 13
I 9 20
I 13 19
I 10 13
I 20 17
E 20
I 19 6
I 18 12
I 17 2
I 4 2
I 6 17
E 16
E 2
E 4
E 17
I 2 18
E 13
E 2
E 6
E 20
E 4
I 3 14
I 7 10
E 5
O
I 14 13
I 9 20
I 13 19
I 10 13
I 20 17
E 20
I 19 6
I 18 12
I 17 2
I 4 2
I 6 17
E 16
E 2
E 4
E 17
I 2 18
E 13
E 2
E 6
E 20
E 4
I 3 14
I 7 10
E 5
O
//
1
20
I 16 1
I 15 9
I 8 6
I 5 19
I 1 17
I 14 13
I 9 20
I 13 19
I 10 13
I 20 17
E 20 -> 3
I 19 6
I 18 12
I 17 2
I 4 2
I 6 17
E 16
E 2
E 4 -> 0
E 17 -> 2
I 2 18 -> 15
E 13 -> 67
E 2 -> 22
E 6 -> 48 <-> 26
E 20 -> 40
E 4 -> 24
I 3 14
I 7 10
E 5 -> 75 <-> 27
O
*/