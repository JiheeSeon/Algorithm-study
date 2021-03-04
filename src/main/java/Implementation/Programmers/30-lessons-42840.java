package Implementation.Programmers;

import java.util.*;

class Solution42840 {
    public int[] solution(int[] answers) {
        int[] answer = {};

        Picker[] pickers = new Picker[3];
        for(int i = 0; i < 3; i++)
            pickers[i] = new Picker(i + 1);

        int[][] ansPattern = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};

        for (int i = 0; i < answers.length; i++){
            for (int j = 0; j < 3; j++){
                if (ansPattern[j][i % ansPattern[j].length] == answers[i])
                    pickers[j].score++;
            }
        }

        Arrays.sort(pickers, Comparator.comparing((Picker p) -> p.score, Comparator.reverseOrder())
                .thenComparing((Picker p) -> p.type));

        int len = 1;
        if (pickers[1].score == pickers[0].score){
            len++;
            if (pickers[2].score == pickers[0].score) len++; }

        answer = new int[len];

        for(int i = 0; i < len; i++){
            answer[i] = pickers[i].type;
        }

        return answer;
    }

    class Picker{
        int score, type;

        public Picker(int type){
            this.type = type;
            score = 0;
        }
    }
}