package CramForCodingTest.SampleCode;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
상정한 문제 : 모든 정점 간의 최단거리
(11404 플로이드 참고)
*/

class FloydWarshall {
    int V, E;
    int[][] weights;
    final static int INF = 987654321; // 충분히 크지 않으면 틀렸습니다 뜸

    public FloydWarshall(int v, int e, int[][] weights) {
        V = v;
        E = e;
        this.weights = weights;
    }

    String solve() {
        for (int l = 1; l <= V; l++) { // 경유지 후보
            for (int src = 1; src <= V; src++) {
                if(l == src) continue;

                for (int dst = 1; dst <= V; dst++) {
                    if(src == dst) continue;

                    if(weights[src][l] != INF && weights[l][dst] != INF
                            && weights[src][dst] > weights[src][l] + weights[l][dst])
                        weights[src][dst] = weights[src][l] + weights[l][dst];
                }
            }
        }

        StringBuilder stb = new StringBuilder();

        for (int r = 1; r < weights.length; r++) {
            for (int c = 1; c < weights.length; c++) {
                stb.append(weights[r][c] == INF ? 0 : weights[r][c]).append(" ");
            }
            stb.append("\n");
        }
        return stb.toString();
    }
}

class FloydWarshallApp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        final int INF = 987654321;
        int[] tmp;
        int[][] weights = new int[V + 1][V + 1];

        for (int src = 1; src <= V; src++) {
            for (int dst = 1; dst <= V; dst++) {
                weights[src][dst] = (src == dst) ? 0 : INF;
            }
        }

        for (int e = 0; e < E; e++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            weights[tmp[0]][tmp[1]] = Math.min(weights[tmp[0]][tmp[1]], tmp[2]);
        }

        System.out.print(new FloydWarshall(V, E, weights).solve());
    }
}