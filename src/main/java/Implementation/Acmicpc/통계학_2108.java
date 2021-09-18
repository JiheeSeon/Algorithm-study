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

// Reference https://blog.naver.com/jihogrammer/222281999239
class MainA2108_Other {

    private static final int MEAN = 0, MEDIAN = 1, MODE = 2, RANGE = 3, MAX = 4000;

    public static void main(String[] args) throws Exception {

        int N = read(13); // Fixed Odd Number
        int[] number = new int[(MAX << 1) + 1]; // 0 ~ 8001 -> -4000 ~ 4000
        int[] result = new int[4]; // MEAN = 0, MEDIAN = 1, MODE = 2, RANGE = 3

        int sum = 0;
        int min = MAX;
        int max = ~MAX;

        for (int i = 0; i < N; i++) {

            // Optimization
            int num = read(13);

            // MEAN
            sum += num;

            // RANGE
            if (min > num) min = num;
            if (max < num) max = num;

            // Count
            num += MAX;
            number[num]++;

            // MODE
            if (result[MODE] < number[num])
                result[MODE] = number[num];

        }

        // Get Values
        double flag = sum > 0 ? 0.5 : -0.5;
        result[MEAN]  = (int) ((double) sum / N + flag);
        result[RANGE] = max - min;
        getValues(number, result, N/2);

        // Print Result
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) sb.append(result[i]).append('\n');
        System.out.print(sb);
    }

    private static void getValues(int[] number, int[] result, int mid) {
        int len  = MAX << 1;
        int mode = result[MODE];
        int cnt  = 0;
        int modeFlag = 0;
        boolean medianFlag = true;

        for (int i = 0; i <= len; i++) {
            if (number[i] < 1) continue;

            if (medianFlag && (cnt += number[i]) > mid) {
                medianFlag = false;
                result[MEDIAN] = i - MAX;
            }

            if (modeFlag < 2 && number[i] == mode) {
                modeFlag++;
                result[MODE] = i - MAX;
            }

            if (!medianFlag && modeFlag > 1) break;

        }
    }

    private static int read(int flag) throws Exception {
        int c, N = System.in.read() - 48;

        if (N + 48 == '-') {
            N = 0;
            while ((c = System.in.read()) > flag) N = 10 * N - c + 48;
        } else
            while ((c = System.in.read()) > flag) N = 10 * N + c - 48;

        return N;

    }
}