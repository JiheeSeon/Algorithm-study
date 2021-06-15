package Backtrack.Acmicpc;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class 스도쿠_2239 {
    final static int SIZE = 9;
    int[][] sudoku;
    TreeSet<Integer>[] remainedYs = new TreeSet[SIZE];
    TreeSet<Integer>[] remainedXs = new TreeSet[SIZE];
    TreeSet<Integer>[] remainedByBlock = new TreeSet[SIZE];
    boolean[][] isOriginal;

    public 스도쿠_2239(int[][] sudoku){
        this.sudoku = sudoku;
        isOriginal = new boolean[SIZE][SIZE];

        // remainedYs
        for(int y = 0; y < SIZE; y++){
            remainedYs[y] = new TreeSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

            for(int x = 0; x < SIZE; x++){
                if(sudoku[y][x] != 0){
                    remainedYs[y].remove((Object)sudoku[y][x]);
                    isOriginal[y][x] = true;
                }
            }
        }
        // remainedXs
        for(int x = 0; x < SIZE; x++){
            remainedXs[x] = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            for(int y = 0; y < SIZE; y++){
                if(sudoku[y][x] != 0) remainedXs[x].remove((Object)sudoku[y][x]);
            }
        }

        // remainedByBlock
        int startY = 0; int startX;
        int blockIdx = 0;

        while(startY < SIZE) {
            startX = 0;
            while (startX < SIZE) { // start 기준을 옮기는 방식
                remainedByBlock[blockIdx] = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

                // block 내부 처리 -> 큰 start pointer를 설정하고나면 거기서 안의 세부값을 조정하는 방식
                for (int y = startY; y < startY + 3; y++) {
                    for (int x = startX; x < startX + 3; x++) {
                        remainedByBlock[blockIdx].remove((Object)sudoku[y][x]);
                    }
                }
                startX += 3;
                blockIdx++;
            }
            startY += 3;
        }
    }

    boolean backtrack(int nowYX){
        if(nowYX == 81) return true; // 가장 마지막 자리까지 도달한 경우

        int nowY = nowYX / SIZE;
        int nowX = nowYX % SIZE;

        // 원래 값인 경우에는 따로 값 세팅할 필요가 없음
        if(isOriginal[nowY][nowX]){
            return backtrack(nowYX + 1);
        }

        int blockIdx = 0;

        for(int val = 1; val <= 9; val++){
            // TreeSet에서 contains check -> O(1)
            if(!remainedYs[nowY].contains(val)) continue;
            if(!remainedXs[nowX].contains(val)) continue;
            blockIdx = nowY / 3 * 3 + nowX / 3;
            if(!remainedByBlock[blockIdx].contains(val)) continue;

            sudoku[nowY][nowX] = val;

            // 후보 리스트 현재 쓴 값을 제거
            remainedYs[nowY].remove((Object)val);
            remainedXs[nowX].remove((Object)val);
            remainedByBlock[blockIdx].remove((Object)val);

            if(backtrack(nowYX + 1)) return true; // 다 완성하면 여기서 끝내도록

            // 후보 리스트에 다시 추가
            remainedYs[nowY].add(val);
            remainedXs[nowX].add(val);
            remainedByBlock[blockIdx].add(val);
        }

        return false;
    }

    String getAns(){
        backtrack(0);

        StringBuilder stb = new StringBuilder();
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++)
                stb.append(sudoku[y][x]);
            stb.append("\n");
        }
        return stb.toString();
    }
}
class MainA2239 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] sudoku = new int[9][];
        for(int i = 0; i < 9; i++){
            sudoku[i] = strToIntArr(br.readLine());
        }

        System.out.println(new 스도쿠_2239(sudoku).getAns());
    }
    static int[] strToIntArr(String s){
        return Pattern.compile("").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}