package Implementation.Programmers.lv1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

class Main68644{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int[] input = Pattern.compile(" ").splitAsStream(br.readLine()).mapToInt(Integer::parseInt).toArray();

        int[] ans = solution(input);
        for(int i : ans)
            stb.append(i).append(" ");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }

    static int[] solution(int[] numbers) {
        int[] answer;

        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < numbers.length; i++){
            for(int j = i + 1; j < numbers.length; j++){
                set.add(numbers[i] + numbers[j]);
            }
        }
        ArrayList<Integer> ar = new ArrayList<>(set);
        Collections.sort(ar);
        answer = ar.stream().mapToInt(o->o).toArray();

//        answer = set.stream().sorted().mapToInt(o->o).toArray();
        return answer;
    }
}