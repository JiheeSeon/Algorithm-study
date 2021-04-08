package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

class Main4195 {
    static int F;
    static Map<String, String> parent;
    static Map<String, Integer> setN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int testcaseN = Integer.parseInt(br.readLine());

        for (int t = 0; t < testcaseN; t++) {
            F = Integer.parseInt(br.readLine());

            parent = new LinkedHashMap<>();
            setN = new LinkedHashMap<>();

            String[] relations;
            String benchmark;

            for (int k = 0; k < F; k++) {
                relations = br.readLine().split(" ");
                union(relations[0], relations[1]);

                benchmark = find(relations[0]);

                stb.append(setN.get(benchmark)).append("\n");
            }
        }
        System.out.println(stb);
    }

    static void union(String s1, String s2) {
        String pS1 = find(s1);
        String pS2 = find(s2);

        if (pS1.equals(pS2)) return;

        if (pS1.compareTo(pS2) < 0){
            parent.put(pS2, pS1);
            setN.put(pS1, setN.getOrDefault(pS2, 1) + setN.getOrDefault(pS1, 1));
            setN.remove(pS2);
        }
        else{
            parent.put(pS1, pS2);
            setN.put(pS2, setN.getOrDefault(pS1, 1) + setN.getOrDefault(pS2, 1));
            setN.remove(pS1);
        }
    }

    static String find(String s) {
        parent.putIfAbsent(s, s);

        if (parent.get(s).equals(s)) return s;
        else {
            String toRet = find(parent.get(s));
            parent.put(s, toRet);
            return toRet;
        }
    }
}