package Implementation.Programmers.lv2;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class 튜플_64065 {
    public int[] solution(String s) {
        // input 문자열 처리
        String stripped = s.substring(1, s.length() - 1);
        Pattern p = Pattern.compile("\\{[[0-9]*,]*[0-9]+\\}"); //숫자 또는 쉼표 중에 0개 이상 나온 후 숫자로 끝나야 함
        Matcher m = p.matcher(stripped);

        int groupN = 0; // groupCount() 메소드는 다른 것.
        String thisStr, original;
        int[] tmpArr;
        Map<Integer, int[]> map = new HashMap<>();
        while(m.find()){ // 패턴이 일치하는 경우 true를 반환, 그 위치로 이동(여러개가 매칭되는 경우 반복 실행가능)
            original = m.group();
            thisStr = original.substring(1, original.length() - 1);
            tmpArr = Pattern.compile(",").splitAsStream(thisStr).mapToInt(Integer::parseInt).toArray();
            map.put(tmpArr.length, tmpArr);
            groupN++;
        }

        int[] ans = new int[groupN];
        Set<Integer> alreadyIn = new HashSet<>();
        for(int key = 1; key <= groupN; key++){
            tmpArr = map.get(key);
            for(int val : tmpArr){
                if(!alreadyIn.contains(val)){
                    ans[key - 1] = val;
                    alreadyIn.add(val);
                    break;
                }
            }
        }
        return ans;
    }
}
class Main64065{
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 튜플_64065().solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")));
        System.out.println(Arrays.toString(new 튜플_64065().solution("{{20,111},{111}}")));
        System.out.println(Arrays.toString(new 튜플_64065().solution("{{123}}")));
    }
}
