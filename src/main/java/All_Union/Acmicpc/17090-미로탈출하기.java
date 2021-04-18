package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class Main17090{
    static int[] parent;
    static char[] maze;
    static boolean[] check;

    static int modColN, modRowN;
    static boolean outOfBoundFlag;
    static boolean cycleFlag;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        int colN = tmp[0]; int rowN = tmp[1];

        modColN = colN + 2;
        modRowN = rowN + 2;

        maze = new char[modColN * modRowN];

        String tmpS;
        for (int y = 1; y < modColN - 1; y++) {
            tmpS = br.readLine();
            for (int x = 1; x < modRowN - 1; x++) {
                maze[y * modRowN + x] = tmpS.charAt(x - 1);
            }
        }

        parent = IntStream.rangeClosed(0, modColN * modRowN).toArray();

        int start, next;
        int curr;

        for (int y = 1; y < modColN - 1; y++) {
            for(int x = 1; x < modRowN - 1; x++) {
                parent = IntStream.rangeClosed(0, modColN * modRowN).toArray();

                start = y * modRowN + x;
                curr = start;

                outOfBoundFlag = false;
                cycleFlag = false;

                while (true) {
                    next = nextIdx(curr, maze[curr]);

                    if (next == -1) {
                        ans++;
                        break;
                    }
                    union(curr, next);

                    if (cycleFlag) break;

                    curr = next;
                }
            }
        }

        System.out.println(ans);
    }

    static int nextIdx(int curr, char sign) {
        return switch (maze[curr]) {
            case 'U' -> curr - modRowN;
            case 'D' -> curr + modRowN;
            case 'L' -> curr - 1;
            case 'R' -> curr + 1;
            default -> -1;
        };
    }

    static void union(int a, int b) {
        if(maze[a] == 0 || maze[b] == 0){
            outOfBoundFlag = true;
            return;
        }
        int pA = find(a);
        int pB = find(b);

        if(pA == pB){
            cycleFlag = true;
            return;
        }

        if(pA < pB) parent[pB] = pA;
        else parent[pA] = pB;
    }

    static int find(int a) {
        return a == parent[a] ? a : (parent[a] = find(parent[a]));
    }
}