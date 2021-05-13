package Implementation.Programmers.lv2;
import java.util.*;

class 뉴스클러스터링_17677 {
    public int solution(String str1, String str2) {
        Map<String, Integer> mA = addElements(new LinkedHashMap<>(), str1);
        Map<String, Integer> mB = addElements(new LinkedHashMap<>(), str2);

        if(mA.size() == 0 && mB.size() == 0) return 65536;

        return (int)((double)65536 / union(mA, mB) * intersect(mA, mB));
    }

    int union(Map<String, Integer> map1, Map<String, Integer> map2){
        Map<String, Integer> rMap = new LinkedHashMap<>(map1);
        Map<String, Integer> d1Map = new LinkedHashMap<>(map1);
        Map<String, Integer> d2Map = new LinkedHashMap<>(map2);

        rMap.keySet().retainAll(map2.keySet());  // map1 && map2
        d1Map.keySet().removeAll(map2.keySet()); // map1 - map2
        d2Map.keySet().removeAll(map1.keySet()); // map2 - map1

        int result = 0;
        for(String ss : rMap.keySet())
            result += Math.max(rMap.get(ss), map2.get(ss)); // max로 처리

        // 교집합에 속하지 않는 원소들은 그대로 더해주기
        result += d1Map.values().stream().mapToInt(x->x).sum();
        result += d2Map.values().stream().mapToInt(x->x).sum();

        return result;
    }

    int intersect(Map<String, Integer> map1, Map<String, Integer> map2){
        Map<String, Integer> rMap = new LinkedHashMap<>(map1);
        rMap.keySet().retainAll(map2.keySet()); // keySet을 retainAll하면 map의 겹치는 키들이 다 제거됨.

        int result = 0;
        for(String ss : rMap.keySet())
            result += Math.min(rMap.get(ss), map2.get(ss));

        return result;
    }

    Map<String, Integer> addElements(Map<String, Integer> map, String s){
        int idx = 2;
        String tmp;

        while(idx <= s.length()){
            tmp = s.substring(idx - 2, idx).toLowerCase();

            if(tmp.matches("[a-zA-Z][a-zA-Z]"))
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);

            idx++;
        }
        return map;
    }
}

class Main17677{
    public static void main(String[] args) {
        System.out.println(new 뉴스클러스터링_17677().solution("FRANCE", "french")); // 16384
        System.out.println(new 뉴스클러스터링_17677().solution("aa1+aa2", "AAAA12")); // 43690
        System.out.println(new 뉴스클러스터링_17677().solution("E=M*C^2", "e=m*c^2")); // 65536
        System.out.println(new 뉴스클러스터링_17677().solution("handshake", "shake hands")); // 65536
    }
}