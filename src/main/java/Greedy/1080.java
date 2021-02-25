package Greedy;

import java.io.*;
import java.util.regex.Pattern;

class Main1080{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] diffMatrix;

    public static void main(String[] args) throws IOException {
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int [] input = processInput(" ", 2);
        int [] temp;
        int yHeight = input[0]; int xWidth = input[1];

        int[][] matrixA = new int[yHeight][xWidth];
        diffMatrix = new boolean[yHeight][xWidth];

        int y, x;
        int operationN = 0;

        for(y = 0; y < yHeight; y++)
            matrixA[y] = processInput("", xWidth);

        for(y = 0; y < yHeight; y++){
            temp = processInput("", xWidth);
            for (x = 0; x < xWidth; x++) {
                diffMatrix[y][x] = matrixA[y][x] == temp[x]; // same -> true
            }
        }

        if (yHeight >= 3 && xWidth >= 3) {
            for (y = 0; y < yHeight - 2; y++) {
                for (x = 0; x < xWidth - 2; x++) {
                    if (!diffMatrix[y][x]) {
                        reverse(y, x); // different -> reverse
                        operationN++;
                    }
                    if (y == yHeight - 3
                            && (!(diffMatrix[yHeight - 2][x] && diffMatrix[yHeight - 1][x]))){
                        operationN = -1;
                        break;
                    }
                }

                if (!(diffMatrix[y][xWidth - 2] && diffMatrix[y][xWidth - 1])) {
                    operationN = -1;
                    break;
                }
            }
        }

        if (operationN != -1) {
            Loop1:
            for (y = 0; y < yHeight; y++) {
                for (x = 0; x < xWidth; x++) {
                    if (!diffMatrix[y][x]) {
                        operationN = -1;
                        break Loop1;
                    }
                }
            }
        }

        bw.write(Integer.toString(operationN));
        bw.flush();
        bw.close();
    }

    static int[] processInput(String delimiter, int limitSize) throws IOException{
        return Pattern.compile(delimiter).splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(limitSize).toArray();

    }

    static void reverse(int startY, int startX){
        for (int i = startY; i < startY + 3; i++){
            for (int j = startX; j < startX + 3; j++){
                diffMatrix[i][j] = !diffMatrix[i][j];
            }
        }
    }

//    static void display2DArray(boolean[][] arr){
//        for (boolean i[] : arr){
//            for (boolean j : i){
//                System.out.print(j + " ");
//            }
//            System.out.println();
//        }
//    }
}