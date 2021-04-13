package All_Union.Acmicpc.Resolve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/*
문제내용
   1-G까지의 gate 존재
   비행기가 도킹하고자 하는 게이트번호 : 1 - 입력값
   도킹할 수 있는 최대 비행기 수?

문제분석
   나보다 작거나 같은 애들에게는 누구나 넣어도 된다
   그러면서 가장 많은 비행기를 도킹시켜야 한다?
   -> 순서를 매겨주자! 큰애부터 채우면서 점점 내려가자 (뒷자리=뒷게이트부터 꽉꽉 채우자!)
   -> 이미 도킹된 게이트일 경우, 그것보다 작은데 아직 안 채워진 게이트에 도킹해야 함.
   -> 그럼 아직 안 채워진 게이트를 어떻게 알 수 있을까? 안내해주는 표지판? 길?이 있다면?

왜 Union find인가? set이 의미하는 바?
   set은 (root 제외) 이미 도킹되면서 연속된 숫자의 게이트 집합
   또는, 이미 root 번호의 gate로 도킹하라고 가리키는 게이트들의 집합
   Union = 여기는 이미 도킹되었으니,다음 도킹될 수 있는 곳을 가리킬게! 라는 의미
   해당 문제의 경우 넣을 수 없는 게이트(0 이하)면 블록해줘야 함. 총 Union된 개수를 세야 함
*/

public class Airport {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = IntStream.rangeClosed(0, G + 1).toArray();

        int[] toDock = new int[P];
        int i;
        for (i = 0; i < P; i++) {
            toDock[i] = Integer.parseInt(br.readLine());
            if(find(toDock[i]) == 0) break;
            union(find(toDock[i]), find(toDock[i]) - 1);
        }
        System.out.println(i);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) return; //이미 같은 set에 있음

        // root 설정
        if(parentA > parentB) parent[parentA] = parentB;
        else parent[parentB] = parentA;
    }

    static int find(int a) {
        return a == parent[a] ? a : (parent[a] = find(parent[a]));
    }
}
