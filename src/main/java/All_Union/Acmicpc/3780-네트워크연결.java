package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Main3780{
    static Map<Enterprise, Enterprise> parent = new HashMap<>();
    static Enterprise[] enterprises;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int N;
        String[] input;
        int arbitraryIdx, centerIdx;

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            input = br.readLine().split(" ");

            while(!input[0].equals("O")) {
                if(input[0].equals("E")){
                    arbitraryIdx = Integer.parseInt(input[1]);
                    System.out.println(find(arbitraryIdx, enterprises[arbitraryIdx].dst));

                } else{
                    centerIdx = Integer.parseInt(input[1]);
                    arbitraryIdx = Integer.parseInt(input[2]);
                    union(centerIdx, arbitraryIdx);
                }
                input = br.readLine().split(" ");
            }
        }
    }

    static void union(int a, int b) {
        if(!parent.containsKey(enterprises[a])) parent.put(enterprises[a], enterprises[a]);
        if(!parent.containsKey(enterprises[b])) parent.put(enterprises[b], enterprises[b]);

        Enterprise pA = find(a, enterprises[a].dst);
        Enterprise pB = find(b, enterprises[b].dst);

        if(!pA.equals(pB)){
            parent.put(pA, pB);
            pA.dst += Math.abs(a - b) % 1000; // 거리 update
        }
    }

    static Enterprise find(int a, Integer updatedD) {
        if(enterprises[a].equals(parent.get(enterprises[a])))
            return enterprises[a];
        else{
            updatedD += parent.get(enterprises[a]).dst;
            Enterprise pE = find(parent.get(enterprises[a]).idx, updatedD);
            parent.put(enterprises[a], pE);
            enterprises[a].dst = updatedD; // 거리 업데이트
            return pE;
        }
    }

    static private class Enterprise{
        int dst = 0; // 나의 직속 부모와의 거리
        final int idx;

        public Enterprise(int idx) {
            this.idx = idx;
        }
    }
}