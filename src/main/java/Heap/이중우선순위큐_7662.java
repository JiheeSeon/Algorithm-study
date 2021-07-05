package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 이중우선순위큐_7662 {
    int opN;
    String[] operations;

    Set<Element> removed;
    PriorityQueue<Element> minHeap;
    PriorityQueue<Element> maxHeap;

    public 이중우선순위큐_7662(int opN, String[] operations) {
        this.opN = opN;
        this.operations = operations;
    }

    String solution() {
        StringBuilder stb = new StringBuilder();

        String[] operation;

        minHeap = new PriorityQueue<>(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return o1.value == o2.value ? Integer.compare(o1.idx, o2.idx) : Integer.compare(o1.value, o2.value) ;
            }
        });

        maxHeap = new PriorityQueue<>(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return o1.value == o2.value ? Integer.compare(o1.idx, o2.idx) : Integer.compare(o2.value, o1.value) ;
            }
        });
        removed = new HashSet<>();

        Element polled;

        bigLoop:
        for (int i = 0; i < opN; i++) { // O(opN)
            operation = operations[i].split(" ");

            switch(operation[0]){
                case "I":
                    Element e = new Element(i, Integer.parseInt(operation[1]));
                    maxHeap.add(e);
                    minHeap.add(e);
                    break;
                case "D":
                    if(maxHeap.isEmpty() || minHeap.isEmpty()) continue;

                    if(operation[1].equals("1")){
                        while (removed.contains(polled = maxHeap.poll())) {
                            if(maxHeap.isEmpty()) continue bigLoop;
                        }
                    } else{

                        while (removed.contains(polled = minHeap.poll())) {
                            if(minHeap.isEmpty()) continue bigLoop;
                        }
                    }

                    removed.add(polled);
                    break;
            }
        }

        if(maxHeap.isEmpty() || minHeap.isEmpty()) return stb.append("EMPTY").append("\n").toString();
        maxHeap.removeAll(removed);
        minHeap.removeAll(removed);
        if(maxHeap.isEmpty() || minHeap.isEmpty()) return stb.append("EMPTY").append("\n").toString();

        int max = maxHeap.poll().value;
        if(maxHeap.size() == 1) return stb.append(max).append(" ").append(max).append("\n").toString();

        int min = minHeap.poll().value;
        return stb.append(max).append(" ").append(min).append("\n").toString();
    }
}

class Element{
    int idx;
    int value;

    public Element(int idx, int value) {
        this.idx = idx;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return idx == element.idx && value == element.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, value);
    }

    @Override
    public String toString() {
        return "Element{" +
                "idx=" + idx +
                ", value=" + value +
                '}';
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