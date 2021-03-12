package Math.Programmers.Lv1;

class Main12934{
    static long solution(int n){
       return Math.sqrt(n) == (double)((long)(Math.sqrt(n))) ? (long)((Math.sqrt(n)) + 1) * (long)((Math.sqrt(n)) + 1): -1;
    }
    public static void main(String[] args) {
        System.out.println(solution(395641));
    }
}