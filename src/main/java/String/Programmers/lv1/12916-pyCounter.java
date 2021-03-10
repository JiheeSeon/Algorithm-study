package String.Programmers.lv1;

import java.io.*;

class Main12916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Boolean.toString(solution(s)));
        bw.flush();
        bw.close();
    }

    static boolean solution(String s) {
        int numOfP = 0, numOfY = 0;
        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'p') numOfP++;
            else if(s.charAt(i) == 'y') numOfY++;
        }

        return numOfP == numOfY;
    }
}