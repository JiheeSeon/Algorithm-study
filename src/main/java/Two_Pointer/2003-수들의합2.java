package Two_Pointer;

import java.io.*;
import java.util.regex.Pattern;

class Main2003{
    /* 투포인터
        * start == end -> sum = 0, 아직 넣은 요소가 아무것도 없다
        * start 올리면, 현재 start가 가리키는 값을 뺀다
        -> 앞으로 start를 유지시켜봤자, 구간합이 더 커지기만 할 뿐, 가망 없다. 올려!
        * end 올리면, end -1 에 있는 원소를 넣어서 구간합을 갱신한다.
        * end 가 배열 마지막 인덱스보다 더 갔다
        -> 더 이상 start를 옮길 수밖에 없다, 즉 값이 작아지는 것에서 희망을 걸어야 한다.
        -> 아니라면 나머지 남은 것들을 합쳐봤자 답이 없음. 거기서 멈추는게 중요
        * 즉, 작아지게 했을 때 가망이 있는지를 보는게 우선순위.
    * */
    static int N, M;
    static int[] A;
    static int result;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        N = input[0]; M = input[1];
        A = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        solution();

        System.out.println(result);
    }
    static void solution(){
        int sum = 0;
        int start = 0; int end = 0;

        while(true){
            // sum 값 구하기
            if (sum >= M) sum -= A[start++]; //start를 증가시키면서 기존 구간 합 값을 조정해 재사용
            else if (end == N) break; // 이미 가망 없는 sum을 가지고 있으면 바로 빼기
            else sum += A[end++]; // 자기 이전까지의 합 구하기

            // 위에서 구한 sum 값을 토대로 result 세팅
            if (sum == M) result++;
        }
    }
}
