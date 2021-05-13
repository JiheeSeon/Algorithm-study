package Implementation.Programmers.lv2;

import java.util.*;

class 괄호변환_60058 {
    public String solution(String p) {
        if(isRightString(p)) return p;
        return makeRightString(p);
    }

    String makeRightString(String w){
        if(w.equals("")) return "";

        int[] leftP = new int[w.length()];
        int[] rightP = new int[w.length()];

        int l = 0, r = 0;
        int i;
        for(i = 0; i < w.length(); i++){
            if(w.charAt(i) == '(') l++;
            else if(w.charAt(i) == ')') r++;
            leftP[i] = l;
            rightP[i] = r;

            if(l == r) break;
        }

        String u = w.substring(0, i + 1);
        String v = w.substring(i + 1, w.length());

        String forV = makeRightString(v);
        if(isRightString(u)) return u.concat(forV);

        StringBuilder stb = new StringBuilder();

        stb.append("(").append(forV).append(")");

        Map<Character, Character> reverseP = new HashMap<Character, Character>();
        reverseP.put('(', ')'); reverseP.put(')', '(');

        for(int j = 1; j < u.length() - 1; j++)
            stb.append(reverseP.get(u.charAt(j)));

        return stb.toString();
    }


    boolean isRightString(String s){
        Stack<Character> stack = new Stack<>();
        char c;
        for(int i = 0; i < s.length(); i++){
            c = s.charAt(i);
            if(c == '(') stack.push(c);
            else{
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.size() == 0;
    }
}