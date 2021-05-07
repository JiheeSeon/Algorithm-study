package Implementation.Programmers.lv2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class 수식최대화_67257 {
    long ans = 0;
    char[] c = {'+', '-', '*'};

    public long solution(String expression) {
        backtrack(0, new ArrayList<>(), expression);
        return ans;
    }

    void backtrack(int depth, ArrayList<Integer> prev, String expression){
        if(depth == 3){
            char op;
            Pattern p; Matcher m;
            String g;
            String[] splitG;
            long res;
            String redExp = expression;

            System.out.println(prev);

            for(int i : prev){
                switch(i){
                    case 0:
                        p = Pattern.compile("[0-9]*\\+-?[0-9]*");
                        if(i == prev.size() - 1) p = Pattern.compile("-?[0-9]*\\+-?[0-9]*");
                        m = p.matcher(redExp);
                        while(m.find()){
                            g = m.group();
                            res = Pattern.compile("\\+").splitAsStream(g).mapToLong(Long::parseLong).sum();
//                            redExp = redExp.replaceAll(g, Integer.toString(res));
                            redExp = redExp.replace(g, Long.toString(res));
                            System.out.println("+  -> " + redExp);
                        }
                        break;
                    case 1:
                        p = Pattern.compile("[0-9]*\\--?[0-9]*");
                        if(i == prev.size() - 1) p = Pattern.compile("-?[0-9]*\\--?[0-9]*");
                        m = p.matcher(redExp.toString());
                        while(m.find()){
                            g = m.group();
                            System.out.println(Arrays.toString(g.split("(?=-)")));
                            res = Arrays.stream(g.split("(?=-)")).mapToLong(Long::parseLong).sum();
                            System.out.println(res);
                            redExp = redExp.replace(g, Long.toString(res));
                            System.out.println("-  -> " + redExp);
                        }
                        break;
                    default:
                        p = Pattern.compile("-[0-9]*\\*-?[0-9]*");
                        if(i == prev.size() - 1) p = Pattern.compile("-?[0-9]*\\*-?[0-9]*");
                        m = p.matcher(redExp.toString());
                        while(m.find()){
                            g = m.group();
                            res = Pattern.compile("\\*").splitAsStream(g).mapToLong(Long::parseLong).reduce((left,right) -> left * right).getAsLong();
                            redExp = redExp.replace(g, Long.toString(res));
                            System.out.println(redExp);
                            System.out.println("*  -> " + redExp);
                        }
                        break;
                }
            }
            System.out.println(redExp);
            res = Long.parseLong(redExp);
            if(ans < res) ans = res;
            return;
        }
        for(int i = 0; i < 3; i++){
            if(prev.contains(i)) continue;

            prev.add(i);
            backtrack(depth + 1, prev, expression);
            prev.remove((Object)i);
        }
    }
}
class Main67257{
    public static void main(String[] args) {
//        System.out.println(Arrays.stream("-10-150".split("(?=-)")).mapToInt(Integer::parseInt).sum());
        System.out.println(new 수식최대화_67257().solution("100-200*300-500+20"));
//        System.out.println(new 수식최대화_67257().solution("50*6-3*2"));
    }
}
