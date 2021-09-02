package Roadmap.Lv1;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class 쉽게푸는문제_1292 {
    static long solve(int start, int end) {
        //set ends arr
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 1000; i++)
            map.put(i*(i + 1) / 2, i);

        long sum = 0;
        int cnt;
        int startGroup, endGroup;

        // set start group
        if(map.containsKey(start)){
            startGroup = map.get(start);
            sum += startGroup;
        } else {
            cnt = 0;
            while(!map.containsKey(start++)){cnt++;}
            startGroup = map.get(start - 1);
            sum += ((long) (cnt + 1) * startGroup);
        }

        // set end group
        if(map.containsKey(end)){
            endGroup = map.get(end);
            sum += ((long) endGroup * endGroup);
        } else{
            cnt = 0;
            while(!map.containsKey(end--)){cnt++;}
            endGroup = map.get(end + 1) + 1;
            sum += ((long) cnt * endGroup);
        }

        // add between start & end group
        for (int i = startGroup + 1; i < endGroup; i++)
            sum += (long) i * i;

        return sum;
    }
}

class MainA1202{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] range = InputProcessor.strToIntArr(br.readLine());
        System.out.println(쉽게푸는문제_1292.solve(range[0], range[1]));
    }
}