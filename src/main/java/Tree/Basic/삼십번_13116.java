package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 삼십번_13116 {
    int a, b;

    public 삼십번_13116(int a, int b) {
        this.a = Math.max(a, b);
        this.b = Math.min(a, b);
    }

    int solve() {
        int levelA = getLevel(a);
        int levelB = getLevel(b);

        // level 맞추기
        while (levelA > levelB) {
            a /= 2;
            levelA--;
        }

        // 동일한 level에서 시작해서 최대공통조상 구하기
        while (a != b) {
            a /= 2; b /= 2;
        }

        return 10 * a; // a == b인 상황
    }

    int getLevel(int val) {
        return (int)(Math.log10(val) / Math.log10(2)) + 1;
    }
}
class MainA13116{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] tmp;
        StringBuilder stb = new StringBuilder();
        while (--T >= 0) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            stb.append(new 삼십번_13116(tmp[0], tmp[1]).solve()).append("\n");
        }
        System.out.print(stb);
    }
}
