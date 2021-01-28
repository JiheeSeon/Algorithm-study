import java.io.*;

class Main5622 {
    public static void main(String[] args) throws IOException {
        int intValueOfA = 'A'; //65
        int intValueOfNewline = 10;
        int intValueOfCurrentLetter;
        int indexOfCurrentLetter;
        int timeToCall = 0;

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while ((intValueOfCurrentLetter = isr.read()) != intValueOfNewline){
            indexOfCurrentLetter = intValueOfCurrentLetter - intValueOfA;
            if(indexOfCurrentLetter < 15)
                timeToCall += 2 + 1 + indexOfCurrentLetter / 3;
            else if(indexOfCurrentLetter <= 18)
                timeToCall += 2 + 1 + 5;
            else if(indexOfCurrentLetter <= 21)
                timeToCall += 2 + 1 + 6;
            else if(indexOfCurrentLetter <= 25)
                timeToCall += 2 + 1 + 7;
        }

        bw.write(Integer.toString(timeToCall));
        bw.flush();
        bw.close();
    }
}