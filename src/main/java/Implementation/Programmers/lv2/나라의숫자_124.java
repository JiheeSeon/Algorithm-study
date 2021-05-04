package Implementation.Programmers.lv2;

class 나라의숫자_124 {
}
class Solution12899 {
    public String solution(int n) {
        return convert124(n, new StringBuilder());
    }

    public String convert124(int n, StringBuilder stb){
        if(1 <= n && n <= 3){
            if(n == 3) stb.append(4);
            else stb.append(n);
            return stb.toString();
        }

        if(n % 3 == 0){
            convert124(n - 1, stb);
            stb.setLength(stb.length() - 1);
            stb.append(4);
        }
        else{
            convert124(n / 3, stb);
            stb.append(n % 3);
        }

        return stb.toString();
    }
}