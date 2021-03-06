package Implementation.Programmers.lv2;

import java.util.*;

// 풀이방법이 잘 생각이 안 나
// 이진트리에서 parent 올라가는 경로를 저장해서 공통 부모의 하나 아래에서까지 경합 수 count.

class 예상대진표_12985 {
    public int solution(int n, int a, int b){
        int min = n - 1 + Math.min(a, b);
        int max = n - 1 + Math.max(a, b);

        int cnt = 0;
        while(min != 0 && max != 0){
            if(min == max) break;
            cnt++;
            min /= 2; max /= 2;
        }

        return cnt;
    }

    public int solution_other(int n, int a, int b){
        return Integer.toBinaryString((a-1)^(b-1)).length();
    }
}
class Main12985{
    public static void main(String[] args) {
        System.out.println(new 예상대진표_12985().solution(8, 4, 7));
        System.out.println(new 예상대진표_12985().solution(16, 1,  15));

        System.out.println(new 예상대진표_12985().solution_other(8, 4, 7));
        System.out.println(new 예상대진표_12985().solution_other(16, 1,  15));
    }
}