package Roadmap.Lv1;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class 최소최대_10818 {
    // make minheap, maxheap -> 1000ms [O(N)(*2)]
    static String solve1(int[] input) {
        StringBuilder stb = new StringBuilder();
        PriorityQueue<Integer> minHeap = Arrays.stream(input).boxed().collect(Collectors.toCollection(PriorityQueue::new));
        stb.append(minHeap.poll());
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(Arrays.stream(input).boxed().collect(Collectors.toCollection(ArrayList::new)));
        stb.append(" ").append(maxHeap.poll());

        return stb.toString();
    }

    // using stream methods -> 732ms
    static String solve2(int[] input) {
        int min = Arrays.stream(input).min().getAsInt();
        int max = Arrays.stream(input).max().getAsInt();
        return min + " " + max;
    }

    // basic impl -> 724ms
    static String solve3(int[] input){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i : input) {
            if(min > i) min = i;
            if(max < i) max = i;
        }
        return min + " " + max;
    }

    // sort & use first and last val-> 1568ms [O(Nlog(N))]
    static String solve4(int[] input) {
        Arrays.sort(input);
        return input[0] + " " + input[input.length - 1];
    }
}
class MainA10818{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] input = InputProcessor.strToIntArr(br.readLine());
        System.out.println(최소최대_10818.solve4(input));
    }
}