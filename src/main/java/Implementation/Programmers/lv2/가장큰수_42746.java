package Implementation.Programmers.lv2;
import java.util.*;
import java.util.stream.*;
import java.util.*;
import java.util.stream.*;

class Solution_Timeout {
    String s = "";
    int maxDepth = 1;

    public String solution(int[] numbers) {
        maxDepth = numbers.length;
        backtrack(0, new StringBuilder(), new boolean[maxDepth], numbers);
        return s;
    }

    void backtrack(int depth, StringBuilder stb, boolean[] check, int[] numbers){
        if(depth == maxDepth){
            if(stb.toString().compareTo(s) > 0)
                s = stb.toString();
            return;
        }

        for(int i = 0; i < maxDepth; i++){
            if(check[i]) continue;

            check[i] = true;
            stb.append(numbers[i]);
            backtrack(depth + 1, stb, check, numbers);
            stb.setLength(stb.length() - (Integer.toString(numbers[i]).length()));
            check[i] = false;
        }
    }
}

class 가장큰수_42746 {
    String s = "";
    int maxDepth = 1;

    public String solution_timeout(int[] numbers) {
        maxDepth = numbers.length;
        backtrack(0, new StringBuilder(), new boolean[maxDepth], numbers);
        return s;
    }

    void backtrack(int depth, StringBuilder stb, boolean[] check, int[] numbers){
        if(depth == maxDepth){
            if(stb.toString().compareTo(s) > 0)
                s = stb.toString();
            return;
        }

        for(int i = 0; i < maxDepth; i++){
            if(check[i]) continue;

            check[i] = true;
            stb.append(numbers[i]);
            backtrack(depth + 1, stb, check, numbers);
            stb.setLength(stb.length() - (Integer.toString(numbers[i]).length()));
            check[i] = false;
        }
    }
    public String solution(int[] numbers) {
        return Arrays.stream(numbers).mapToObj(n -> (String) Integer.toString(n)).sorted(new StrComparator()).collect(Collectors.joining());
    }
    class StrComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            if(a.length() == b.length()) return -a.compareTo(b);
            if(a.charAt(0) != b.charAt(0)) return -a.compareTo(b);

            int aIdx = 0; int bIdx = 0;
            StringBuilder stb = new StringBuilder();

            while(aIdx < a.length() && bIdx < b.length()){
                if(a.charAt(aIdx) > b.charAt(bIdx)) return -1;
                else if(a.charAt(aIdx) < b.charAt(bIdx)) return 1;
                else stb.append(a.charAt(aIdx));
                aIdx++; bIdx++;
            }
            int delta = 0;
            int toRet;

            if(aIdx == a.length()){ // b가 더 길다 -> stb는 a용
                while(aIdx < b.length()){
                    stb.append(b.charAt(delta++));
                    aIdx++;
                }
                toRet = -stb.toString().compareTo(b);
                return toRet == 0 ? 1 : toRet;
            }
            else{ // a가 더 길다 -> stb는 b용
                while(bIdx < a.length()){
                    stb.append(a.charAt(delta++));
                    bIdx++;
                }
                toRet = -a.compareTo(stb.toString());
                return toRet == 0 ? -1 : toRet;
            }
        }
    }
}