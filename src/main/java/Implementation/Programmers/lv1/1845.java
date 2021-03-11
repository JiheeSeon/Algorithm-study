package Implementation.Programmers.lv1;

import java.util.*;

class Main1845 {
    public int solution(int[] nums) {
        int pickNum = nums.length / 2;
        int[] distinct = Arrays.stream(nums).distinct().toArray();
        return Math.min(pickNum, distinct.length);
    }
}