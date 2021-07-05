package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class 이중우선순위큐_7662 {
    int opN;
    String[] operations;

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public 이중우선순위큐_7662(int opN, String[] operations) {
        this.opN = opN;
        this.operations = operations;
    }

    String solution() {
        StringBuilder stb = new StringBuilder();

        boolean maxHeapFlag = true;
        String[] operation;

        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        // 총체적으로 최대 O(1000000 * 1000000 * lg(1000000))
        for (int i = 0; i < opN; i++) { // O(opN)
            operation = operations[i].split(" ");

            switch(operation[0]){
                case "I":
                    if(maxHeapFlag) maxHeap.add(Integer.parseInt(operation[1])); // lgn
                    else minHeap.add(Integer.parseInt(operation[1]));
                    break;
                case "D":
                    if(maxHeapFlag && maxHeap.isEmpty()) continue;
                    if(!maxHeapFlag && minHeap.isEmpty()) continue;

                    if(operation[1].equals("1")){
                        if(maxHeapFlag) maxHeap.poll();
                        else{
                            maxHeap.addAll(minHeap); // O(m * lgm)
                            maxHeap.poll(); // O(1)
                            minHeap = new PriorityQueue<>();
                            maxHeapFlag = true;
                        }
                    } else{
                        if(maxHeapFlag){
                            minHeap.addAll(maxHeap); // O(n * lgn)
                            minHeap.poll(); // O(1)
                            maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
                            maxHeapFlag = false;
                        } else{
                            minHeap.poll();
                        }
                    }
                    break;
            }
        }

        int max, min;

        if(maxHeap.isEmpty() && minHeap.isEmpty())
            stb.append("EMPTY").append("\n");
        else {
            if(maxHeapFlag){
                max = maxHeap.poll();
                if(maxHeap.isEmpty()) min = max;
                else{
                    minHeap.addAll(maxHeap);
                    min = minHeap.poll();
                }
            }else{
                min = minHeap.poll();
                if(minHeap.isEmpty()) max = min;
                else{
                    maxHeap.addAll(minHeap);
                    max = maxHeap.poll();
                }
            }
            stb.append(max).append(" ").append(min).append("\n");
        }

        return stb.toString();
    }


}
class MainA7662{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int opN;
        String[] operations;

        StringBuilder stb = new StringBuilder();
        while (--T >= 0) {
            opN = Integer.parseInt(br.readLine());
            operations = new String[opN];

            for(int i = 0; i < opN; i++) operations[i] = br.readLine();
            stb.append(new 이중우선순위큐_7662(opN, operations).solution());
        }
        System.out.print(stb);
    }
}