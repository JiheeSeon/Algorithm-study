package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class Main17619{
    static Log[] logs;
    static int[] idxMap;
    static Map<Log, Log> parent = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = strToIntArray(br.readLine());
        int N = tmp[0]; int Q = tmp[1];

        logs = new Log[N];
        idxMap = new int[N];

        for (int i = 0; i < N; i++) {
            tmp = strToIntArray(br.readLine());
            logs[i] = new Log(tmp, i + 1);
            parent.put(logs[i], logs[i]);
        }

        Arrays.sort(logs);
        for (int i = 0; i < N; i++) {
            idxMap[logs[i].num] = i;
        }

        // union
        for (int e = 1; e < N; e++) {
            if(logs[e - 1].x1 <= logs[e].x1 && logs[e].x1 <= logs[e - 1].x2)
                union(logs[e - 1], logs[e]);
        }

        // find
        StringBuilder stb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            tmp = strToIntArray(br.readLine());
            stb.append(find(logs[tmp[0] - 1]).equals(find(logs[tmp[1] - 1])) ? 1 : 0).append("\n");
        }
        System.out.println(stb);
    }

    static void union(Log l1, Log l2) {
        Log pL1 = find(l1);
        Log pL2 = find(l2);

        if(pL1.equals(pL2)) return;

        if(pL1.compareTo(pL2) < 0) parent.put(pL2, pL1);
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
        private int x1, x2, y, num;

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
    }
}