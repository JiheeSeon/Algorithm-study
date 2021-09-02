package Roadmap.Lv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class MainA2693{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();
        PriorityQueue<Integer> pq;
        while (T-- > 0) {
            pq = new PriorityQueue<>(Comparator.reverseOrder());
            pq.addAll(Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).boxed().collect(Collectors.toCollection(ArrayList::new)));
            pq.poll(); pq.poll();
            stb.append(pq.poll()).append("\n");
        }
        System.out.print(stb);
    }
}