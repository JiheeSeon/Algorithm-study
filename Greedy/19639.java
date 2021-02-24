import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

import static java.util.Comparator.reverseOrder;

class Main19639 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int numOfEnemy = input[0];
        int numOfRecoveryItem = input[1];
        int initPower = input[2];
        Enemy[] enemies = new Enemy[numOfEnemy];
        Portion[] portions = new Portion[numOfRecoveryItem];

        int i, validCheck = initPower;


        for (i = 0; i < numOfEnemy; i++) {
            enemies[i] = new Enemy(i + 1, -Integer.parseInt(br.readLine()));
            validCheck += enemies[i].minusPower;
        }

        for (i = 0; i < numOfRecoveryItem; i++) {
            portions[i] = new Portion(i + 1, Integer.parseInt(br.readLine()));
            validCheck += portions[i].plusPower;
        }


        int enemyIdx = 0, portionIdx = 0;
        int currPower = initPower;
        int res = 0;

        if (validCheck <= 0) stb.append("0\n");
        else {
            while(enemyIdx < numOfEnemy) {
//                System.out.println("enemyIdx = " + enemyIdx);
//                System.out.println("portionIdx = " + portionIdx);
//                System.out.println("currPower = " + currPower);
//                System.out.println("enemies[enemyIdx] = " + enemies[enemyIdx]);
//                System.out.println("expected Power for minus = " + (currPower + enemies[enemyIdx].minusPower));
//                if (portionIdx < portions.length)
//                    System.out.println("expected Power for plus = " + (currPower + portions[portionIdx].plusPower));
//                else
//                    System.out.println("no more portions");

                if (portionIdx < portions.length && (currPower + enemies[enemyIdx].minusPower <= 0 || currPower <= 0.5 * initPower)){
//                    System.out.println("=============portion=============");
                    currPower += portions[portionIdx].plusPower;
                    stb.append(portions[portionIdx++].order).append("\n");
                } else if(currPower + enemies[enemyIdx].minusPower < 0) {
//                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    res = -1;
                    break;
                } else {
//                    System.out.println("=============fight=============");
                    currPower += enemies[enemyIdx].minusPower;
                    stb.append("-").append(enemies[enemyIdx++].order).append("\n");
//                    System.out.println("enemies[enemyIdx] = " + enemies[enemyIdx - 1]);
                }
            }

            if (portionIdx != portions.length - 1){
                while(++portionIdx < portions.length){
                    stb.append(portions[portionIdx]).append("\n");
                }
            }
            if (res == -1){
                System.out.println("-1");
                stb.delete(0, stb.length());
                stb.append("0");
            }
        }

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    private static class Enemy {
        int order, minusPower;

        public Enemy(int order, int minusPower) {
            this.order = order;
            this.minusPower = minusPower;
        }
        @Override
        public String toString() {
            return "minusPower=" + minusPower;
        }
    }

    private static class Portion {
        int order, plusPower;

        public Portion(int order, int plusPower) {
            this.order = order;
            this.plusPower = plusPower;
        }
    }
}