package Sort.TopologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class 게임개발_1516 {
    int N;
    Map<Integer, Integer> buildTime = new HashMap<>();
    ArrayList<Integer>[] graphForNext;
    ArrayList<Integer>[] graphForPrevious;
    ArrayList<Integer> startBuildings = new ArrayList<>();
    int[] dp, inDegree;

    public 게임개발_1516(int n, int[][] buildingInfo) {
        N = n;

        graphForNext = new ArrayList[N + 1];
        graphForPrevious = new ArrayList[N + 1];
        inDegree = new int[N + 1];
        dp = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            graphForNext[i] = new ArrayList<>();
            graphForPrevious[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++){
            buildTime.put(i, buildingInfo[i][0]);

            if(buildingInfo[i].length == 2){
                startBuildings.add(i);
            } else{
                inDegree[i] = buildingInfo[i].length - 2;

                for(int j = 1; j < buildingInfo[i].length - 1; j++) {
                    graphForNext[buildingInfo[i][j]].add(i);
                    graphForPrevious[i].add(buildingInfo[i][j]);
                }
            }
        }
    }

    String getAns() {
        Queue<Integer> q = new LinkedList<>(startBuildings);
        int now;
        while (!q.isEmpty()) {
            now = q.poll();

            // add into Queue, cut out-way line
            for(int next: graphForNext[now]){
                inDegree[next]--;
                if(inDegree[next] == 0) q.add(next);
            }

            if(graphForPrevious[now].isEmpty())
                dp[now] = buildTime.get(now);
            else {
                for (int prev : graphForPrevious[now]) {
                    dp[now] = Math.max(dp[now], dp[prev] + buildTime.get(now));
                }
            }
        }

        StringBuilder stb = new StringBuilder();
        for(int i = 1; i <= N; i++)
            stb.append(dp[i]).append("\n");
        return stb.toString();
    }
}

class MainA1516{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 건물 짓는데 걸리는 시간 / 선행번호들 / -1까지

        int N = Integer.parseInt(br.readLine());
        int[][] buildingInfo = new int[N + 1][];
        for(int i = 1; i <= N; i++)
            buildingInfo[i] = strToIntArr(br.readLine());

        System.out.print(new 게임개발_1516(N, buildingInfo).getAns());
    }

    static int[] strToIntArr(String s) {
        return Pattern.compile(" ").splitAsStream(s).mapToInt(Integer::parseInt).toArray();
    }
}
