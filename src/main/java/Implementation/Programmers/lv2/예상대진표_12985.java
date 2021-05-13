package Implementation.Programmers.lv2;

import java.util.*;

class 예상대진표_12985 {
    public int solution(int n, int a, int b){
        int min = n - 1 + Math.min(a, b);
        int max = n - 1 + Math.max(a, b);

        ArrayList<Integer> aPath = new ArrayList<>();
        while(min != 0){
            aPath.add(min);
            min /= 2;
        }

        ArrayList<Integer> bPath = new ArrayList<>();
        while(max != 0){
            bPath.add(max);
            max /= 2;
        }

        int currIdx = aPath.size();

        while(--currIdx >= 0)
            if(!aPath.get(currIdx).equals(bPath.get(currIdx))) break;

        return currIdx + 1;
    }
}
class Main12985{
    public static void main(String[] args) {
        System.out.println(new 예상대진표_12985().solution(8, 4, 7));
        System.out.println(new 예상대진표_12985().solution(16, 1,  15));

    }
}