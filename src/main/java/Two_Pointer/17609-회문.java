package Two_Pointer;

import java.io.*;

class Main17609{
    static int length;
    static String string;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            stb.append(solution(br.readLine())).append("\n");
        }
        System.out.println(stb);
    }
    static int solution(String s){
        length = s.length();
        string = s;

        if (isPalindrome()) return 0;
        else if (canBePalindrome(0, length - 1, true)) return 1;
        else return 2;
    }
    static boolean isPalindrome(){
        int left = 0, right = length - 1;

        while(left <= right){
            if (string.charAt(left++) != string.charAt(right--))
                return false;
        }
        return true;
    }
    static boolean canBePalindrome(int left, int right, boolean notSkippedYet){
        if (left > right) return true;

        if (string.charAt(left) == string.charAt(right)) return canBePalindrome(left + 1, right - 1, notSkippedYet);
        else if (notSkippedYet) return canBePalindrome(left + 1, right, false) || canBePalindrome(left, right - 1, false);
        else return false;
    }
}