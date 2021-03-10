package Greedy.Acmicpc;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

/* Test case
10
ABB
BB
BB
BB
BB
BB
BB
BB
BB
BB
*/

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

//        Arrays.sort(enemies, Comparator.comparing((Enemy e) -> e.minusPower, reverseOrder()).thenComparing((Enemy e) -> e.order));
//        Arrays.sort(portions, Comparator.comparing((Portion p) -> p.plusPower, reverseOrder()).thenComparing((Portion p) -> p.order));
        Arrays.sort(portions, Comparator.comparing((Portion p) -> p.plusPower));

        int enemyIdx = 0, portionIdx = 0;

        if (validCheck <= 0) stb.append("0\n");
        else {
            while (enemyIdx < numOfEnemy) {
                if (initPower + enemies[enemyIdx].minusPower > 0) {
                    initPower += enemies[enemyIdx].minusPower;
                    stb.append("-").append(enemies[enemyIdx++].order).append("\n");
                } else if (portionIdx < portions.length) {
                    initPower += portions[portionIdx].plusPower;
                    stb.append(portions[portionIdx++].order).append("\n");
                } else {
                    stb.delete(0, stb.length());
                    stb.append("0");
                }
            }
            if (initPower <= 0) {
                stb.delete(0, stb.length());
                stb.append("0");
            } else if (portionIdx <= portions.length - 1) {
                while (portionIdx < portions.length) {
                    stb.append(portions[portionIdx++].order).append("\n");
                }
            }
        }

        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    private static class Enemy implements Comparable<Enemy> {
        int order, minusPower;

        public Enemy(int order, int minusPower) {
            this.order = order;
            this.minusPower = minusPower;
        }

        @Override
        public int compareTo(Enemy o) {
            return Comparator.comparing((Enemy e) -> e.minusPower)
                    .compare(this, o);
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