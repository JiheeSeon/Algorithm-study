package Implementation.Programmers.lv1;

import java.util.*;

class Solution42576 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        int val;

        // O(N) Error 발생시키지 않으려고 들어있는지 확인했는데 메소드로 지원 가능
        for(String keyPtcpt : participant){
            val = hm.containsKey(keyPtcpt)? hm.get(keyPtcpt) + 1: 0; // O(1)
            hm.put(keyPtcpt, val); // O(1)
        }

        // O(N)
        for (String keyCompl : completion){
            val = hm.get(keyCompl);
            if (val == 0)
                hm.remove(keyCompl); // O(1)
            else
                hm.put(keyCompl, val - 1);
        }

        // O(1)
        for (String k : hm.keySet()){
            answer = k;
        }

        return answer;
    }

    public String advancedSolution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        /*O(N) :: getOrDefault로 key가 있는지 없는지를 확인할 필요 없음*/
        for(String key : participant){ hm.put(key, hm.getOrDefault(key, 0) + 1); }

        for (String key : completion){ hm.put(key, hm.get(key) - 1); }

        for (String k : hm.keySet()){ if (hm.get(k) != 0){ answer = k; break;}} // O(N)

        return answer;
    }
}