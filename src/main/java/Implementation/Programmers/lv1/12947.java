package Implementation.Programmers.lv1;

class Main12947{
    public static void main(String[] args) {
        System.out.println(solution(13));
    }
    static boolean solution(int x) {
        int originalVal = x;
        int digitSum = 0;

        while(x > 0){
            digitSum += (x % 10);
            x /= 10;
        }
        return (originalVal % digitSum) == 0;
    }
}