package Math.Programmers.Lv1;

class Main12928{
    public int solution(int n) {
        int sum = 0;
        for(int i = 1; i*i <= n; i++){
            if (n % i == 0){
                sum = sum + ((i == n/i) ? i: i + n/i);
            }
        }
        return sum;
    }
}