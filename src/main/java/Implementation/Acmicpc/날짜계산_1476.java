package Implementation.Acmicpc;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 날짜계산_1476 {
    int goalE, goalS, goalM;
    int currentYear = 0;

    public 날짜계산_1476(int [] ESM) {
        goalE = ESM[0]; // 1 ≤ E ≤ 15
        goalS = ESM[1]; // 1 ≤ S ≤ 28
        goalM = ESM[2]; // 1 ≤ M ≤ 19
    }

    int solve() {
        int e = 0, s = 0, m = 0;

        while (true) {
            currentYear++;

            e = (e + 1  == 15) ? 15 : (e + 1) % 15;
            s = (s + 1  == 28) ? 28 : (s + 1) % 28;
            m = (m + 1  == 19) ? 19 : (m + 1) % 19;

            if (e == goalE && s == goalS && m == goalM) {
                return currentYear;
            }
        }
    }
}

class MainA1476{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(new 날짜계산_1476(InputProcessor.strToIntArr(br.readLine())).solve());
    }
}