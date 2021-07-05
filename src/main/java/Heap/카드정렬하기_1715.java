package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
반례 #1
10
10
10
10
10
10
10
10
10
10
10

반례 #2
6
15
30
31
32
50
54

A + B + A + B + C <= A + B + C + D
즉, A + B <= D 일 때만 기존의 방식이 통함. (앞에서부터 주루룩 더하는 거)

수정한 방식은, 가장 작은 합이 나올 수 있는 것들의 합이 결론적인 최소합이 됨.
이 때, 가장 작은 원소 2개를 더한 것이 sum의 최솟값이므로
minHeap을 만들어, 매번 구한 합을 다시 힙에 넣음.

heap 에 최종적으로 원소가 하나 남으면 더 이상 더할 쌍이 없는 것.
*/

class 카드정렬하기_1715 {
    int N;
    PriorityQueue<Integer> minHeap;

    public 카드정렬하기_1715(int n, LinkedList<Integer> list) {
        N = n;
        minHeap = new PriorityQueue<>(list);
    }

    int solution() {
        if(N == 1) return 0;

        int now, next;
        boolean firstFlag = true;
        int ret = 0;

        while (minHeap.size() >= 2) {
            now = minHeap.poll();
            next = minHeap.poll();

            if(firstFlag){
                firstFlag = false;
                ret += now + next;
                minHeap.add(now + next);
                continue;
            }
            minHeap.add(now + next);
            ret += now + next;
        }
        return ret;
    }
}

class MainA1715{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < N; i++)
            list.add(Integer.parseInt(br.readLine()));

        System.out.println(new 카드정렬하기_1715(N, list).solution());
    }
}