package String;
import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

class Main10809 {
    public static void main(String[] args) throws IOException {
        final int newLineAsciiValue = 10;
        final int aToAsciiValue = 97;
        int currentCharacterInAsciiValue;
        int whileIndex = 0;
        int indexToSet;

        int[] currentCharacterLocation = new int['z' - 'a' + 1];
        Arrays.fill(currentCharacterLocation, -1);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while ((currentCharacterInAsciiValue = isr.read()) != newLineAsciiValue) {
            indexToSet = currentCharacterInAsciiValue - aToAsciiValue;

            if (currentCharacterLocation[indexToSet] == -1)
                currentCharacterLocation[indexToSet] = whileIndex++;
            else
                whileIndex++;
        }

        bw.write(Arrays.stream(currentCharacterLocation).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        bw.flush();
        bw.close();
    }
}