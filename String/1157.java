import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main1157 {
    public static void main(String[] args) throws IOException {
        int valueOf_a = 'a'; //97
        int valueOf_A = 'A'; //122
        int valueOf_z = 'z'; //65
        int valueOf_Z = 'Z'; //90
        int valueOfNewline = 10; //'\n'
        int distanceOfSmallBigLetter = valueOf_a - valueOf_A; //32
        int valueOfSingleLetter;
        int letterOfMax = -1;
        int numberOfMax = -1;
        int[] numberEachLetterAppeared = new int[valueOf_Z - valueOf_A + 1];

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while ((valueOfSingleLetter = isr.read()) != valueOfNewline) {
            if (valueOf_a <= valueOfSingleLetter && valueOfSingleLetter <= valueOf_z)
                valueOfSingleLetter -= distanceOfSmallBigLetter;
            numberEachLetterAppeared[valueOfSingleLetter - valueOf_A] += 1;
        }


        int numberToAppear;

        for (int i = 0; i < numberEachLetterAppeared.length; i++) {
            numberToAppear = numberEachLetterAppeared[i];

            if (numberOfMax < numberToAppear && numberToAppear != 0) {
                numberOfMax = numberToAppear;
                letterOfMax = i + valueOf_A;
            }
        }

        String max = "";
        for (int nToAppear : numberEachLetterAppeared) {
            if (numberOfMax == nToAppear) {
                max += "0";
            }
        }

        if (max.length() != 1)
            bw.write("?");
        else
            bw.write(letterOfMax);

        bw.flush();
        bw.close();
    }
}