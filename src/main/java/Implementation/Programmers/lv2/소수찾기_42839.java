package Implementation.Programmers.lv2;

import java.util.*;
import java.util.stream.*;
import java.util.regex.Pattern;

class 소수찾기_42839 {
    int[] nums;
    int[] primes;
    Set<Integer> result = new HashSet<>();

    public int solution(String numbers) {
        nums = Pattern.compile("").splitAsStream(numbers).mapToInt(Integer::parseInt).toArray();
        primes = IntStream.rangeClosed(0, (int)Math.pow(10, numbers.length()) + 1).toArray();

        primes[1] = 0;
        // 에라토스테네스의 체 구현 -> 소수 선별
        for(int i = 2; i < primes.length; i++){
            if(primes[i] == 0) continue;

            for(int j = 2 * i; j < primes.length; j+=i){
                primes[j] = 0;
            }
        }

        // backtrack
        for(int i = 1; i <= nums.length; i++){
            backtrack(0, i, new ArrayList<>(), new StringBuilder());
        }

        return result.size();
    }
    boolean isPrimeNumber(int n){
        return primes[n] != 0;
    }
    void backtrack(int depth, int maxDepth, ArrayList<Integer> prevIdx, StringBuilder stb){
        if(depth == maxDepth){
            int val = Integer.parseInt(stb.toString());
            if(isPrimeNumber(val)) result.add(val);
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(prevIdx.contains(i)) continue;

            prevIdx.add(i);
            stb.append(nums[i]);
            backtrack(depth + 1, maxDepth, prevIdx, stb);
            prevIdx.remove((Object)i);
            stb.setLength(stb.length() - 1);
        }
    }
}