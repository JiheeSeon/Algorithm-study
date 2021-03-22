package Backtrack.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main18428 {
    static boolean ifYes = false;
    static int N;
    static char[][] hallway;

    static int remainedObstacles = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hallway = new char[N][N]; // 선생님 T 학생 S 장애물 O
        System.out.println(Math.pow(6, 6));

        for (int i = 0; i < N; i++) {
            hallway[i] = br.readLine().replaceAll(" ", "").toCharArray();
        }

        backtrack(0);
        System.out.println(ifYes ? "YES" : "NO");
    }

    static void backtrack(int index) {
        int row = index / N;
        int col = index % N;

        for (int i = index; i < N * N; i++) {
            if(ifYes) break;
            if (hallway[row][col] != 'X') continue;

            boolean isTeacher = false;
            for (int k = 0; k < N; k++){
                if (hallway[row][k] == 'T')
                    isTeacher = true;
            }
            if(!isTeacher) {
                for (int k = 0; k < N; k++) {
                    if (hallway[k][col] == 'T')
                        isTeacher = true;
                }
            }
            if (!isTeacher) continue;

            hallway[row][col] = 'O';
            remainedObstacles--;

            if (remainedObstacles == 0){
                if(isValid()) ifYes = true;
                break;
            } else{
                backtrack(index + 1);
            }
            hallway[row][col] = 'X';
            remainedObstacles++;
        }
    }
    static boolean isValid(){
        boolean[][] isSafe = new boolean[N][N];
        int[] obstaclePos = new int[3];

        int k = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(hallway[i][j] == 'O')
                    obstaclePos[k++] = N * i + j;
            }
        }

        return true;
    }
    static boolean checkRowValidity(){
        for (int i = 0; i < N; i++){

        }
        return true;
    }
    static boolean checkColValidity(){
        for (int i = 0; i < N; i++){

        }
        return true;
    }
}