package Math.Programmers.Lv1;

class Main68935{
    static StringBuilder stb = new StringBuilder();
    public int solution(int n) {
        recursive(n);
        String s = stb.reverse().toString().replaceAll("^0+", "");
        return Integer.parseInt(s, 3);
    }
    void recursive(int n){
        if(n == 0) return;
        recursive(n / 3);
        stb.append(n % 3);
    }
}