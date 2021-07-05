package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
반례
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

앞에꺼부터 더하는 게 안 통함.
*/
class 카드정렬하기_1715 {
    int N;
    PriorityQueue<Integer> minHeap;

    public 카드정렬하기_1715(int n, LinkedList<Integer> list) {
        N = n;
        minHeap = new PriorityQueue<>(list);
    }

    int solution() {
        int now;
        int summed = 0;
        int ret = 0;

        while (!minHeap.isEmpty()) {
            now = minHeap.poll();

            // 첫 묶음일 경우
            if(summed == 0){
                summed = now;
                continue;
            }

            summed += now;
            ret += summed;
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