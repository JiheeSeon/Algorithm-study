package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
1655 가운데를 말해요

최소힙 -> 가장 작은 값을 바로 접근 가능
최대힙 -> 가장 큰 값을 바로 접근 가능


*/
class 가운데를말해요_1655 {
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder stb = new StringBuilder();
        int val;

        for(int i = 0; i < N; i++){
            val = Integer.parseInt(br.readLine());
            if(i == 0){
                stb.append(val).append("\n");
                maxHeap.add(val);
            }else{
                stb.append(getMedian(val)).append("\n");
            }
        }
        System.out.print(stb);
    }

    static int getMedian(int value) {
        // 먼저 넣어야 할 곳에 넣고
        if(maxHeap.peek() > value) maxHeap.add(value);
        else minHeap.add(value);

        // 양 조절
        if(maxHeap.size() > minHeap.size() + 1){
            minHeap.add(maxHeap.poll());
        }else if(maxHeap.size() < minHeap.size()){
            maxHeap.add(minHeap.poll());
        }

        return maxHeap.peek();
    }
}