package All_Union.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class Main1043 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int[] input = getInput();
        int N = input[0]; int M = input[1];
        parent = IntStream.rangeClosed(0, N).toArray();

        int[] truth = getInput(); // 1-truthN 까지 유효
        int truthN = truth[0];

        if (truthN == 0) {
            System.out.println(M);
            return;
        }

        for(int i = 1; i < truth.length - 1; i++){
            union(truth[i], truth[i + 1]);
        }

        int[] tmp;
        ArrayList<Integer>[] participates = (ArrayList<Integer>[]) new ArrayList[M]; // party -> who participates?
        for (int i = 0; i < participates.length; i++) {
            participates[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            tmp = getInput();

            if(tmp.length == 2) participates[i].add(tmp[1]);

            for(int j = 1; j < tmp.length - 1; j++){
                union(tmp[j], tmp[j + 1]);
                participates[i].add(tmp[j]);
                participates[i].add(tmp[j + 1]);
            }
        }

        boolean flag;
        int result = 0;
        for(int i = 0; i < participates.length; i++){
            flag = true;
            for(int person: participates[i]){
                if(find(person) == find(truth[1])){
                    flag = false; break;
                }
            }
            if(flag) result++;
        }

        System.out.println(result);
    }

    static int[] getInput() throws IOException {
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
    }

    static void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX == parentY) return;

        if(parentX > parentY) parent[parentX] = parentY;
        else parent[parentY] = parentX;
    }

    static int find(int x) {
        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }
}
/*푸는 데 시간을 끌었던 부분
* 원소가 하나인 경우 for 문 안에 안 들어가서 arraylist로 못 들어간다는 것을 생각 X
* 현 시점 기준으로 되는지를 보는줄 알았음. 마지막에 find로 한번 최초조상으로 갈아엎어준 후 확인해야 한다는 것을 누락
* */