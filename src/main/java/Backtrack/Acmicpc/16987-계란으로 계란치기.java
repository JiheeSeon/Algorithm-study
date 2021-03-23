package Backtrack.Acmicpc;

import java.io.*;
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
    static void backtrack(int attackingIdx){
        Egg attackingEgg, attackedEgg;

        for (int attackedIdx = 0; attackedIdx < eggN; attackedIdx++){
            System.out.println();
            System.out.println("enter " + attackingIdx);
            System.out.println("attack " + attackedIdx);
            if (flagToBreak) break;

            for(boolean b : isBroken) System.out.print(b + " ");
            System.out.println();

            if(isAllBroken()){
                result = eggN;
                flagToBreak = true;
                break;
            }

            if (attackingIdx == attackedIdx && attackingIdx != eggN - 1) {
                continue;
            }
            if (isBroken[attackedIdx]) continue;
            if (isBroken[attackingIdx]){
                backtrack(attackingIdx + 1);
                flagToBreak = true;
                continue;
            }

            System.out.println("attackingIdx = " + attackingIdx + " => attackedIdx = " + attackedIdx);

            for(boolean b : isBroken) System.out.print(b +" ");
            System.out.println();

            attackingEgg = eggs[attackingIdx];
            attackedEgg = eggs[attackedIdx];

            if (!(attackingIdx == attackedIdx &&attackingIdx == eggN - 1)) {
                attackingEgg.updateDurability(-attackedEgg.weight);
                attackedEgg.updateDurability(-attackingEgg.weight);

                if (attackingEgg.durability <= 0) {
                    isBroken[attackingIdx] = true;
                    localResult++;
                }
                if (attackedEgg.durability <= 0) {
                    isBroken[attackedIdx] = true;
                    localResult++;
                }
            }

            if(attackingIdx >= eggN - 1){
                result += Math.max(result, localResult);
                break;
            } else backtrack(attackingIdx + 1);

            if (!(attackingIdx == attackedIdx &&attackingIdx == eggN - 1)) {
                attackingEgg.updateDurability(attackedEgg.weight);
                attackedEgg.updateDurability(attackingEgg.weight);

                if (attackingEgg.durability > 0) {
                    isBroken[attackingIdx] = false;
                    localResult--;
                }
                if (attackedEgg.durability > 0) {
                    isBroken[attackedIdx] = false;
                    localResult--;
                }
            }
        }
    }
    static boolean isAllBroken(){
        for(boolean b : isBroken){
            if (!b) return false;
        }
        return true;
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