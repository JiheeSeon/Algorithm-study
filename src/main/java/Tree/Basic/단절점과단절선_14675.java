package Tree.Basic;

import Util.InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class 단절점과단절선_14675 {
    final int CUT_VERTEX_MODE = 1;

    int V;
    int[] check;

    public 단절점과단절선_14675(int v, int[] check) {
        V = v;
        this.check = check;
    }

    String solve(int a) {
        return check[a] != 0 ? "no\n" : "yes\n";
    }
}

class MainA14675{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp;

        int V = Integer.parseInt(br.readLine());

        int[] check = new int[V + 1];

        for (int v = 0; v < V - 1; v++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            check[tmp[0]]++; check[tmp[1]]++;
        }

        단절점과단절선_14675 solution = new 단절점과단절선_14675(V, check);
        StringBuilder stb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        // 트리의 모든 선은 단절선
        // 트리의 점은 하나의 선분과만 연결되어 있으면 그래프를 두개 이상으로 쪼갤 수 없음.
        for (int q = 0; q < Q; q++) {
            tmp = InputProcessor.strToIntArr(br.readLine());
            stb.append(tmp[0] == 2 ? "yes\n" : (check[tmp[1]] == 1 ? "no\n" : "yes\n"));
        }
        System.out.print(stb);
    }
}