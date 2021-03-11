package Implementation.Programmers.lv1;

import java.util.*;

class Main12906 {
    public int[] solution(int []arr) {
        ArrayList<Integer> arl = new ArrayList<>();
        arl.add(arr[0]);

        for(int i = 1; i < arr.length; i++){
            if(arr[i] != arr[i - 1])
                arl.add(arr[i]);
        }
        return arl.stream().mapToInt(o->o).toArray();
    }
}