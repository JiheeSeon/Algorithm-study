package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*문제 분석
트리의 원소 및 서브트리 삭제 -> 남은 트리에서 리프노드의 개수 구하기

1. child(graph) 를 두는 경우 :: 거슬러 내려가는 방식
-> 루트로부터 거슬러 내려갔을 때 더 이상 탐색할 게 없는 경우
-> Map<Integer, ArrayList<Integer>> 로 만들어 key가 없는 경우 개수 추가
>> 1번만 탐색해도 되므로 퍼포먼스가 보다 효율적, 구현이 귀찮음

2. parent 배열을 기준으로 모든 요소로부터 dfs 진행 :: 거슬러 올라오는 방식
-> 삭제하고자 하는 요소를 만나면 개수 추가 후 return
>> 여러번 탐색해서 퍼포먼스는 비효율적이지만, parent만으로 해결 가능

1, 2가 퍼포먼스 상 실제론 크게 차이 안 나는듯 (8ms)
아래의 solution은 2번에 해당
*/

class 트리_원소삭제_BottomUp {
    static int[] parent;
    static int toErase;
    static Set<Integer> leafs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        parent = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        int root = -2; // 아무 원소나 초기화 시켜놓음

        // 1. 인덱스들을 leaf node 후보들로 넣어놓은 후
        leafs = IntStream.range(-1, N).boxed().collect(Collectors.toCollection(HashSet::new));
        // 2. 다른 인덱스가 자길 parent 로 가리키면 parent이므로 leaf node 후보에서 제외
        for (int i = 0; i < N; i++){
            leafs.remove(parent[i]);
            if(parent[i] == -1) root = i; // root 를 따로 저장 -> 루트를 삭제하는 경우 체크
        }

        toErase = Integer.parseInt(br.readLine()); // 사용자가 삭제하고자 하는 노드

        // 루트를 삭제하면 남는 원소는 0개
        if(toErase == root){
            System.out.println(0);
            return;
        }

        // leaf node들에 한해 아래에서부터 올라가도록
        for(int i : leafs){
            // parent[i] == -2 가 의미하는 바
            // 이미 삭제하고자 하는 노드의 후손으로 체크됨
            if(parent[i] != -2) eraseIfNeeded(i);
        }

        // 트리 원소 삭제 후 leaf node 체크
        leafs = IntStream.range(-1, N).boxed().collect(Collectors.toCollection(HashSet::new));
        for (int i = 0; i < N; i++) {
            if(parent[i] == -2) leafs.remove(i);
            else leafs.remove(parent[i]);
        }

        System.out.println(leafs.size());
    }

    static boolean eraseIfNeeded(int now){
        // 종료조건 세팅
        if(now == toErase){ // 현재 원소가 삭제할 원소이면
            parent[now] = -2;
            return true;
        }

        if(parent[now] == -2) return true; // 조상이 지워야 하는 원소인 경우
        else if(parent[now] == -1) return false; // 지워야 하는 원소를 거치지 않는 경우(루트로 도달)

        if(eraseIfNeeded(parent[now])){ // 실질적으로 재귀호출
            parent[now] = -2;
            return true;
        }

        return false;
    }
}
