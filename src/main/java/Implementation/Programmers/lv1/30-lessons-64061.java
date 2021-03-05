package Implementation.Programmers.lv1;

import java.util.*;

class Solution64061 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        int[] topIndex = new int[board.length];
        Arrays.fill(topIndex, -1);

        int lane, temp;

        Stack<Integer> dollStack = new Stack();

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[j][i] == 0) continue;
                else{
                    topIndex[i] = j;
                    break;
                }
            }
        }

        for(int i = 0; i < moves.length; i++){
            lane = moves[i] - 1;

            if (topIndex[lane] == -1 || topIndex[lane] >= board.length) continue;
            if (dollStack.isEmpty() || (!dollStack.isEmpty() && dollStack.peek() != board[topIndex[lane]][lane]))
                dollStack.push(board[topIndex[lane]][lane]);
            else {
                dollStack.pop();
                answer+=2;
            }
            topIndex[lane]++;
        }

        return answer;
    }
}