package Backtrack.Acmicpc;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/*
19236 청소년 상어
backtracking - 푸는 데 엄청 오래 걸림

1. for문 안팎으로 backtrack, backup 세팅
   moveFish 이전 상황으로 되돌려야 하는 것 간과.
   moveFish의 영향을 받는 vector, fishLocation 모두 되돌려야 함.
2. moveFish 로직
   swap에서 index 헷갈리지 않게 주의했어야 함.
   기존의 direction을 읽어오는 게 아니라 바뀌는 direction을 반영해서 업데이트
3. backtrack에서 이번에 선택할 걸 고르는 것에 반해 얘는 다음걸 고르는 방식
*/

class 청소년상어_19236 {
    final static int yHeight = 4;
    final static int xWidth = 8;

    final static int Y = 0;
    final static int X = 1;
    final static int D = 2;

    final static int BLANK = -1;
    final static int directionN = 8;

    // 북쪽부터 반시계 45도 회전시켰을 때
    final static int[] dy = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    final static int[] dx = new int[]{0, -1, -1, -1, 0, 1, 1, 1};

    int[][] vector; // input으로 받은 물고기의 초기 vector 배열

    /*
    i : fish number j : Y/X/D
    해당 물고기가 잡아먹히면 null로 체크
    moveFish로 위치 또는 vector가 바뀌면 반영
    */
    int[][] fishLocationAfterMove = new int[17][3];

    int firstEatenFish, firstFishDirection;

    public 청소년상어_19236(int[][] vector) {
        // after first shark comes
        this.vector = new int[yHeight][xWidth];
        for (int y = 0; y < yHeight; y++) {
            for (int x = 0; x < xWidth; x += 2) {
                this.vector[y][x] = vector[y][x];
                this.vector[y][x + 1] = vector[y][x + 1] - 1; // 0 시작으로 맞춤
                fishLocationAfterMove[this.vector[y][x]] = new int[]{y, x, this.vector[y][x + 1]};
            }
        }

        // get first shark info
        firstEatenFish = this.vector[0][0];
        firstFishDirection = this.vector[0][1];
    }

    int getAns() {
        // shark eats fish
        vector[0][0] = BLANK;
        vector[0][1] = BLANK;
        fishLocationAfterMove[firstEatenFish] = null;
        return moveShark(0, 0, 0, firstFishDirection, firstEatenFish);
    }

    int moveShark(int depth, int sharkY, int sharkX, int sharkDir, int acc) {
        // 잡아먹을 물고기 후보 관련 변수
        int candidateFishNo;
        int candidateFishD;
        int[] candidateFishInfo;

        // for backup
        int[][] vectorBack = new int[yHeight][xWidth];
        int[][] fishLocationBeforeMove = new int[17][3];

        // 물고기 이동하기 전에 원상복구할 수 있도록 백업
        for (int y = 0; y < yHeight; y++)
            vectorBack[y] = vector[y].clone();

        for (int f = 1; f <= 16; f++)
            fishLocationBeforeMove[f] = (fishLocationAfterMove[f] == null) ? null : fishLocationAfterMove[f].clone();

        // 물고기 이동
        moveFish(sharkY, sharkX);

        // 상어가 잡아먹을 수 있는 모든 물고기에 대한 simulation
        int nextSharkY, nextSharkX;
        int max = acc;

        for (int i = 1; i <= 3; i++) {
            nextSharkY = sharkY + dy[sharkDir] * i;
            nextSharkX = sharkX + dx[sharkDir] * 2 * i;

            if (nextSharkY < 0 || nextSharkX < 0 || nextSharkY >= yHeight || nextSharkX >= xWidth) break;
            if (vector[nextSharkY][nextSharkX] == BLANK) continue; // 이미 비어있는 자리로는 갈 수 없음.

            // backup
            candidateFishNo = vector[nextSharkY][nextSharkX];
            candidateFishD = vector[nextSharkY][nextSharkX + 1];
            candidateFishInfo = fishLocationAfterMove[candidateFishNo];

            // eat fish on the location
            fishLocationAfterMove[candidateFishNo] = null;
            vector[nextSharkY][nextSharkX] = BLANK;
            vector[nextSharkY][nextSharkX + 1] = BLANK;

            max = Math.max(max, moveShark(depth + 1, nextSharkY, nextSharkX, candidateFishD, acc + candidateFishNo));

            fishLocationAfterMove[candidateFishNo] = candidateFishInfo;
            vector[nextSharkY][nextSharkX] = candidateFishNo;
            vector[nextSharkY][nextSharkX + 1] = candidateFishD;
        }

        // 물고기 원위치 - 백업해놓은 배열 비용
        for (int y = 0; y < yHeight; y++)
            vector[y] = vectorBack[y].clone();

        for (int f = 1; f <= 16; f++)
            fishLocationAfterMove[f] = fishLocationBeforeMove[f] == null ? null : fishLocationBeforeMove[f].clone();

        return max;
    }

    void moveFish(int sharkY, int sharkX) {
        int toSwapY, toSwapX;

        int tmpNo, tmpD;
        int[] tmpArr;

        int swapFishNo, swapFishD;
        int[] swapFishArr;

        int nowD;

        for (int fishNo = 1; fishNo <= 16; fishNo++) {
            if (fishLocationAfterMove[fishNo] == null) continue;

            for (int dir = 0; dir < directionN; dir++) {
                nowD = (fishLocationAfterMove[fishNo][D] + dir) % directionN;
                toSwapY = fishLocationAfterMove[fishNo][Y] + dy[nowD];
                toSwapX = fishLocationAfterMove[fishNo][X] + 2 * (dx[nowD]);

                if (toSwapY < 0 || toSwapX < 0 || toSwapY >= yHeight || toSwapX >= xWidth || (toSwapY == sharkY && toSwapX == sharkX))
                    continue;

                // swap info -> for print
                swapFishNo = vector[toSwapY][toSwapX];
                swapFishD = vector[toSwapY][toSwapX + 1];

                // just get into it
                if (swapFishNo == BLANK) {
                    // 물고기가 이동할 자리에 현재 물고기의 정보를 담음
                    vector[toSwapY][toSwapX] = fishNo;
                    vector[toSwapY][toSwapX + 1] = nowD;

                    // 물고기가 원래 있던 자리는 비게 됨
                    vector[fishLocationAfterMove[fishNo][Y]][fishLocationAfterMove[fishNo][X]] = BLANK;
                    vector[fishLocationAfterMove[fishNo][Y]][fishLocationAfterMove[fishNo][X] + 1] = BLANK;

                    // 현재 물고기가 이동한 곳에 대한 정보 업데이트
                    fishLocationAfterMove[fishNo] = new int[]{toSwapY, toSwapX, nowD};
                } else { // swap
                    // 현재 물고기의 정보를 tmp에 담음
                    tmpNo = vector[fishLocationAfterMove[fishNo][Y]][fishLocationAfterMove[fishNo][X]];
                    tmpD = nowD;
                    tmpArr = fishLocationAfterMove[fishNo];

                    // 현재 물고기가 있는 곳에 먼저 스왑할 자리에 있는 물고기의 정보를 받아옴
                    vector[fishLocationAfterMove[fishNo][Y]][fishLocationAfterMove[fishNo][X]] = swapFishNo;
                    vector[fishLocationAfterMove[fishNo][Y]][fishLocationAfterMove[fishNo][X] + 1] = swapFishD;
                    swapFishArr = fishLocationAfterMove[swapFishNo];

                    // tmp에 옮겨놓았던 현재 물고기의 정보를 스왑할 자리에 넣어둠
                    vector[fishLocationAfterMove[swapFishNo][Y]][fishLocationAfterMove[swapFishNo][X]] = tmpNo;
                    vector[fishLocationAfterMove[swapFishNo][Y]][fishLocationAfterMove[swapFishNo][X] + 1] = tmpD;

                    // 위치는 바뀌되 방향은 그대로
                    fishLocationAfterMove[fishNo] = new int[]{swapFishArr[Y], swapFishArr[X], nowD};
                    fishLocationAfterMove[swapFishNo] = new int[]{tmpArr[Y], tmpArr[X], swapFishArr[D]};
                }

                break; // 여기까지 도달했다는 것은 한번 스왑했다는 것 -> 이동 중지
            }
        }
    }

    void displayVector() {
        for (int y = 0; y < yHeight; y++) {
            for (int x = 0; x < xWidth; x += 2)
                System.out.print(vector[y][x] + "(" + vector[y][x + 1] + ") ");
            System.out.println();
        }
        System.out.println();
    }

    void displayFishLocation() {
        int idx = -1;
        for (int[] i : fishLocationAfterMove) {
            if (++idx == 0) continue;
            System.out.println(idx + " > " + Arrays.toString(i));
        }
        System.out.println();
    }
}

class MainA19236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] vect = new int[4][8];
        for (int y = 0; y < 4; y++)
            vect[y] = strToIntArr(br.readLine());

        System.out.println(new 청소년상어_19236(vect).getAns());
    }

    static int[] strToIntArr(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}