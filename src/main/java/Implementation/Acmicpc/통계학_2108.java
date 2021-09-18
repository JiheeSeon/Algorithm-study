package Implementation.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class MainA2108{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> minHeap = new PriorityQueue<>(); //중앙값 이상의 수
        PriorityQueue<Long> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // 중앙값 이하의 수

        long nowN;
        long sum = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long existedCnt;

        TreeMap<Long, Long> basicMap = new TreeMap<>(); // 나온 수 (key) -> 출현 횟수
        TreeMap<Long, LinkedList<Long>> countMap = new TreeMap<>(Comparator.reverseOrder()); // 출현횟수 -> 나온 수
        LinkedList<Long> valueTmp;

        for(int i = 0; i < N; i++) {
            nowN = Long.parseLong(br.readLine());
            sum += nowN;
            if(nowN < min) min = nowN;
            if(nowN > max) max = nowN;

            existedCnt = basicMap.getOrDefault(nowN, 0L);
            basicMap.put(nowN, existedCnt + 1);

            if(countMap.containsKey(existedCnt)) countMap.get(existedCnt).remove(nowN);
            valueTmp = countMap.getOrDefault(existedCnt + 1, new LinkedList<>());
            valueTmp.add(nowN);
            countMap.put(existedCnt + 1, valueTmp);

            if(minHeap.size() == maxHeap.size()) minHeap.offer(nowN);
            else maxHeap.offer(nowN); // 사실상 maxHeap.size()가 항상 minHeap.size()(최소 0)보다는 클 것

            if(!minHeap.isEmpty() && !maxHeap.isEmpty()){
                if(minHeap.peek() < maxHeap.peek()){
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(minHeap.poll());
                }
            }
        }

        StringBuilder stb = new StringBuilder();
        stb.append(Math.round(sum / (double) N)).append("\n").append(maxHeap.size() < minHeap.size() ? minHeap.poll() : maxHeap.poll()).append("\n");

        long maxCnt = basicMap.values().stream().mapToLong(l -> l).max().getAsLong();

        long frequentRes;
        LinkedList<Long> list = countMap.get(maxCnt);
        if(list.size() > 1){
            Collections.sort(list);
            frequentRes = list.get(1);
        } else frequentRes = list.get(0);

        stb.append(frequentRes).append("\n");
        stb.append((max - min));
        System.out.println(stb);
    }
}