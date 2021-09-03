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

        while (++idx < xWidth) {
            previous = 0;

            while (++idx < xWidth && stackedRain[idx] != 0 && previous <= stackedRain[idx]) {
                previous = stackedRain[idx];
            }
            secondBlockIdx = idx - 1;
            standardHeight = Math.min(stackedRain[firstBlockIdx], stackedRain[secondBlockIdx]);

            for (int i = firstBlockIdx; i < secondBlockIdx; i++) {
                if(standardHeight > stackedRain[i])
                    ret += (standardHeight - stackedRain[i]);
            }

            firstBlockIdx = secondBlockIdx;
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