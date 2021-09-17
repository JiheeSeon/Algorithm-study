package Implementation.Acmicpc;

import Util.InputProcessor;

import java.io.*;
import java.util.*;

class 프린터큐_1966{
    int numOfDocs, docToFind;
    int[] priority;

    Queue<Document> printQueue; // priority(priorityMap -> key)가 현재 poll 한 item과 같으면 빼도록
    Map<Integer, LinkedList<Integer>> priorityMap; // 우선순위가 같은 그룹마다 어떤 라벨의 문서들이 있는지 저장, 순서에 맞춰 순회한다는 장점

    public 프린터큐_1966(int numOfDocs, int docToFind, int[] priority) {
        this.numOfDocs = numOfDocs;
        this.docToFind = docToFind;
        this.priority = priority;

        init();
    }

    void init() {
        printQueue = new LinkedList<>();
        priorityMap = new TreeMap<>(Comparator.reverseOrder());

        LinkedList<Integer> p;

        for (int i = 0; i < numOfDocs; i++) {
            if(!priorityMap.containsKey(priority[i])) priorityMap.put(priority[i], new LinkedList<>());
            p = priorityMap.get(priority[i]);
            p.add(priority[i]);

            printQueue.add(new Document(i, priority[i]));
        }
    }

    int solve() {
        if(numOfDocs == 1) return 1;

        int popPriority;
        int docNWithSamePriority;
        LinkedList<Integer> labelsWithSamePriority;
        Document d;
        int popN = 0;

        for(Map.Entry<Integer, LinkedList<Integer>> entry : priorityMap.entrySet()) {
            popPriority = entry.getKey();
            labelsWithSamePriority = entry.getValue();

            docNWithSamePriority = labelsWithSamePriority.size();

            while (docNWithSamePriority > 0) {
                d = printQueue.poll();

                if(d.priority == popPriority){
                    popN++;
                    docNWithSamePriority--;
                    if(d.label == docToFind) return popN;
                } else{
                    printQueue.add(d);
                }
            }
        }

        return popN;
    }

    private class Document{
        int label;
        int priority;

        public Document(int label, int priority) {
            this.label = label;
            this.priority = priority;
        }
    }
}

class MainA1966{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder stb = new StringBuilder();
        int[] info;
        int numOfDocs, docToFind;

        while (T-- > 0) {
            info = InputProcessor.strToIntArr(br.readLine());
            numOfDocs = info[0]; docToFind = info[1];
            info = InputProcessor.strToIntArr(br.readLine()); // 우선순위
            stb.append(new 프린터큐_1966(numOfDocs, docToFind, info).solve()).append("\n");
        }

        System.out.print(stb);
    }
}