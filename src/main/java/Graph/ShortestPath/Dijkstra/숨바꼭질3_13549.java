package Graph.ShortestPath.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

class 숨바꼭질3_13549 {
    int start, end;

    public 숨바꼭질3_13549(int start, int end) {
        this.start = start;
        this.end = end;
    }

    int solution() {
        int[] shortestT = new int[100001];
        Arrays.fill(shortestT, Integer.MAX_VALUE);
        shortestT[start] = 0;

        PriorityQueue<Edge13549> pq = new PriorityQueue<>();
        pq.offer(new Edge13549(start, 0));

        Edge13549 now;

        while (!pq.isEmpty()) {
            now = pq.poll();

            // weight를 시간에 대응. distance 비교를 다익스트라로 함.
            if(now.pos + 1 < 100001 && shortestT[now.pos + 1] > now.time + 1) {
                shortestT[now.pos + 1] = now.time + 1;
                pq.add(new Edge13549(now.pos + 1, now.time + 1));
            }

            if(now.pos >= 0 && now.pos * 2 < 100001 && shortestT[now.pos * 2] > now.time) {
                shortestT[now.pos * 2] = now.time;
                pq.add(new Edge13549(now.pos * 2, now.time));
            }

            if(now.pos >= 1 && shortestT[now.pos - 1] > now.time + 1) {
                shortestT[now.pos - 1] = now.time + 1;
                pq.add(new Edge13549(now.pos - 1, now.time + 1));
            }
        }

        return shortestT[end];
    }
}
class MainA13549{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        if(tmp[0] >= tmp[1]){
            System.out.println(tmp[0] -tmp[1]);
            return;
        }

        System.out.println(new 숨바꼭질3_13549(tmp[0], tmp[1]).solution());
    }
}

class Edge13549 implements Comparable<Edge13549>{
    int pos, time;

    public Edge13549(int pos, int weight) {
        this.pos = pos;
        this.time = weight;
    }

    @Override
    public int compareTo(Edge13549 e){
        return time == e.time ? Integer.compare(pos, e.pos) : Integer.compare(time, e.time);
    }
}