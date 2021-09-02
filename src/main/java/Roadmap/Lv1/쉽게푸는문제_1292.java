package Roadmap.Lv1;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

class 쉽게푸는문제_1292 {
    static long solve(int start, int end) {
        //set ends arr
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 1; i <= 45; i++)
            map.put(i*(i + 1) / 2, i);

        int dist = end - start + 1;

        long sum = 0;
        int cnt;
        int startGroup, endGroup;

        // set start group
        cnt = 0;
        while(true){
            cnt++;
            if(map.containsKey(start)){
                startGroup = map.get(start);
                sum += (long)startGroup * cnt;
                break;
            }
            start++;
        }

        cnt = 0;
        while (true) {
            --end; ++cnt;

            if (map.containsKey(end)) {
                endGroup = map.get(end) + 1;
                sum += (long)endGroup * cnt;
                break;
            }
        }

        if (startGroup == endGroup) {
            return (long) endGroup * dist;
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