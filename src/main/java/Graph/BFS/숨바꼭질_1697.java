package Graph.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class 숨바꼭질_1697 {
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int start = tmp[0]; int end = tmp[1];
        check = new int[100003];
        bfs(start, end);
        if(start == end) System.out.println(0);
        else System.out.println(check[end]);
    }

    static void bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        int now;
        int next;
        int[] candidate;

        while (!q.isEmpty()) {
            now = q.poll();
            candidate = new int[]{-1, 1, now};

            for (int i = 0; i < 3; i++) {
                next = now + candidate[i];
                if(next < 0 || next >= 100000 || (check[next] != 0 && check[next] <= check[now] + 1)) continue;

                q.add(next);
                check[next] = check[now] + 1;
                if(next == end) return;
            }
        }
    }
}
/*Testcase
6 16
3

8 20
3

15964 89498
4781

3 43
6

4 27
5

5 35
5

6 43
5

7 43
6

0 0
2
*/