package Two_Pointer;

import java.io.*;

class Main17609{
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
        int result = 0;
        int start = 0; int end = s.length() - 1;

        while (true){
            if (start >= end) break;

            if (s.charAt(start) != s.charAt(end)) {
                if(result++ >= 1) return 2;

                if (start + 1 < s.length() && (s.charAt(start + 1) == s.charAt(end))) {
                    start++;
                }
                else if (end - 1 > start && (s.charAt(start) == s.charAt(end - 1))){
                    end--;
                }
                else return 2;
            }

            start++; end--;
        }

        return result;
    }
}