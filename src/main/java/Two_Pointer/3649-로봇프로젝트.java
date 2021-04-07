package Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main3649{
    static int x;
    static int legoN;
    static int[] lenOfLegos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String xStr;
        while((xStr = br.readLine()) != null) {
            x = Integer.parseInt(xStr) * 10000000;
            legoN = Integer.parseInt(br.readLine());
            lenOfLegos = new int[legoN];

            for (int i = 0; i < legoN; i++) {
                lenOfLegos[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(lenOfLegos);

            System.out.println(solution());
        }
    }
    static String solution(){
        StringBuilder stb = new StringBuilder();
        List<LegoComb> legoCombs = new ArrayList<>();

        int start = 0, end = lenOfLegos.length - 1;

        while(true){
            if(start >= end) break;
            else if(lenOfLegos[start] + lenOfLegos[end] <= x){
                if(lenOfLegos[start] + lenOfLegos[end] == x)
                    legoCombs.add(new LegoComb(lenOfLegos[start], lenOfLegos[end]));
                start++;
            }
            else end--;
        }
        if(legoCombs.isEmpty()) return stb.append("danger").toString();

        legoCombs.sort(Comparator.comparingInt(l -> Math.abs(l.l1-l.l2)));
        LegoComb legoComb = legoCombs.get(legoCombs.size() - 1);

        return stb.append("yes").append(" ").append(legoComb.l1).append(" ").append(legoComb.l2).toString();
    }
    static private class LegoComb{
        int l1, l2;

        public LegoComb(int l1, int l2) {
            this.l1 = l1;
            this.l2 = l2;
        }
    }
}