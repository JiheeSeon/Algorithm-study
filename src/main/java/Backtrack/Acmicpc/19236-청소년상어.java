package Backtrack.Acmicpc;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/*
19236 청소년 상어
backtracking
*/
class 청소년상어_19236 {
    final static int Y = 0;
    final static int X = 1;
    final static int D = 2;

    int[][] vect;
    int firstEatenFish, sharkD;

    final static int BLANK = -1;

    // 동쪽부터 반시계 45도 회전시켰을 때
    int[] dy = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    int[] dx = new int[]{0, -1, -1, -1, 0, 1, 1, 1};
    int[] sign = {-1, 1};

    int[][] fishLocation = new int[17][3];

    public 청소년상어_19236(int[][] vect){
        // after first shark comes
        this.vect = new int[vect.length][vect[0].length];
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < 8; x += 2){
                fishLocation[vect[y][x]] = new int[]{y, x, vect[y][x + 1] - 1};
                this.vect[y][x] = vect[y][x];
                this.vect[y][x + 1] = vect[y][x + 1] - 1;
            }
        }

        // get shark info
        firstEatenFish = this.vect[0][0];
        sharkD = this.vect[0][1];
    }

    int getAns(){
        // shark eats fish
        vect[0][0] = BLANK;
        vect[0][1] = BLANK;
        fishLocation[firstEatenFish] = null;
        return moveShark(0, 0, 0, sharkD, 0);
    }

    int moveShark(int depth, int sharkY, int sharkX, int sharkDir, int cnt){
        if(depth != 0 && vect[sharkY][sharkX] == BLANK){
            return cnt;
        }

        int candidateFishNo;
        int candidateFishD;
        int[] candidateFishInfo;

        //move fish
        moveFish(sharkY, sharkX);
//        System.out.println("sharkY = " + sharkY + " sharkX = " + sharkX  + "(" + depth + ")");
//        displayVect();

        int nextSharkY, nextSharkX;
        int max = cnt + 1;

        int idx = 2;
        while(--idx >= 0){
            for (int i = 1; i <= 3; i++) {
                nextSharkY = sharkY + dy[sharkDir] * sign[idx] * i;
                nextSharkX = sharkX + dx[sharkDir] * sign[idx] * 2 * i;

                if(nextSharkY < 0 || nextSharkX < 0 || nextSharkY >= 4 || nextSharkX >= 8) break;
                if(vect[nextSharkY][nextSharkX] == BLANK) continue;

                candidateFishNo = vect[nextSharkY][nextSharkX];
                candidateFishD = vect[nextSharkY][nextSharkX + 1];
                candidateFishInfo = fishLocation[candidateFishNo];

//                System.out.println("depth = " + depth + " candidateFishNo = " + candidateFishNo);

                fishLocation[candidateFishNo] = null;
                vect[nextSharkY][nextSharkX] = BLANK;
                vect[nextSharkY][nextSharkX + 1] = BLANK;

                max = Math.max(max, moveShark(depth + 1, nextSharkY, nextSharkX, sharkDir, cnt + 1));

                fishLocation[candidateFishNo] = candidateFishInfo;
                vect[nextSharkY][nextSharkX] = candidateFishNo;
                vect[nextSharkY][nextSharkX + 1] = candidateFishD;
            }
        }

        return max;
    }
    void moveFish(int sharkY, int sharkX){
        int toSwapY, toSwapX;
        int tmpNo, tmpD; int[] tmpArr;
        int swapFishNo, swapFishD; int[] swapFishArr;

        for(int fishNo = 1; fishNo <= 16; fishNo++) {
            if (fishLocation[fishNo] == null) continue;
            System.out.println();
            System.out.println("fishNo = " + fishNo);
            System.out.print("fishLocation[Y] = " + fishLocation[fishNo][Y]);
            System.out.println(" fishLocation[X] = " + fishLocation[fishNo][X]);
//            displayVect();

            for (int dir = 0; dir < 8; dir++) {
                toSwapY = fishLocation[fishNo][Y] + dy[(fishLocation[fishNo][D] + dir) % 8];
                toSwapX = fishLocation[fishNo][X] + 2 * dx[(fishLocation[fishNo][D] + dir) % 8];

                System.out.println("toSwapY = " + toSwapY + " toSwapX = " + toSwapX);
                if (toSwapY < 0 || toSwapX < 0 || toSwapY >= 4 || toSwapX >= 8 || (toSwapY == sharkY && toSwapX == sharkX)) continue;

                // swap info -> for print
                swapFishNo = vect[toSwapY][toSwapX];
                swapFishD = vect[toSwapY][toSwapX + 1];
//                System.out.println("swapFishNo = " + swapFishNo + " swapFishD = " + swapFishD);

                // just get into it
                if(swapFishNo == BLANK){
                    vect[toSwapY][toSwapX] = fishNo;
                    vect[toSwapY][toSwapX + 1] = fishLocation[fishNo][D];

                    vect[fishLocation[fishNo][Y]][fishLocation[fishNo][X]] = BLANK;
                    vect[fishLocation[fishNo][Y]][fishLocation[fishNo][X] + 1] = BLANK;
                    fishLocation[fishNo] = null;
                    fishLocation[fishNo] = null;
                } else { // swap
                    // original -> tmp
                    swapFishArr = fishLocation[swapFishNo];
                    tmpNo = vect[fishLocation[fishNo][Y]][fishLocation[fishNo][X]];
                    tmpD = vect[fishLocation[fishNo][Y]][fishLocation[fishNo][X] + 1];
                    tmpArr = fishLocation[fishNo];

                    // swapped to original
                    vect[fishLocation[fishNo][Y]][fishLocation[fishNo][X]] = swapFishNo;
                    vect[fishLocation[fishNo][Y]][fishLocation[fishNo][X] + 1] = swapFishD;

                    // original to swapped
                    vect[fishLocation[swapFishNo][Y]][fishLocation[swapFishNo][X]] = tmpNo;
                    vect[fishLocation[swapFishNo][Y]][fishLocation[swapFishNo][X] + 1] = tmpD;

                    fishLocation[fishNo] = swapFishArr;
                    fishLocation[swapFishNo] = tmpArr;
                }

                break;
            }

            displayVect();
        }
    }
    void displayVect() {
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < 8; x+=2)
                System.out.print(vect[y][x] + "("+vect[y][x+1]+") ");
            System.out.println();
        }
        System.out.println();
    }

    void displayFishLocation() {
        int idx = -1;
        for(int[] i : fishLocation){
            if(++idx == 0) continue;
            System.out.println(idx + " > " + Arrays.toString(i));
        }
        System.out.println();
    }
}

class MainA19236 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] vect = new int[4][8];
        for(int i = 0; i < 4; i++)
            vect[i] = strToIntArr(br.readLine());

        System.out.println(new 청소년상어_19236(vect).getAns());
    }

    static int[] strToIntArr(String s){
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}