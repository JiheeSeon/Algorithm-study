package Roadmap.Lv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 이진수_3460 {
    static String solve(int N) {
        return solve(N, 0, new StringBuilder()).toString();
    }

    static private StringBuilder solve(int val, int level, StringBuilder stb) {
        if(val == 0) return stb;

        if(val % 2 == 1) stb.append(level).append(" ");
        return solve(val / 2, level + 1, stb);
    }
}

class MainA3460{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();
        while (T-- > 0) {
            stb.append(이진수_3460.solve(Integer.parseInt(br.readLine()))).append("\n");
        }
        System.out.print(stb);
    }
}