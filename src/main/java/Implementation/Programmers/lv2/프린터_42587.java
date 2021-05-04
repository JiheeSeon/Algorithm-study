package Implementation.Programmers.lv2;

import java.util.*;

class 프린터_42587 {
    public int solution(int[] priorities, int location) {
        Document[] docs = new Document[priorities.length];

        for (int i = 0; i < priorities.length; i++)
            docs[i] = new Document(i, priorities[i]);

        Queue<Document> q = new LinkedList<>();
        for (Document i : docs) q.add(i);

        int[] ans = new int[priorities.length];

        Arrays.sort(docs);

        int rank = 0;
        Document front;

        while (!q.isEmpty()) {
            front = q.poll();
            while (front.priority < docs[rank].priority) {
                q.add(front);
                front = q.poll();
            }

            ans[front.idx] = ++rank;
        }
        return ans[location];
    }

    private class Document implements Comparable<Document> {
        int idx;
        int priority;

        public Document(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }

        @Override
        public int compareTo(Document d) {
            return Integer.compare(d.priority, priority);
        }

        @Override
        public String toString() {
            return "(" + this.idx + " -> " + this.priority + ")";
        }
    }
}