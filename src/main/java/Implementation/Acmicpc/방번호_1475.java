package Implementation.Acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MainA1475{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();
        char c;
        int numOfGeneralNumber = 0;
        int numOfSpecialNumber = 0;

        for (int i = 0; i < number.length(); i++) {
            c = number.charAt(i);

            if(c == '6' || c == '9') numOfSpecialNumber++;
            else numOfGeneralNumber++;
        }

        long totalCnt = numOfGeneralNumber;

        if(numOfSpecialNumber > numOfGeneralNumber * 2)
            totalCnt += (int)(Math.ceil((numOfSpecialNumber - numOfGeneralNumber * 2) / 2.0));

        System.out.println(totalCnt);
    }
}

/* 반례
99991
2

900966
2

101010
6

11169696969
4

99999999612
5

123456666666666666
7
 */