package String.Programmers.lv1;

import java.util.stream.*;

class Main12926 {
    public String solution(String s, int n) {
        return s.chars().map(o->o==32?32:(o >='a'? (o-'a'+n)%26+'a': (o-'A'+n)%26+'A')).mapToObj(o->Character.toString((char)o)).collect(Collectors.joining());
    }
}