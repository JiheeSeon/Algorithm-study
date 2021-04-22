package Backtrack.Acmicpc;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class 알파벳_1987 {
    static int yHeight, xWidth;
    static char[][] graph;
    static boolean[][] check;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        yHeight = tmp[0]; xWidth = tmp[1];

        graph = new char[yHeight][xWidth];
        check = new boolean[yHeight][xWidth];

        String s;
        for (int y = 0; y < yHeight; y++) {
            s = br.readLine();
            for (int x = 0; x < xWidth; x++) {
                graph[y][x] = s.charAt(x);
            }
        }

        dfs(new Point(0, 0), new HashSet<>());
        System.out.println(res);

    }

    static void dfs(Point curr, Set<Character> set) {
        if(curr.y < 0 || curr.x < 0 || curr.y >= yHeight || curr.x >= xWidth
                || check[curr.y][curr.x] || set.contains(graph[curr.y][curr.x])){
            res = Math.max(res, set.size());
            return;
        }

        for (int i = 0; i < 4; i++) {
            check[curr.y][curr.x] = true;
            set.add(graph[curr.y][curr.x]);

            dfs(new Point(curr.y + dy[i], curr.x + dx[i]), set);

            check[curr.y][curr.x] = false;
            set.remove(graph[curr.y][curr.x]);
        }
    }

    static private class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
/*
3 4
CAAE
ADBF
BCEB

10 10
ASWERHGCFH
QWERHDLKDG
ZKFOWOHKRK
SALTPWOKSS
BMDLKLKDKF
ALSKEMFLFQ
GMHMBPTIYU
DMNKJZKQLF
HKFKGLKEOL
OTOJKNKRMW

20 20
POIUYTREWQBWKALSLDLG
LKJHGFDSAMASFBMBOSOZ
NMBVCXZAKPAISJLBMROW
CEVTBFNIMLASNCVKNDKX
VPQLBKENMSAHBBLFOWPQ
ZLSKJJBNBEASZNFDGHHN
GPBMDLQDALAASBBXCEGA
APQIKBMROIBANPOBLMKS
ASKSKVJRPORHNOXZKSPN
LSNVOEOOOKAKANLGKOAX
AKVMBOTOWPQOJBSMSPEP
BLLBKWPEPBKNMROSALLP
BNQLDNBMKOVMEMELSLMA
RLEPQQPVKJRNBITNBSAS
ZXMCOITRPWKLPGKHNGMS
QOBKRPPPZSLEMPNKSPPR
OQJDNZNANDWKQKVJEOGJ
QUYVOIUYWERLKJHASDFV
ZCVWRETPOIUHJKLVBMAS
QWERZCVUIAFDKHSDFHSA

Hidden?
1 3
ABC
*/