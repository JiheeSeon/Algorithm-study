package Recursion;

import java.io.*;

class 재귀함수가뭔가요_17478 {
    String introS = "____";
    StringBuilder localS;
    int maxDepth;

    public 재귀함수가뭔가요_17478(int maxDepth){
        this.maxDepth = maxDepth;
    }

    String getAns(){
        StringBuilder stb = new StringBuilder();
        stb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.").append("\n");
        solution_1(0, stb);

        return stb.toString();
    }

    /*
    solution 1
    StringBuilder를 인자로 전해서 인자에 작업을 함.
    -> 외부에서 정의한 StringBuilder의 참조값으로 공통되는 작업 진행(append)
    -> 외부에서 정의되었기 때문에 따로 반환값을 줄 필요 없음.
    */

    private void solution_1(int depth, StringBuilder stb){
        String introStr = "";

        // introStr를 세팅하기 위한 과정
        if(depth >= 1){
            localS = new StringBuilder();
            for(int i = 0; i < depth; i++) localS.append(introS);
            introStr = localS.toString();
        }

        if(depth == maxDepth){
            stb.append(introStr).append("\"재귀함수가 뭔가요?\"\n");
            stb.append(introStr).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
            stb.append(introStr).append("라고 답변하였지.\n");
            return;
        }

        // 해당 단계에서 필요한 string을 append하기
        stb.append(introStr).append("\"재귀함수가 뭔가요?\"\n");
        stb.append(introStr).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n").append(introStr).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n").append(introStr).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");

        // 다음 단계 호출 -> 자신의 작업을 완료하고 append한 상태의 stringbuilder를 넘겨줌.
        solution_1(depth + 1, stb);

        // 마무리
        stb.append(introStr).append("라고 답변하였지.\n");
    }
}
class MainA17478 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int D = Integer.parseInt(br.readLine());
        System.out.print(new 재귀함수가뭔가요_17478(D).getAns());
    }
}