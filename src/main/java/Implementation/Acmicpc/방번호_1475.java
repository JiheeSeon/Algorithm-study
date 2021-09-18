package Implementation.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class MainA1475{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();
        char c;

        int numOfSpecialNumber = 0; // 6, 9
        int[] cntArray = new int[10]; // 한 '세트' 임에 유의할 것, 각 숫자마다의 출현회수를 기록해야 함

        for (int i = 0; i < number.length(); i++) {
            c = number.charAt(i);

            if(c == '6' || c == '9') numOfSpecialNumber++;
            else cntArray[c - '0']++;

        }

        int totalCnt = Arrays.stream(cntArray).max().getAsInt();

        if(numOfSpecialNumber > totalCnt * 2)
            totalCnt += (int)(Math.ceil((numOfSpecialNumber - totalCnt * 2) / 2.0));

        System.out.println(totalCnt);
    }
}

/* 반례
1234566
1

(ㅂㄷㅂㄷ)

12345
1

1234566612
2
 */