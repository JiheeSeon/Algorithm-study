package Math.Programmers.Lv1;

class Main12921{
    public static void main(String[] args) {

    }
    static int solution(int n) {
        boolean flag = true;
        int result = 0;

        for(int i = 2; i <= n; i++){
            for(int j = 2; j * j <= n; j++) {
                if (i % j == 0){
                    flag = false;
                    break;
                }
            }
            if(flag) result++;
        }
        return result;
    }
}