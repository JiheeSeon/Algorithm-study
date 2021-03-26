package Two_Pointer;

import java.io.*;
import java.util.regex.Pattern;

class Main11728{
    static int lenA, lenB;
    static long[] A, B;
    static StringBuilder stb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();
        lenA = input[0]; lenB = input[1];

        A = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();
        B = Pattern.compile(" ").splitAsStream(br.readLine()).mapToLong(Long::parseLong).toArray();

        solution();
        System.out.println(stb);
    }
    static void solution(){
        int pointerA = 0; int pointerB = 0;

        while (!(pointerA >= lenA && pointerB >= lenB)){
            if (pointerA >= lenA)
                stb.append(B[pointerB++]).append(" ");
            else if(pointerB >= lenB)
                stb.append(A[pointerA++]).append(" ");
            else
                stb.append(A[pointerA] < B[pointerB] ? A[pointerA++]: B[pointerB++]).append(" ");
        }
    }
}