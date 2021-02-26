package Bruteforce;

import java.io.*;
import java.util.regex.Pattern;

class Main1018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int yHeight = input[0];
        int xWidth = input[1];

        int[][] blackChess = new int[yHeight][xWidth];
        int[][] whiteChess = new int[yHeight][xWidth];

        int y, x;
        int colorWhite = 0, colorBlack = 1;
        int startY, startX;
        int minDiffWhite = Integer.MAX_VALUE, minDiffBlack = Integer.MAX_VALUE;

        int[] temp ;
        for (y = 0; y < yHeight; y++) {
            temp = Pattern.compile("").splitAsStream(br.readLine()).mapToInt(o -> o.equals("W") ? 0 : 1).toArray();
            for (x = 0; x < xWidth; x++) {
                whiteChess[y][x] = (temp[x] == (colorWhite++ % 2)) ? 0 : 1;
                blackChess[y][x] = (temp[x] == (colorBlack++ % 2)) ? 0 : 1; // 다르면 1
            }
            colorWhite+=(xWidth + 1) % 2;
            colorBlack+=(xWidth + 1) % 2;
        }

        int whiteDiff = 0, blackDiff = 0;
        for (startY = 0; startY <= yHeight - 8; startY++) {
            for (startX = 0; startX <= xWidth - 8; startX++) {
                whiteDiff = 0; blackDiff = 0;
                for (int yDist = 0; yDist < 8; yDist++) {
                    for (int xDist = 0; xDist < 8; xDist++) {
                        if (whiteChess[startY + yDist][startX + xDist] == 1) whiteDiff++;
                        if (blackChess[startY + yDist][startX + xDist] == 1) blackDiff++;
                    }
                }
                if (minDiffWhite > whiteDiff){
//                    System.out.println("WHITE > " + startY + ", "+ startX + " = " + whiteDiff);
                    minDiffWhite = whiteDiff;
//                    System.out.println(minDiffWhite);
                }
                if (minDiffBlack > blackDiff) {
//                    System.out.println("BLACK > " + startY + ", "+ startX+ " = " + blackDiff);
                    minDiffBlack = blackDiff;
                }
            }
        }

//        System.out.println(minDiffWhite);
//        for(int[] a : whiteChess){
//            for (int b : a)
//                System.out.print(b + " ");
//            System.out.println();
//        }
//
//        System.out.println(whiteDiff);
//
//        System.out.println("===========================");
//
//        for(int[] a : blackChess){
//            for (int b : a)
//                System.out.print(b + " ");
//            System.out.println();
//        }
//
//        System.out.println(blackDiff);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(Math.min(minDiffWhite, minDiffBlack)));
        bw.flush();
        bw.close();
    }
}