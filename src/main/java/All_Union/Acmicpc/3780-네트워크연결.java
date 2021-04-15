package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Main3780{
    static Enterprise[] enterprises;
    static Map<Enterprise, Enterprise> parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();

        String input; String[] tmp;
        int N;

        Enterprise existed, toConnect;
        Enterprise next, root = null;

        long distance;

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            enterprises = new Enterprise[N + 1];

            for (int j = 1; j < N + 1; j++){
                enterprises[j] = new Enterprise(j);
                parent.put(enterprises[j], enterprises[j]);
            }

            input = br.readLine();

            while(input.charAt(0) != 'O'){
                tmp = input.split(" ");
                existed = enterprises[Integer.parseInt(tmp[1])];

                if (tmp[0].equals("E")){
                    stb.append(existed.distance).append("\n");
                }
                else{
                    toConnect = enterprises[Integer.parseInt(tmp[2])];
                    root = toConnect;

                    existed.setDistance(Math.abs(Integer.parseInt(tmp[2]) - Integer.parseInt(tmp[1])) % 1000);
                    union(existed, toConnect);

                    for(Map.Entry<Enterprise, Enterprise> e: parent.entrySet()){
                        if(!e.getKey().equals(e.getValue())) find(e.getKey());
                    }
                }
                input = br.readLine();
            }
        }

        System.out.println(stb);
    }

    static private class Enterprise implements Comparable<Enterprise>{
        private final int idx;
        private int distance = 0;

        public Enterprise(int idx) {
            this.idx = idx;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(Enterprise o) {
            return Integer.compare(idx, o.idx);
        }

        @Override
        public String toString() {
            return "Enterprise(" + "idx=" + idx + ", distance=" + distance + ')';
        }
    }

    static void union(Enterprise e1, Enterprise e2) {
        Enterprise pE1 = find(e1);
        Enterprise pE2 = find(e2);

        if(pE1.equals(pE2)) return;

        parent.put(pE1, pE2);
    }

    static Enterprise find(Enterprise e) {
        if(e.equals(parent.get(e))) return e;
        else{
            e.distance += parent.get(e).distance;
            Enterprise pE = find(parent.get(e));
            parent.put(e, pE);
            return pE;
        }
    }
}