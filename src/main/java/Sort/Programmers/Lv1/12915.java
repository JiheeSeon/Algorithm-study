package Sort.Programmers.Lv1;

import java.util.*;
import java.util.Comparator;

class Main12915 {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings);
        Arrays.sort(strings, Comparator.comparingInt((String i) -> i.charAt(n)));
        return strings;
    }
}