package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class 색종이만들기_2630{
    final static int WHITE = 0;
    final static int BLUE = 1;
    final static int DIFF = -1;

    int N;
    int[][] graph;

    int[] confetti = new int[2]; // 총 색종이 개수를 세팅하는 배열 -> WHITE BLUE 순

    public 색종이만들기_2630(int n, int[][] graph) {
        N = n;
        this.graph = graph;
    }

    /*
    Role of Recursion

    return 반환
    자기를 기준으로 4분면으로 나누어 재귀호출을 해서
    모두 파란색이었는지, 모두 하얀색이었는지, 하나라도 달랐던게 있었는지(색 통일 불가)

    수행하는 operation
    1. 자기 아래에 있는 애들이 모두 같은 색깔이면 그 색 값만 반환하고
    (혹시라도 맨 위 계층이면 그 색에 해당되는 값을 더해줌)
    2. 다른 색깔을 반환하면 특정 색을 반환하는 곳에 대해서는 1만큼 더해주고
       특정 색을 반환하지 않는 경우는 이미 아래 콜스택에서 값을 더해준 상황이었다는 것이므로 아무 역할도 하지 않음
   */
    int recursion(int n, int sY, int sX){
        if(n == 1){
            return graph[sY][sX];
        }

        // 4분면으로 나누어 재귀호출 -> 모두 파랑인지, 하양인지, 아님 다른지 확인
        int[] areas = new int[4];
        areas[0] = recursion(n / 2, sY, sX);
        areas[1] = recursion(n / 2, sY + n / 2, sX);
        areas[2] = recursion(n / 2, sY, sX + n / 2);
        areas[3] = recursion(n / 2, sY + n / 2, sX + n / 2);

        // 모두 BLUE or WHITE 인 경우
        // 1. 가장 상위 층(n)일 경우 blue나 white 쪽에 1 더함
        // 2. 그렇지 않을 경우 blue/white 값을 그대로 돌려줌
        if (areas[0] != DIFF
                && (areas[0] == areas[1]) && (areas[1] == areas[2]) && (areas[2] == areas[3])){
            if(n == N) confetti[areas[0]]++;
            return areas[0];
        }

        // 자기 아래에서 이미 값이 다른게 있으면 현재 층에서 바로 값을 더해줌
        // (위까지 올라갈 필요 없음)
        else{
            for(int i = 0; i < 4; i++){
                // DIFF가 반환된 경우는 이미 아래쪽에서 BLUE나 WHITE 쪽에 더했을 것.
                if(areas[i] != DIFF) confetti[areas[i]]++;
            }
            return DIFF;
        }
    }

    String getAns() {
        recursion(N, 0, 0);
        return new StringBuilder(Integer.toString(confetti[WHITE])).append("\n").append(confetti[BLUE]).toString();
    }
}

class MainA2630 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        for(int i = 0; i < N; i++)
            graph[i] = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        System.out.println(new 색종이만들기_2630(N, graph).getAns());
    }
}