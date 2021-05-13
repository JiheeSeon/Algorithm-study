package Implementation.Programmers.lv2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class 조이스틱_42860 {
    public int solution(String name) {
        // 1. 각 자리에서 위로 조작하는 경우와 아래로 조작하는 경우 중 최소값으로 더해놓음.
        int sum = 0;
        for(int i = 0; i < name.length(); i++)
            sum += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

        // 2. 앞으로 갈 것인지 뒤로 갈 것인지를 체크해야 함.
        // => A가 1번 이상 나오는 경우 뒤로 갔을 때 나올 수 있는 값을 다 체크해야 함.
        Matcher m = Pattern.compile("[A]+").matcher(name);

        int min = name.length() - 1; // 쭉 오른쪽으로 가는 경우
        while(m.find())
            min = Math.min(min, (m.start() == 0 ? 0 : (m.start() - 1) * 2) + (name.length() - m.end()));

        return sum + min;
    }
}
class Main42860{
    public static void main(String[] args) {
        System.out.println(new 조이스틱_42860().solution("JEROEN")); //56
        System.out.println(new 조이스틱_42860().solution("JAN")); // 23
        System.out.println(new 조이스틱_42860().solution("EEEEAAAAAEAAAE")); // 35
        System.out.println(new 조이스틱_42860().solution("EEEEAAAAAAAAAE")); // 27
    }
}