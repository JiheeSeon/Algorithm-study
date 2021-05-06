package Implementation.Programmers.lv2;

class 타겟넘버_43165 {
    int[] dm = {1, -1};
    int answer = 0;

    public int solution(int[] numbers, int target) {
        backtrack(0, numbers.length, 0, target, numbers);
        return answer;
    }
    void backtrack(int depth, int maxDepth, int acc, int target, int[] numbers){
        if(depth == maxDepth){
            if(acc == target) answer++;
            return;
        }
        for(int i = 0; i < 2; i++){
            backtrack(depth + 1, maxDepth, acc + dm[i] * numbers[depth], target, numbers);
        }
    }
}
