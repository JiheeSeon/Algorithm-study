package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 트리라는 그래프 형태를 사용해야 한다는 것을 파악하면 끝나는 문제*/

class 트리의기본속성_9372 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] tmp; int V, E;
        StringBuilder stb = new StringBuilder();

        while(--T >= 0){
            tmp = InputProcessor.strToIntArr(br.readLine());
            V = tmp[0]; E = tmp[1];
            for(int e = 0; e < E; e++) br.readLine();
            stb.append(V - 1).append("\n");
        }
        System.out.print(stb);
    }
}