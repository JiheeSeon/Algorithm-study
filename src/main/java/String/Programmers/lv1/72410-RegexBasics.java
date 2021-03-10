package String.Programmers.lv1;

import java.io.*;

class Main72410 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String oldId = br.readLine();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(solution(oldId));
        bw.flush();
        bw.close();
    }

    static String solution(String new_id) {
        //...!@BaT#*..y.abcdefghijklm
        String answer = "";

        answer = new_id.toLowerCase();

        answer = answer.replaceAll("[^a-z0-9-_.]", "");
        answer = answer.replaceAll("(\\.)(\\.)+", ".");
        answer = answer.replaceAll("^(\\.)|(\\.)$", "");
        if (answer.equals("")) answer = "a";
        if (answer.length() >= 16) answer = answer.substring(0, 15);
        answer = answer.replaceAll("(\\.)$", "");

        StringBuilder stb = new StringBuilder(answer);

        while(stb.length() < 3)
            stb.append(answer.charAt(answer.length() - 1));

        return stb.toString();
    }
}