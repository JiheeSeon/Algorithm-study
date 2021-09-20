package Implementation.Acmicpc;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
문제
로봇 청소기가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성하시오.

N -> yHeight (세로 길이)    M -> xWidth(가로 길이)
r -> y                     c -> x

로봇 청소기는 다음과 같이 작동한다.

1. 현재 위치를 청소한다.
2. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 인접한 칸을 탐색한다.
    a. 왼쪽 = -1 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
    b. 왼쪽 = -1 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다. //for 문 내
    c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
    d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
* 로봇 청소기는 이미 청소되어있는 칸을 또 청소하지 않으며, 벽을 통과할 수 없다.

문제 분석

int[][] visited = input으로 받아온 이중배열
int[][] directions = {{-1, 0}, {1, 0}, {1, 0}, {-1, 0}} // 북 0 -> 동 1 -> 남 2 -> 서 3

1. 현재 위치 청소 -> visited[y][x] = 2;
2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 탐색 -> 서쪽 = 4
    a.왼쪽 = 시계 반대방향
        - nextY = y + dy[currD - 1 < 0 ? currD - 1 + 4 : currD - 1]
        - nextX = x + dx[currD - 1 < 0 ? currD - 1 + 4 : currD - 1]
    b. c. d. 구조 파악
*/

class MainA14503{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmp = InputProcessor.strToIntArr(br.readLine());
        final int yHeight = tmp[0];
        final int xWidth = tmp[1];

        final int UNCLEANED = 0;
        final int WALL = 1;
        final int CLEANED = 2;

        final int CANNOT_CLEAN = 5;
        final int CANNOT_GO_TO_BACKWARD = 6;

        int[] yxd = InputProcessor.strToIntArr(br.readLine());
        int nowY = yxd[0]; int nowX = yxd[1]; int nowD = yxd[2];

        int[][] deltaD = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 0 -> 동 1 -> 남 2 -> 서 3

        int[][] visited = new int[yHeight][xWidth];
        for (int y = 0; y < yHeight; y++) visited[y] = InputProcessor.strToIntArr(br.readLine());


        final int DIRECTION = 0;
        final int REMAINED_LOOP = 1;
        int[][][] start = new int[yHeight][xWidth][2];
        for (int y = 0; y < yHeight; y++) {
            for (int x = 0; x < xWidth; x++) {
                if(visited[y][x] == 1) start[y][x] = new int[]{CANNOT_CLEAN, 0};
                else start[y][x] = new int[]{-1, 4};
            }
        }

        int cnt = 0; int loopN;
        int nextD, nextY, nextX;

        boolean ISCLEANABLE = true;

        while(true){
            // 2번
//            if(CLEANABLE && visited[nowY][nowX] == CLEANED) break;
            System.out.println("phase 1. NOW (" + nowD + ", " + nowY + ", "+ nowX + ")" + " -> " + Arrays.toString(start[nowY][nowX]) + ", " + (cnt + 1));
            if(start[nowY][nowX][DIRECTION] == CANNOT_GO_TO_BACKWARD) break;

            if(ISCLEANABLE && visited[nowY][nowX] == UNCLEANED){
                System.out.println("CLEANING (" + nowY + ", " + nowX + ") " + visited[nowY][nowX]);
                visited[nowY][nowX] = CLEANED;
                cnt++;
            }

            ISCLEANABLE = false;
            loopN = 0;

            if(start[nowY][nowX][DIRECTION] == -1) nextD = (nowD + 3) % 4;
            else nextD = start[nowY][nowX][DIRECTION];

            while(loopN++ < start[nowY][nowX][REMAINED_LOOP]){
                start[nowY][nowX][REMAINED_LOOP] -= 1;

                if(nextD > 4) continue;

                nextY = nowY + deltaD[nextD][0];
                nextX = nowX + deltaD[nextD][1];

                if(0 <= nextY && nextY < yHeight && 0 <= nextX && nextX < xWidth && visited[nextY][nextX] == UNCLEANED){
                    start[nowY][nowX][DIRECTION] = (nextD + 3) % 4;
                    System.out.println("phase 2. nowD = " + nowD + ", nowY = " + nowY + ", nowX = " + nowX
                            + " , start[" + nowY + "]["+ nowX + "]" +"["+ DIRECTION + "] >> " +  start[nowY][nowX][DIRECTION]
                            + " , start[" + nowY + "]["+ nowX + "]" +"["+ REMAINED_LOOP + "] >> " +  start[nowY][nowX][REMAINED_LOOP]  + "\n");

                    nowD = nextD; nowY = nextY; nowX = nextX;

                    ISCLEANABLE = true;
                    break;
                }

                nextD = (nextD + 3) % 4;
            }

            if (!ISCLEANABLE) {
                // 위에서 break가 안되었으면 == 어디든 청소 불가능했다는 의미 or 이미 청소 안된 걸 배열을 통해 확인한 경우
                if(start[nowY][nowX][DIRECTION] == CANNOT_GO_TO_BACKWARD) break;

                start[nowY][nowX][DIRECTION] = CANNOT_CLEAN;

                nextD = nowD < 2 ? (nowD + 2) % 4 : nowD - 2;
                nextY = nowY + deltaD[nextD][0];
                nextX = nowX + deltaD[nextD][1];

                if(nextY < 0 || nextX < 0 || nextY >= yHeight || nextX >= xWidth
                        || visited[nextY][nextX] == WALL || start[nextY][nextX][0] == CANNOT_GO_TO_BACKWARD){
                    start[nowY][nowX][0] = CANNOT_GO_TO_BACKWARD;
                    break;
                }

                nowY = nextY; nowX = nextX;
                System.out.println("phase 2.1. nextD = " + nowD + " nextY = " + nowY + " nextX = " + nowX);
                System.out.println();
            }
        }

        System.out.println(cnt);
    }
}

/*
11 10
7 4 0
0  1 1 1 1 1 1 1 1 1 1
1  1 0 0 0 0 0 0 0 0 1
2  1 0 0 0 1 1 1 1 0 1
3  1 0 0 1 1 0 0 0 0 1
4  1 0 1 1 0 0 0 0 0 1
5  1 0 0 0 0 0 0 0 0 1
6  1 0 0 0 0 0 0 1 0 1
7  1 0 0 0 0 0 1 1 0 1
8  1 0 0 0 0 0 1 1 0 1
9  1 0 0 0 0 0 0 0 0 1
10 1 1 1 1 1 1 1 1 1 1

0 1 2 3
북동남서

11 10
7 4 0
    0   1   2   3   4   5   6   7   8   9
0  [1] [1] [1] [1] [1] [1] [1] [1] [1] [1]
1  [1] [0] [0] [0] [0] [1] [1] [1] [0] [1]
2  [1] [0] [0] [1] [1] [1] [1] [1] [0] [1]
3  [1] [0] [0] [1] [1] [0] [0] [0] [0] [1]
4  [1] [0] [1] [1] [0] [0] [0] [0] [0] [1]
5  [1] [0] [0] (12)(11)[0] [0] [0] [0] [1]
6  [1] [0] (14)(13)(10)(9) [0] [1] [0] [1]
7  [1] (16)(15)(2) (1) (8) [1] [1] [0] [1]
8  [1] (17)(18)(3) (4) (7) [1] [1] [0] [1]
9  [1] [0](19)(20)(5) (6) [0] [0] [0] [1]
10 [1] [1] [1] [1] [1] [1] [1] [1] [1] [1]

왜 21이 아니지?
그리고 왜 계속 돌지? (무한 루프)

57
*/