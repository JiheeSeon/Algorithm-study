package All_Union.Acmicpc.Resolve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/*
거짓말쟁이로 알려지는 조건
    1. 진실을 아는 사람들이 있을 때
    2. 어떤 파티에서는 진실을 듣고, 다른 파티에서는 과장된 이야기를 들울 때
    -> 진실을 아는 사람들이 있는 파티에 참여하고, 진실을 아는 사람이 없는 파티에 참여한 경우
    모든 파티에 참여할 것임
* */
public class Lie {
    static int[] parent;
    static boolean[] truthParty;
    static int ancestorOfTruth = Integer.MAX_VALUE;
    static int[][] partyInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmpInput = readAndSplit(br);

        int N = tmpInput[0];
        parent = IntStream.rangeClosed(0, N).toArray();

        int partyN = tmpInput[1];
        truthParty = new boolean[partyN];
        partyInfo = new int[partyN][];

        tmpInput = readAndSplit(br);
        int truthN = tmpInput[0];

        if(truthN == 1) ancestorOfTruth = tmpInput[1];
        else if(truthN > 1){
            for (int i = 1; i < truthN; i++)
                union(tmpInput[i], tmpInput[i + 1]);
        } else{
            System.out.println(partyN);
            return;
        }

        // 파티마다 Union 했을 때 진실트리에 있는지 확인해야 함.
        int participantN;
        int partyIdx; int participantIdx;

        for (partyIdx = 0; partyIdx < partyN; partyIdx++) {
            tmpInput = readAndSplit(br);
            participantN = tmpInput[0];
            partyInfo[partyIdx] = new int[participantN];

            for (participantIdx = 1; participantIdx < participantN; participantIdx++) {
                partyInfo[partyIdx][participantIdx - 1] = tmpInput[participantIdx];
                union(tmpInput[participantIdx], tmpInput[participantIdx + 1]);
            }
            partyInfo[partyIdx][participantIdx - 1] = tmpInput[participantIdx];
        }
//
//        System.out.println(Arrays.toString(parent));

        int result = 0;
        boolean toAdd;
        for (partyIdx = 0; partyIdx < partyN; partyIdx++) {
            toAdd = true;
            for (participantIdx = 0; participantIdx < partyInfo[partyIdx].length; participantIdx++) {
                if(find(partyInfo[partyIdx][participantIdx]) == ancestorOfTruth){
                    toAdd = false; break;
                }
            }
            if(toAdd) result++;
        }
        System.out.println(result);
    }

    static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if (pA == pB) return;

        if (pA > pB){
            parent[pA] = pB;
            ancestorOfTruth = Math.min(pB, ancestorOfTruth);
        }
        else{
            parent[pB] = pA;
            ancestorOfTruth = Math.min(pA, ancestorOfTruth);
        }
    }

    static int find(int a) {
        return a == parent[a] ? a : (parent[a] = find(parent[a]));
    }

    static int[] readAndSplit(BufferedReader br) throws IOException{
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }
}
