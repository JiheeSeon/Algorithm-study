package Bruteforce.Backtrack;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

class sma1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stb = new StringBuilder();
    static char[] skill;
    static ArrayList<Integer>[] nextSkill;
    static boolean [] visited;

    public static void main(String[] args) throws IOException {
        skill = getInput();
        int numOfRelatedSkills = Integer.parseInt(br.readLine());
        nextSkill = (ArrayList<Integer>[])new ArrayList[skill.length];
        visited = new boolean[skill.length];

        int i;
        for (i = 0; i < numOfRelatedSkills; i++)
            nextSkill[i] = new ArrayList<>();

        char[] temp;
        char current, next;
        int currentSkillIdx, nextSkillIdx;

        for (i = 0; i < numOfRelatedSkills; i++) {
            temp = getInput();
            current = temp[0]; next = temp[1];
            currentSkillIdx = convertSkillToIndex(current);
            nextSkillIdx = convertSkillToIndex(next);

            nextSkill[currentSkillIdx].add(nextSkillIdx);
        }

        for (i = 0; i < numOfRelatedSkills; i++)
            dfs(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("");
        bw.flush();
        bw.close();
    }

    static char[] getInput() throws IOException {
        return br.readLine().trim().toCharArray();
    }

    static int convertSkillToIndex(char skillName){
        return Arrays.binarySearch(skill, skillName);
    }

    static void dfs(int n){
        if (nextSkill[n].isEmpty()){
            stb.insert(0, skill[n]);
            return;
        }

        int skillIdx;
        for (int i = 0; i < nextSkill[n].size(); i++){
            skillIdx = nextSkill[n].get(i);
            dfs(skillIdx);
            stb.insert(0, skill[skillIdx] +" ");
        }
    }
}
