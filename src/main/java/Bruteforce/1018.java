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

        int[] temp ;

        // input으로 체스판 정보를 받으면서 원래 칠해져야 하는 색깔 기준으로 같으면 0, 다르면 1 로 세팅한 배열로 가공
        for (y = 0; y < yHeight; y++) {
            //편의상 white -> 0, black -> 1
            temp = Pattern.compile("").splitAsStream(br.readLine()).mapToInt(o -> o.equals("W") ? 0 : 1).toArray();
            for (x = 0; x < xWidth; x++) {
                whiteChess[y][x] = (temp[x] == (colorWhite++ % 2)) ? 0 : 1;
                blackChess[y][x] = (temp[x] == (colorBlack++ % 2)) ? 0 : 1; // 다르면 1
            }
            // start point color setting (가로판 크기에 영향 받음)
            colorWhite+=(xWidth + 1) % 2;
            colorBlack+=(xWidth + 1) % 2;
        }

        int whiteDiff, blackDiff; // 8 X 8 배열마다 하얀색으로 시작할 때, 검은색으로 시작할 때 별 차이 갯수 저장
        // 0,0 지점이 W로 시작하는 경우와 B로 시작하는 경우로 가정할 때 가장 차이가 적게 나는 경우
        int minDiffWhite = Integer.MAX_VALUE, minDiffBlack = Integer.MAX_VALUE;

        for (startY = 0; startY <= yHeight - 8; startY++) {
            for (startX = 0; startX <= xWidth - 8; startX++) {
                // after setting start point of 8X8 chess, reset difference
                whiteDiff = 0; blackDiff = 0;
                for (int yDist = 0; yDist < 8; yDist++) {
                    for (int xDist = 0; xDist < 8; xDist++) {
                        if (whiteChess[startY + yDist][startX + xDist] == 1) whiteDiff++;
                        if (blackChess[startY + yDist][startX + xDist] == 1) blackDiff++;
                    }
                }
                // set min value
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