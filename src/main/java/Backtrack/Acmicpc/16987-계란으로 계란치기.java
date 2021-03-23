package Backtrack.Acmicpc;

import java.io.*;
import java.util.LinkedList;
import java.util.regex.Pattern;

class Main16987{
    static int eggN;
    static Egg[] eggs;

    static boolean[] isBroken;
    static boolean flagToBreak = false;

    static int result = 0;
    static int localResult = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        eggN = Integer.parseInt(br.readLine());

        eggs = new Egg[eggN];
        isBroken = new boolean[eggN];

        int [] temp;
        for (int i = 0; i < eggN ; i++){
            temp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
            eggs[i] = new Egg(temp[0], temp[1]);
        }

        backtrack(0);

        System.out.println(result);
    }
    static void backtrack(int currIndex){
        Egg eggCurrent, eggToBroke;

        System.out.println("currIndex = " + currIndex);
        System.out.println();
        for (int toBroke = 0; toBroke < eggN; toBroke++){
            if(flagToBreak) break;
//            if (currIndex != (eggN - 1)) continue;

            System.out.println("currIndex = " + currIndex);
            System.out.println("toBroke = " + toBroke);
            System.out.println("localResult = " + localResult);


            eggCurrent = eggs[currIndex];
            eggToBroke = eggs[toBroke];

            if(!isBroken[currIndex] && !isBroken[toBroke]) {
                eggCurrent.updateDurability(-eggToBroke.weight);
                eggToBroke.updateDurability(-eggCurrent.weight);

                System.out.println("eggCurrent = " + eggCurrent.durability);
                System.out.println("eggToBroke = " + eggToBroke.durability);

                if (eggCurrent.durability < 0) {
                    isBroken[currIndex] = true;
                    localResult++;
                }
                if (eggToBroke.durability < 0) {
                    isBroken[toBroke] = true;
                    localResult++;
                }
                System.out.println("localResult = " + localResult);
                System.out.println();
            }

            System.out.println("currIndex = " + currIndex);
            System.out.println("eggN-1 = " + (eggN - 1));

            if (currIndex == eggN - 2){
                System.out.println("===========last============");
                result = Math.max(localResult, result);
                System.out.println();
            } else backtrack(currIndex + 1);

            if(!isBroken[currIndex] && !isBroken[toBroke]) {
                if (eggCurrent.durability < 0){
                    isBroken[currIndex] = false;
                    localResult--;
                }
                if (eggToBroke.durability < 0) {
                    isBroken[toBroke] = false;
                    localResult--;
                }
                eggCurrent.updateDurability(eggToBroke.weight);
                eggToBroke.updateDurability(eggCurrent.weight);
            }
        }
    }
    static private class Egg{
        int durability, weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }

        void updateDurability(int othersDurability){
            durability += othersDurability;
        }

        @Override
        public String toString() {
            return "Egg{" +
                    "weight=" + weight +
                    ", durability=" + durability +
                    '}';
        }
    }
}