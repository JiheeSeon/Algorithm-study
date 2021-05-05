package Implementation.Programmers.lv2;
import java.util.*;
import java.util.stream.*;
import java.util.*;
import java.util.stream.*;

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
            StringBuilder stb1 = new StringBuilder(a).append(b);
            StringBuilder stb2 = new StringBuilder(b).append(a);

            return -stb1.compareTo(stb2);
        }
    }
}