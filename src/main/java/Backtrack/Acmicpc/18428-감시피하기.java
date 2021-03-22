package Backtrack.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

class Main18428 {
    static boolean ifYes = false;
    static int N;
    static char[][] hallway;

    static Set<Integer> candidateSet = new TreeSet<>();
    static LinkedList<Integer> candidateList;
    static int[] previous;

    static boolean[] isSafeY;
    static boolean[] isSafeX;

    static boolean breakFlag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hallway = new char[N][N]; // 선생님 T 학생 S 장애물 O
        previous = new int[3];

        for (int i = 0; i < N; i++) {
            hallway[i] = br.readLine().replaceAll(" ", "").toCharArray();
        }

        setCandidatesByRow();
        setCandidatesByColumn();
        displayCandidates();
        candidateList = new LinkedList<>(candidateSet);

        backtrack(1);
        System.out.println(ifYes ? "YES" : "NO");
    }

    static void backtrack(int currentResourceNumber) {
        for (int i = currentResourceNumber == 1
                ? 0
                : previous[currentResourceNumber - 2] + 1
             ; i < candidateList.size(); i++) {

            if (breakFlag) break;
            System.out.println("i = " + i);

            previous[currentResourceNumber - 1] = i;
            int realValue = candidateList.get(i);
            System.out.println("realValue/5 = " + (realValue / 5));
            System.out.println("realValue%5 = " + (realValue % 5));
            hallway[realValue / N][realValue % N] = 'O';

            if (currentResourceNumber == 3){
                // 검사해서 괜춘하면 flag yes
                if(isBlockedWell()) {
                    ifYes = true;
                    breakFlag = true;
                    break;
                }
            } else{
                backtrack(currentResourceNumber+1);
            }
            previous[currentResourceNumber - 1] = -1;
            hallway[realValue / N][realValue % N] = 'X';
        }
    }

    static void displayCandidates(){
        for(int i : candidateSet) System.out.print(i + " ");
        System.out.println();
    }

    static boolean isBlockedWell(){
        isSafeY = new boolean[N];
        isSafeX = new boolean[N];

        checkBlockedByColumn();
        checkBlockedByRow();

        for(boolean b : isSafeY){
            if(!b) return false;
        }

        for(boolean b : isSafeX){
            if(!b) return false;
        }
        return true;
    }

    static void checkBlockedByColumn(){
        String yInfo;

        for(int y = 0; y < N; y++) {
            yInfo = new String(hallway[y]);
            if ((!yInfo.contains("T")
                    || (yInfo.contains("T") && !yInfo.contains("S")))
                    || (!yInfo.matches("[XOTS]*S[XST]+T[XOTS]*"))
                    && (!yInfo.matches("[XOTS]*T[XST]+S[XOTS]*"))){
                System.out.println(yInfo);
                System.out.println("safe for this row");
                isSafeX[y] = true;
            }
        }
    }
    static void checkBlockedByRow(){
        String xInfo;
        char[] temp = new char[N];

        for(int x = 0; x < N; x++) {
            for(int i = 0; i < N; i++) temp[i] = hallway[i][x];
            xInfo = new String(temp);

            System.out.println(xInfo);

            if ((!xInfo.contains("T")
                    || (xInfo.contains("T") && !xInfo.contains("S")))
                    || (!xInfo.matches("[XOTS]*S[XST]+T[XOTS]*"))
                    && (!xInfo.matches("[XOTS]*T[XST]+S[XOTS]*"))){
                System.out.println("safe for this column");
                isSafeY[x] = true;

            }
        }
    }

    static void setCandidatesByRow(){
        int leftT, rightT;
        int leftS, rightS;
        char rangeStart;

        for(int y = 0; y < N; y++) {
            rangeStart = ' ';
            leftT = 100; rightT = 100;
            leftS = 100; rightS = 100;

            for(int x = 0; x < N; x++){
                if (hallway[y][x] == 'S'){
                    if(rangeStart == 'S' || rangeStart == ' ') { // S S or X S
                        rangeStart = 'S';
                        leftS = x;
                    } else{ // T S
                        rightS = x;
                        if (rightS - leftT > 1 && rightS != 100 && leftT != 100){
                            for (int k = leftT + 1; k < rightS; k++){
                                System.out.println("candidates added "+(N*y+k));
                                candidateSet.add(N * y + k);
                            }
                            System.out.println("Candidates added by right S");
                        }
                        rangeStart = 'T';
                    }
                } else if (hallway[y][x] == 'T'){ // T T or X T
                    if(rangeStart == 'T' || rangeStart == ' ') {
                        rangeStart = 'T';
                        leftT = x;
                    } else{ // S T
                        rightT = x;
                        if (rightT - leftS > 1 && rightT != 100 && leftS != 100) {
                            for (int k = leftS + 1; k < rightT; k++) {
                                System.out.println("candidates added "+(N*y+k));
                                candidateSet.add(N * y + k);
                            }
                            System.out.println("Candidates added by right T");
                        }
                        rangeStart = 'S';
                    }
                }

                System.out.println("position = " + (y * N + x));
                System.out.println("leftT = " + leftT);
                System.out.println("rightS = " + rightS);
                System.out.println("leftS = " + leftS);
                System.out.println("rightT = " + rightT);
                System.out.println("rangeStart = " + rangeStart);
                System.out.println();
            }
        }
    }
    static void setCandidatesByColumn(){
        int upT, downT;
        int upS, downS;
        char rangeStart;

        for(int x = 0; x < N; x++) {
            rangeStart = ' ';
            upT = 100; downT = 100;
            upS = 100; downS = 100;

            for(int y = 0; y < N; y++){
                if (hallway[y][x] == 'S'){
                    if(rangeStart == ' ' || rangeStart == 'S') { // S -> S
                        rangeStart = 'S';
                        upS = y;
                    } else{
                        downS = y;
                        if (downS - upT > 1 && downS != 100 && upT != 100){ // T -> S
                            for (int k = upT + 1; k < downS; k++){
                                candidateSet.add(N * k + x);
                                System.out.println("candidates added "+(N*k+x));
                            }
                            System.out.println("candidate added by down S");
                        }
                        rangeStart = 'T';
                    }
                } else if (hallway[y][x] == 'T'){
                    if(rangeStart == ' ' || rangeStart == 'T') { // T -> T
                        rangeStart = 'T';
                        upT = y;
                    } else {
                        downT = y;
                        if (downT - upS > 1 && downT != 100 && upS != 100) {
                            for (int k = upS + 1; k < downT; k++) {
                                candidateSet.add(N * k + x);
                                System.out.println("candidates added "+(N*k+x));
                            }
                            System.out.println("candidate added by down T");
                        }
                        rangeStart = 'S';
                    }
                }
                System.out.println("position = " + (y * N + x));
                System.out.println("leftT = " + upT);
                System.out.println("rightS = " + downS);
                System.out.println("leftS = " + upS);
                System.out.println("rightT = " + downT);
                System.out.println("rangeStart = " + rangeStart);
                System.out.println();
            }
        }
    }
}