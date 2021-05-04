package Implementation.Programmers.lv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
가장 많이 헤맸던 문제
- 문제를 잘 이해하기 어려워 삽질 시간 길었음.
- 구현을 어떻게 전개할지 몰라 헤맴. (구현에 확실히 약한 편)

문제 분석
1. 트럭이 다리에 완전히 오르지 않은 경우 트럭의 무게를 고려하지 않음.
-> 예제 기준으로 1초에서 2초까지만 다리에 트럭의 무게가 유효
-> 왜냐하면 0초에서 1초는 트럭이 다리와 평지에 걸쳐져있음. 온전히 다리위에 존재 X
-> 2초-3초까지는 다리에서 평지로 내려오는 시간, 3초가 되어서야 딱 평지에 도착.
-> 즉 다리에 하중이 가는 시기는 1초-2초 직전 :: 2초부터 무게 계산 X

2. 그럼 다리를 벗어날 때마다 최대한 다리가 버틸 수 있는 무게에 가깝게 트럭을 보내야 함.
동시에 여러개의 트럭이 출발할 수는 없음. 1차선 다리라고 생각한자.
-> 한 트럭이 움직이고나서 벗어날 초와 뺄 하중을 큐에 넣어놓음
-> 큐에서 하나씩 뺄 때마다 현재 하중을 제거하고, 더 올릴 수 있는 트럭만큼 올리기

*/
class 다리를지나는트럭_42583 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(new Solution().solution(5, 5, new int[]{2, 2, 2, 2, 1, 1, 1, 1, 1}));
    }
}

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int idx = 0;
        int sum = 0;
        int nowT = 0;

        Queue<TruckRecord> truckRec = new LinkedList<>();

        TruckRecord tr;
        boolean flag = false;

        Set<Integer> set = new HashSet<>();

        while(!flag || !truckRec.isEmpty()){
            if(flag){
                tr = truckRec.poll();
                sum -= tr.weight;
                nowT = tr.outSec;

            } else{
                nowT = 0; sum = 0; flag = true;
            }

            while(idx < truck_weights.length && sum + truck_weights[idx] <= weight){
                if(set.contains(nowT + bridge_length)) continue;
                else set.add(nowT + bridge_length);

                truckRec.add(new TruckRecord(truck_weights[idx], nowT + bridge_length));
                sum += truck_weights[idx];
                nowT++; idx++;
            }
        }
        return nowT + 1;
    }

    private class TruckRecord{
        int outSec;
        int weight;

        public TruckRecord(int weight, int outSec){
            this.weight = weight;
            this.outSec = outSec;
        }
    }
}