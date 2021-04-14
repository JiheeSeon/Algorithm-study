package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


class Main16562 {
    /* 자료구조 생각하는데 한참 헷갈림*/
    static Map<Friend, Friend> parent = new LinkedHashMap<>();
    static Friend[] friends; //input을 따로 저장해둠

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = convertInputToIntArray(br);
        int N = input[0]; int M = input[1]; int k = input[2];
        friends = new Friend[N + 1];

        input = convertInputToIntArray(br);
        Friend tmp;
        for (int i = 0; i < input.length; i++) {
            tmp = new Friend(i + 1, input[i]);
            parent.put(tmp, tmp);
            friends[i + 1] = tmp;
        }

        for (int i = 0; i < M; i++) {
            input = convertInputToIntArray(br);
            union(friends[input[0]], friends[input[1]]);
        }

        int cost = 0;
        for (Friend f : friends) {
            if(f == null) continue;
            // 내가 조상이면
            if (f.equals(find(f))) cost += f.cost;
        }
        System.out.println(cost > k ? "Oh no" : cost);
    }

    static void union(Friend f1, Friend f2) {
        Friend pF1 = find(f1);
        Friend pF2 = find(f2);

        if (pF1.equals(pF2)) return;

        if (pF1.compareTo(pF2) > 0) parent.put(pF2, pF1);
        else parent.put(pF1, pF2);
    }

    static Friend find(Friend f) {
        if(f.equals(parent.get(f)))
            return f;
        else{
            Friend fP = find(parent.get(f));
            parent.put(f, fP);
            return fP;
        }
    }

    static int[] convertInputToIntArray(BufferedReader br) throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }

    private static class Friend implements Comparable<Friend> {
        int number, cost;

        public Friend(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Friend o) {
            return Integer.compare(o.cost, this.cost);
        }

        @Override
        public String toString() {
            return "Friend(" +
                    "number=" + number +
                    ", cost=" + cost +
                    ')';
        }
    }
}