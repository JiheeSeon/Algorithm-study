package Bruteforce.Backtrack;

import java.io.*;
import java.util.*;

class sma1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stb = new StringBuilder();

    static char[] skills;
    static HashMap<Character, Character> previousSkills = new HashMap<Character, Character>();


    public static void main(String[] args) throws IOException {
        skills = getInput();
        int N = Integer.parseInt(br.readLine());

        char[] temp;
        char prevSkillName, currSkillName;

        for (int i = 0; i < N; i++) {
            temp = getInput();

            prevSkillName = temp[0];
            currSkillName = temp[1];

            previousSkills.put(currSkillName, prevSkillName);
        }

        for (Map.Entry<Character, Character> entry : previousSkills.entrySet()) {
            if (!(previousSkills.containsValue(entry.getKey())))
                dfs(entry.getKey());
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.substring(1));
        bw.flush();
        bw.close();
    }

    static char[] getInput() throws IOException {
        return br.readLine().replace(" ", "").toCharArray();
    }

    static void dfs(char currentSkill) {
        if (!previousSkills.containsKey(currentSkill)) {
            stb.insert(0, currentSkill).insert(0, "\n");
            return;
        }

        stb.insert(0, currentSkill).insert(0, " ");
        dfs(previousSkills.get(currentSkill));
    }
}
