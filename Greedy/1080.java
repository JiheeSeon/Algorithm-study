import java.io.*;
import java.util.regex.Pattern;

class Main1080{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int [] input = processInput(2);
        int yHeight = input[0]; int xWidth = input[1];

        int[][] matrixA = new int[yHeight][xWidth];
        int[][] matrixB = new int[yHeight][xWidth];

        for(int row = 0; row < yHeight; row++)
            matrixA[row] = processInput(xWidth);
        for(int row = 0; row < yHeight; row++)
            matrixB[row] = processInput(xWidth);


        bw.write(" ");
        bw.flush();
        bw.close();
    }

    static int[] processInput(int limitSize) throws IOException{
        return Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).limit(limitSize).toArray();

    }
}