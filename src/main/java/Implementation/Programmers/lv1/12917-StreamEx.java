package Implementation.Programmers.lv1;

import java.util.Comparator;
import java.util.stream.Collectors;

class Main12917 {
    public String solution(String s) {
        return s.chars().boxed().sorted(Comparator.reverseOrder()).mapToInt(o->o).mapToObj(o->Character.toString((char)o)).collect(Collectors.joining());
    }
}