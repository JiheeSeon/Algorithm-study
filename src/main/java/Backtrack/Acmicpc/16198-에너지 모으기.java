package Backtrack.Acmicpc;

import java.io.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.regex.Pattern;

class Main16198{
    static int result = 0;
    static int N;
    static LinkedList<Marvel> marvelsOriginal;
    static LinkedList<Marvel> marvelsChanged;
    static int[] previous; // digit -> index
    static int localResult = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] temp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        marvelsOriginal = new LinkedList<>();

        for(int i = 0; i < temp.length; i++){ marvelsOriginal.add(new Marvel(i, temp[i]));}
        marvelsChanged = new LinkedList<>(marvelsOriginal);

//        weight = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).boxed().collect(Collectors.toCollection(LinkedList::new));
//        weight = (LinkedList<Integer>) Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        previous = new int[N - 2];

        backtrack(1);
        System.out.println(result);
    }
    static void backtrack(int currDigit){
//        int indexForNewList;
        for (int i = 1; i <= N - 2; i++){ // 1 2
            if (containsPreviousElement(i)) continue;
            System.out.println("i = " + i);

            displayMarvelChanged();

            int indexForNewList = marvelsChanged.indexOf(marvelsOriginal.get(i));
            System.out.println("indexForNewList = " + indexForNewList);

            System.out.println("marvel in original = " + marvelsOriginal.get(i));
            System.out.println("marvelsChanged.get(indexForNewList - 1) = " + marvelsChanged.get(indexForNewList - 1).weight);
            System.out.println("marvelsChanged.get(indexForNewList + 1) = " + marvelsChanged.get(indexForNewList + 1).weight);

            int collectedEnergy = marvelsChanged.get(indexForNewList - 1).weight * marvelsChanged.get(indexForNewList + 1).weight;
            localResult += collectedEnergy;

            Marvel marvelToRemove = marvelsChanged.remove(indexForNewList);
            previous[currDigit - 1] = marvelToRemove.originalIndex;

            System.out.println("current index = " + previous[currDigit - 1]);

            System.out.println("marvelToRemove = " + marvelToRemove);
            System.out.println("collectedEnergy = " + collectedEnergy);
            System.out.println("localResult = " + localResult);
            System.out.println();

            if (currDigit == N - 2){
                if (result < localResult) result = localResult;
            } else backtrack(currDigit + 1);

            previous[currDigit - 1] = 0;
            marvelsChanged.add(marvelToRemove.originalIndex, marvelToRemove);
            localResult -= collectedEnergy;
        }
    }
    static void displayMarvelChanged(){
        for(Marvel m : marvelsChanged)
            System.out.println(m);
        System.out.println();
    }

    static boolean containsPreviousElement(int n){
        for (int prev : previous) {
            System.out.println("prev = " + prev);
            if (prev == n) return true;
        }
        return false;
    }
    static private class Marvel{
        int originalIndex, weight;
        public Marvel(int originalIndex, int weight) {
            this.originalIndex = originalIndex;
            this.weight = weight;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Marvel marvel = (Marvel) o;
            return originalIndex == marvel.originalIndex && weight == marvel.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(originalIndex, weight);
        }

        @Override
        public String toString() {
            return "Marvel{" +
                    "originalIndex=" + originalIndex +
                    ", weight=" + weight +
                    '}';
        }
    }
}