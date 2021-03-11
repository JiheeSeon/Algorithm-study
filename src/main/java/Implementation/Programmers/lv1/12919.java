package Implementation.Programmers.lv1;

import java.util.*;
import java.util.stream.*;

class Main12919 {
    public String solution(String[] seoul) {
        ArrayList<String> seoulList = (ArrayList<String>) Arrays.stream(seoul).collect(Collectors.toList());
        return new StringBuilder().append("김서방은 ").append(seoulList.indexOf("Kim")).append("에 있다").toString();
    }
}