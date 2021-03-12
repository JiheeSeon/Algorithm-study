package Sort.Programmers.Lv1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Main12933{
    static long solution(long n) {
        /* Failed approach
        Integer[] ns = (Integer[]) Pattern.compile("").splitAsStream(Long.toString(n)).mapToInt(Integer::parseInt).boxed().toArray();
        Integer[] ns = (Integer[]) Pattern.compile("").splitAsStream(Long.toString(n)).map(Integer::parseInt).toArray();
        Integer[] ns =  (Integer[]) Pattern.compile("").splitAsStream(Long.toString(n)).mapToInt(Integer::parseInt).mapToObj(o->(Integer)o).toArray();
        * */
        /* Advanced approach
        Integer.toString(n).chars().sorted().forEach(c -> res = Character.valueOf((char)c) + res);
        * */
        return Long.parseLong(Pattern.compile("").splitAsStream(Long.toString(n)).mapToInt(Integer::parseInt).boxed().sorted(Comparator.reverseOrder()).map(o->Integer.toString(o)).collect(Collectors.joining()));

    }

    public static void main(String[] args) {
        System.out.println(solution(123024958L));
    }
}