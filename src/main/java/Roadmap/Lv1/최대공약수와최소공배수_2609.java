package Roadmap.Lv1;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 최대공약수와최소공배수_2609 {
    static private int gcd(int a, int b){
        if(b == 0) return a;

        return gcd(b, a % b);
    }

    static String solve(int a, int b) {
        StringBuilder stb = new StringBuilder();
        int gcd = gcd(a, b);
        stb.append(gcd).append("\n");
        int lcm = a / gcd * b;
        stb.append(lcm);
        return stb.toString();
    }
}

class MainA2609{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = InputProcessor.strToIntArr(br.readLine());
        System.out.println(최대공약수와최소공배수_2609.solve(input[0], input[1]));
    }
}