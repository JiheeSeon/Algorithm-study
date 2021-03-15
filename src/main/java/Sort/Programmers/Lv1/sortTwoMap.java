package Sort.Programmers.Lv1;
import java.util.*;

class sortingMap {
    static double[] solution(int N, int[] stages) {
        int numOfPlayer = stages.length;
        Arrays.sort(stages);

        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();

        for(int stageN: stages){
            if(stageN == N + 1) continue;
            map.put(stageN, 1 + map.getOrDefault(stageN, 0));
        }

        int numOfTryingPeople;
        for(int key: map.keySet()){
            numOfTryingPeople = map.get(key);

            map2.put(key, numOfPlayer);
            numOfPlayer -= numOfTryingPeople;
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet())
            System.out.println(entry.getKey() + " " + entry.getValue());

        double[] res =
                map.entrySet().stream()
                        .mapToDouble(o->o.getValue()/(double)map2.get(o.getKey()))
                        .sorted()
                        .toArray();

        return res;
    }

    public static void main(String[] args) {
//        double[] res = solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3});
        double[] res = solution(4, new int[]{4, 4, 4, 4, 4});
        for(double i : res)
            System.out.print(i + " ");
    }
}