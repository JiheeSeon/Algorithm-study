package Implementation.Programmers.lv2;
import java.util.*;

class 기능개발_42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] time = new int[progresses.length];
        int tmp;
        for(int i = 0; i < progresses.length; i++){
            tmp = (100 - progresses[i]) / speeds[i];
            time[i] = (100 - progresses[i]) % speeds[i] == 0
                    ? tmp : tmp + 1;
        }
        ArrayList<Integer> a = new ArrayList<>();
        int prev = time[0]; int cnt = 1;
        int t = 1;
        while(t < time.length){
            while(t < time.length && time[t] <= prev){
                cnt++;
                t++;
            }
            a.add(cnt);
            cnt = 0;
            if(t != time.length) prev = time[t];
        }

        return a.stream().mapToInt(x->x).toArray();
    }
}
