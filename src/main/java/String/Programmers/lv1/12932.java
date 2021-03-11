package String.Programmers.lv1;

class Main12932{
    public int[] solution(long n) {
        StringBuilder stb = new StringBuilder();
        stb.append(n);

        int[] ret = new int[stb.length()];
        for(int i = ret.length - 1; i >= 0; i--)
            ret[i] = Integer.parseInt(String.valueOf(stb.charAt(ret.length - i - 1)));
        return ret;
    }

    public int[] otherSolution(long n){
        int len = (""+n).length();
        int[] ret = new int[len];
        int idx = 0;

        while(idx < len){
            ret[idx++] = (int)(n % 10);
            n /= 10;
        }
        return ret;
    }
}