package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class Main17619{
    static Log[] logs;
    static int[] idxMap;
    static Map<Log, Log> parent = new HashMap<>();
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArray(br.readLine());
        int N = tmp[0]; int Q = tmp[1];

        logs = new Log[N];
        idxMap = new int[N];
        check = new boolean[N];

        for (int i = 0; i < N; i++) {
            tmp = strToIntArray(br.readLine());
            logs[i] = new Log(tmp, i + 1);
            parent.put(logs[i], logs[i]);
        }

        Arrays.sort(logs);
        for (int i = 0; i < N; i++) {
            logs[i].sortedIdx = i;
            idxMap[logs[i].num - 1] = i; // original number -> new Idx
        }

        // union with bfs
        bfs(logs[0]);

        // find
        StringBuilder stb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            tmp = strToIntArray(br.readLine());
            stb.append(find(logs[idxMap[tmp[0] - 1]]).equals(find(logs[idxMap[tmp[1] - 1]])) ? 1 : 0).append("\n");
        }
        System.out.println(stb);
    }

    static void bfs(Log start) {
        Queue<Log> q = new LinkedList<>();
        q.add(start);
        check[start.sortedIdx] = true;

        Log now; int i;

        while (!q.isEmpty()) {
            now = q.poll();
            i = now.sortedIdx;

            while (++i < logs.length) {
                if(check[i]
                        ||!(((logs[i].x1 <= now.x1 && now.x2 <= logs[i].x2)
                        || (now.x1 <= logs[i].x1 && logs[i].x2 <= now.x2))
                        ||(now.x1 <= logs[i].x1 && logs[i].x1 <= now.x2))) continue;

                union(now, logs[i]);
                q.add(logs[i]);
                check[i] = true;
            }
        }
    }

    static void union(Log l1, Log l2) {
        Log pL1 = find(l1);
        Log pL2 = find(l2);

        if(pL1.equals(pL2)) return;

        if(pL1.num < pL2.num) parent.put(pL2, pL1);
        else parent.put(pL1, pL2);
    }

    static Log find(Log log) {
        if(log.equals(parent.get(log)))
            return log;
        else{
            Log pLog = find(parent.get(log));
            parent.put(log, pLog);
            return pLog;
        }
    }

    static int[] strToIntArray(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }

    static private class Log implements Comparable<Log>{
        private int x1, x2, y, num, sortedIdx;

        public Log(int[] coordinateInfo, int num) {
            x1 = coordinateInfo[0];
            x2 = coordinateInfo[1];
            y = coordinateInfo[2];
            this.num = num;
        }

        @Override
        public int compareTo(Log o) {
            if(x1 == o.x1){
                if (x2 == o.x2) {
                    return Integer.compare(y, o.y);
                } else return Integer.compare(x2, o.x2);
            } else return Integer.compare(x1, o.x1);
        }

        @Override
        public String toString() {
            return "Log (x1 = " + x1 + ", x2 = " + x2 + ", num=" + num + ')';
        }
    }
}

/*Test case
8 6
2 8 2
8 10 7
21 25 22
17 20 20
3 5 5
6 7 4
14 17 25
8 14 10
1 3
1 2
1 5
1 8
1 4
1 6
*/