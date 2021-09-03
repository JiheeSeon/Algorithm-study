package Roadmap.Lv2;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 빗물_14719 {
    int yHeight, xWidth;
    int[] stackedRain;

    public 빗물_14719(int yHeight, int xWidth, int[] stackedRain) {
        this.yHeight = yHeight;
        this.xWidth = xWidth;
        this.stackedRain = stackedRain;
    }

    int solve() {
        int idx = -1;
        int previous = 0;
        int firstBlockIdx = -1, secondBlockIdx = -1;
        int standardHeight;
        int ret = 0;

        while(++idx < xWidth){
            if(previous > stackedRain[idx]) {
                firstBlockIdx = idx - 1; break;
            }
            previous = stackedRain[idx];
        }
        idx--;

        if(firstBlockIdx == -1) return 0;

        while (idx < xWidth) {
            previous = 0;

            // 연속적으로 올라갈 수 있을만큼 계속 올라간다
            while (++idx < xWidth && previous <= stackedRain[idx]) {
                previous = stackedRain[idx];
            }
            secondBlockIdx = --idx;

            if(firstBlockIdx == secondBlockIdx) return ret;

            standardHeight = Math.min(stackedRain[firstBlockIdx], stackedRain[secondBlockIdx]);

            for (int i = firstBlockIdx; i < secondBlockIdx; i++) {
                if(standardHeight > stackedRain[i])
                    ret += (standardHeight - stackedRain[i]);
            }

            firstBlockIdx = secondBlockIdx;
            secondBlockIdx = -1;
        }

        return ret;
    }
}
class MainA14719{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        int yHeight = tmp[0]; int xWidth = tmp[1];
        tmp = InputProcessor.strToIntArr(br.readLine());
        System.out.println(new 빗물_14719(yHeight, xWidth, tmp).solve());
    }
}

/*
4 8
3 2 1 2 1 0 3 2
출력
2
답
9
*/