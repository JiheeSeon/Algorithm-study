package Implementation.Programmers.lv1;

class Solution {
    public int solution(int num) {
        if(num == 1) return 0;

        double n = num;
        for(int i = 1; i <= 500; i++){
            if (n % 2 == 0)
                n /= 2;
            else
                n = n * 3 + 1;
            if (n == 1) return i;
        }
        return -1;
    }
}