package All_Union.Acmicpc.Resolve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FriendNW {
    static Map<String, String> parent; // value가 부모의 key를 가리키는 구조
    static Map<String, Integer> population; // 각 네트워크에 속한 인원 업데이트용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int F; String s;
        String[] friends;

        for (int i = 0; i < T; i++) {
            F = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            population = new HashMap<>();

            for (int j = 0; j < F; j++) {
                s = br.readLine();
                friends = s.split(" ");
                union(friends[0], friends[1]);
                System.out.println(population.get(find(friends[0])));
            }
        }
    }

    static void union(String s1, String s2) {
        if(!parent.containsKey(s1)) parent.put(s1, s1);
        if(!parent.containsKey(s2)) parent.put(s2, s2);

        String pS1 = find(s1);
        String pS2 = find(s2);

        if(pS1.equals(pS2)) return;

        if(pS1.compareTo(pS2) < 0){
            // 두 사람이 함께 속한 네트워크 인원 수 = 각 트리 별 인원의 합
            // union을 할 때마다 트리의 관계성이(부모-자식 관계) 바뀌고 루트 노드 계산하므로 여기서 하는게 맞음
            population.put(pS1, population.getOrDefault(pS1, 1) + population.getOrDefault(pS2, 1));
            // 0보다 작다는 건 pS1이 사전 순으로 윗쪽에 있다는 의미
            parent.put(pS2, pS1);
        } else {
            population.put(pS2, population.getOrDefault(pS2, 1) + population.getOrDefault(pS1, 1));
            parent.put(pS1, pS2);
        }
    }

    static String find(String s){
        if (s.equals(parent.get(s)))
            return s;
        else{
            parent.put(s, find(parent.get(s)));
            return parent.get(s);
        }
    }
}
