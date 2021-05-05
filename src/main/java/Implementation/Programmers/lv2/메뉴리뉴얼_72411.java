package Implementation.Programmers.lv2;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class 메뉴리뉴얼_72411 {
    String[] arrangedOrders = {};
    Map<String, Integer> appearanceMap = new TreeMap<>(); // menu -> fromN

    public String[] solution(String[] orders, int[] course) {
        arrangedOrders = new String[orders.length];

        String s;
        for(int i = 0; i < orders.length; i++){
            s = orders[i];
            arrangedOrders[i] = Pattern.compile("").splitAsStream(s).sorted().collect(Collectors.joining());
            for(int j : course)
                backtrack(0, j, -1, new StringBuilder(), arrangedOrders[i]);
        }

        Map<Integer, ArrayList<String>> res = new HashMap<>(); // menuN -> str arraylist <Menu>
        Map<Integer, Integer> maxMap = new HashMap<>(); // menuN -> fromN

        for(int c : course)
            res.put(c, new ArrayList<>());

        int menuN; int fromN;
        for (String menu : appearanceMap.keySet()) {
            if(appearanceMap.get(menu) == 1) continue;

            menuN = menu.length();
            fromN = appearanceMap.get(menu);

            ArrayList<String> aa;

            if(!maxMap.containsKey(menuN)){
                maxMap.put(menuN, fromN);
                aa = res.get(menuN);
                aa.add(menu);
                res.put(menuN, aa);
            }
            else{
                if(maxMap.get(menuN) < fromN) {
                    maxMap.put(menu.length(), fromN);
                    aa = new ArrayList<>();
                    aa.add(menu);
                    res.put(menuN, aa);
                } else if(maxMap.get(menuN) == fromN){
                    res.get(menuN).add(menu);
                }
            }
        }

        ArrayList<String> strings = new ArrayList<>();
        for(Integer i : res.keySet()){
            strings.addAll(res.get(i));
        }

        return strings.stream().sorted().toArray(String[]::new);
    }

    void backtrack(int depth, int maxDepth, int prev, StringBuilder stb, String original){
        if(depth == maxDepth){
            appearanceMap.put(stb.toString(), appearanceMap.getOrDefault(stb.toString(), 0) + 1);
            return;
        }
        for (int i = prev + 1; i < original.length(); i++) {
            stb.append(original.charAt(i));
            backtrack(depth + 1, maxDepth, i, stb, original);
            stb.setLength(stb.length() - 1);

        }
    }

    private class MenuInfo{
        String menuName;
        int fromN;

        public MenuInfo(String menuName, int fromN) {
            this.menuName = menuName;
            this.fromN = fromN;
        }
    }
}
class Main72411{
    public static void main(String[] args) {
        //["AC", "ACDE", "BCFG", "CDE"]
        System.out.println(Arrays.toString(new 메뉴리뉴얼_72411().solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2,3,4})));
        // ["ACD", "AD", "ADE", "CD", "XYZ"]
        System.out.println(Arrays.toString(new 메뉴리뉴얼_72411().solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2,3,5})));
        // ["WX", "XY"]
        System.out.println(Arrays.toString(new 메뉴리뉴얼_72411().solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2,3,4})));

    }
}