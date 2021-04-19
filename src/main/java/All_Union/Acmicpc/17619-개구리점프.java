package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

/*구상한 풀이방법
*
*
* */
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

/*Test case 1
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

Testcase 2
59 32
514975552 514985636 16834006
327499462 696970299 404152378
954296767 954296768 577869684
14239385 14239387 404152378
958932239 958933264 91348208
895146368 954027725 47046709
463548818 463550838 47046709
823288989 892256657 484115193
895151737 941684944 18633441
938858887 941285733 445923063
758364004 758364171 558354899
284244019 284244020 410258299
918837860 918842387 526011144
588964520 588964557 370352167
737981676 737983480 306147325
72844498 72919343 445923063
589206834 606628523 43271985
657946397 698139581 577869684
5072145 78684338 558354899
723406381 723408900 16834006
958007195 958047498 484115193
621374213 621388893 526011144
784192303 868514190 47046709
861417437 893140809 16834006
943934475 943934478 856254443
955751280 956081817 410258299
650745165 650745442 856254443
220933970 221090672 577869684
653706473 653706509 406779794
855555950 873506088 404152378
306111777 306112974 525007406
730950709 730950717 305481030
863033810 873846736 43271985
806613205 806613313 558354899
307181201 927213363 491960496
793504679 793692394 64163792
964368383 997641601 306147325
951858201 951865368 525007406
394417434 394417439 487810928
150818545 151916039 487810928
840938316 840938317 445923063
859532363 859618230 525007406
873162538 873561306 577869684
764205028 764205823 926822907
779141731 779394742 91348208
567280449 602756955 18633441
911126151 911168185 406779794
418463994 423536975 305481030
622369020 622447013 836860488
606947827 610928649 445923063
894558102 894558358 487810928
678113097 681301564 526011144
964472354 964600477 487810928
159524936 205264257 18633441
190535554 190554552 406779794
928869818 929853487 836860488
970101267 970101269 305481030
770595103 784343042 408912554
977899753 977899754 43271985
18 7
58 49
21 52
29 21
32 23
43 25
22 15
31 5
41 34
47 36
16 30
47 10
52 24
46 41
6 4
5 33
48 25
10 58
23 30
16 22
26 53
6 45
54 31
10 36
9 8
21 4
20 23
42 5
15 57
5 7
45 20
32 46
*/