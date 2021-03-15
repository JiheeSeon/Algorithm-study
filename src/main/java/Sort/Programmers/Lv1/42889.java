package Sort.Programmers.Lv1;

import java.util.Arrays;
import java.util.Comparator;

class Main42889{
    public static void main(String[] args) {
        int[] res = solution(7, new int[]{4, 4, 4, 4, 5});
        for(int i : res)
            System.out.print(i + " ");
    }
    static int[] solution(int N, int[] stages) {
        /*문제 flow
        1. stages 배열에서 각 단계마다 개수를 세야 함
            -> 나오지 않은 단계도 세야 하는가? YES
        2. 각 단계에 도달한 총 플레이어 수도 필요
            -> 그 때 그 때 구할 수 있는가? YES 뒤에서부터 가능
            -> 미리 계산해서 저장해둘 필요가 있는가?
               YES. 정렬의 기준이 되어야 함
        3. 실패율 = 1번 / 2번 -> 배열 하나로 쇼부 가능
        4. 실패율 기준으로 정렬, then 스테이지 번호로 오름차순 정렬
            -> 정렬하면 당연히 스테이지 번호 순서가 다 망가짐, 기존 인덱스를 알길 없음
            -> 객체 배열 or list 형태여야 변형 없이 sort 하기 쉬움

        정리
        - 기존 인덱스, appeared count 를 저장한 객체 sort
        * */
        Stage[] stageArray = new Stage[N];

        for(int i = 0; i < N; i++){
            stageArray[i] = new Stage(i + 1);
        }

        for (int stage : stages) { if(stage != N + 1) stageArray[stage - 1].increaseBlockedCount(); }

        int numOfAll = stages.length;
        for(int i = 0; i < N; i++){
            stageArray[i].setTotalCount(numOfAll);
            numOfAll -= stageArray[i].blockedCount;
        }

        Arrays.sort(stageArray,
                Comparator.comparingDouble((Stage s) -> s.isBothCountZero() ? 0 : (s.blockedCount/(double)s.totalCount))
                        .reversed()
                        .thenComparing(s -> s.stageNum));

        for(Stage stage : stageArray)
            System.out.println(stage);

        return Arrays.stream(stageArray).mapToInt(s->s.stageNum).toArray();
    }
    private static class Stage{
        int stageNum, blockedCount, totalCount;

        public Stage(int stageNum){
            this.stageNum = stageNum;
        }
        public void increaseBlockedCount() {
            blockedCount++;
        }
        public void setTotalCount(int totalCount){
            this.totalCount = totalCount;
        }

        public boolean isBothCountZero(){
            return blockedCount == 0 && totalCount == 0;
        }

        @Override
        public String toString() {
            return "Stage{" +
                    "stageNum=" + stageNum +
                    ", blockedCount=" + blockedCount +
                    ", totalCount=" + totalCount +
                    '}';
        }
    }
}